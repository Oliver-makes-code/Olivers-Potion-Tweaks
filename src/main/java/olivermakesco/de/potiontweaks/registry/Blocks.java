package olivermakesco.de.potiontweaks.registry;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import olivermakesco.de.potiontweaks.Mod;
import olivermakesco.de.potiontweaks.block.KyriteOreBlock;
import olivermakesco.de.potiontweaks.block.TopazOreBlock;

public class Blocks {
    public static Block kyriteOre = new KyriteOreBlock(
            AbstractBlock.Settings
                    .of(Material.STONE,MapColor.PALE_YELLOW)
                    .sounds(BlockSoundGroup.STONE)
    );
    public static Block topazOre = new TopazOreBlock(
            AbstractBlock.Settings
                    .of(Material.STONE,MapColor.DULL_RED)
                    .sounds(BlockSoundGroup.STONE)
    );

    public static void register() {
        register(kyriteOre, Mod.id("kyrite_ore"));
        register(topazOre, Mod.id("topaz_ore"));
    }

    public static void register(Block block, Identifier id) {
        Registry.register(Registry.BLOCK, id, block);
    }
}
