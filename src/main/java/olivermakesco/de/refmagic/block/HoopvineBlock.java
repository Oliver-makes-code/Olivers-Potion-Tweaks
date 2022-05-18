package olivermakesco.de.refmagic.block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class HoopvineBlock extends Block {
    public static final BooleanProperty top = BooleanProperty.of("top");
    public static final VoxelShape topShape = VoxelShapes.cuboid(6/16f,0,6/16f,10/16f,15/16f,10/16f);
    public static final VoxelShape bottomShape = VoxelShapes.cuboid(6/16f,0,6/16f,10/16f,1,10/16f);

    public HoopvineBlock() {
        super(QuiltBlockSettings.copyOf(Blocks.GRASS).material(Material.PLANT).sounds(BlockSoundGroup.NETHER_SPROUTS));
        setDefaultState(getStateManager().getDefaultState().with(top, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(top);
    }

    protected boolean canPlantOnTop(BlockState floor) {
        return floor.isIn(EnliumBlock.enliumGrowable) || floor.getBlock() == this;
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (!state.canPlaceAt(world,pos)) return Blocks.AIR.getDefaultState();
        if (world.getBlockState(pos.up()).getBlock() != this) return state.with(top,true);
        return state.with(top,false);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var state = super.getPlacementState(ctx);
        if (ctx.getWorld().getBlockState(ctx.getBlockPos().up()).getBlock() != this) return state.with(top,true);
        return state.with(top,false);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlantOnTop(world.getBlockState(pos.down()));
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.AIR && !this.collidable || super.canPathfindThrough(state, world, pos, type);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(top)) return topShape;
        return bottomShape;
    }
}
