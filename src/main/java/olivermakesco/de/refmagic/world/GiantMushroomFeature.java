package olivermakesco.de.refmagic.world;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import olivermakesco.de.refmagic.Mod;
import olivermakesco.de.refmagic.registry.RefinedMagicBlocks;

public class GiantMushroomFeature extends Feature<TreeFeatureConfig> {

    public GiantMushroomFeature() {
        super(TreeFeatureConfig.CODEC);
    }

    @Override
    public boolean place(FeatureContext<TreeFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        ServerWorld world = context.getWorld().toServerWorld();

        world.setBlockState(pos, RefinedMagicBlocks.enchantedStem.getDefaultState());

        return false;
    }
}