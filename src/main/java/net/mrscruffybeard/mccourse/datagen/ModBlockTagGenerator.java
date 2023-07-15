package net.mrscruffybeard.mccourse.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.mrscruffybeard.mccourse.MCCourseMod;
import net.mrscruffybeard.mccourse.block.ModBlocks;
import net.mrscruffybeard.mccourse.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).add(ModBlocks.ALEXANDRITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get())
                .add(ModBlocks.END_STONE_ALEXANDRITE_ORE.get())
                .add(ModBlocks.NETHER_ALEXANDRITE_ORE.get())
                .addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_AXE);

        this.tag(BlockTags.MINEABLE_WITH_HOE);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.ALEXANDRITE_BLOCK.get(),
                        ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                        ModBlocks.ALEXANDRITE_ORE.get(),
                        ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
                        ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
                        ModBlocks.NETHER_ALEXANDRITE_ORE.get(),

                        ModBlocks.ALEXANDRITE_STAIRS.get(),
                        ModBlocks.RAW_ALEXANDRITE_STAIRS.get(),

                        ModBlocks.ALEXANDRITE_SLAB.get(),
                        ModBlocks.RAW_ALEXANDRITE_SLAB.get(),

                        ModBlocks.SOUND_BLOCK.get(),

                        ModBlocks.ALEXANDRITE_BUTTON.get(),
                        ModBlocks.ALEXANDRITE_PESSURE_PLATE.get(),

                        ModBlocks.ALEXANDRITE_FENCE.get(),
                        ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                        ModBlocks.ALEXANDRITE_WALL.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL);

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(
                        ModBlocks.SOUND_BLOCK.get(),

                        ModBlocks.ALEXANDRITE_BUTTON.get(),
                        ModBlocks.ALEXANDRITE_PESSURE_PLATE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.ALEXANDRITE_BLOCK.get(),
                        ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                        ModBlocks.ALEXANDRITE_ORE.get(),
                        ModBlocks.NETHER_ALEXANDRITE_ORE.get(),

                        ModBlocks.ALEXANDRITE_STAIRS.get(),
                        ModBlocks.RAW_ALEXANDRITE_STAIRS.get(),

                        ModBlocks.ALEXANDRITE_SLAB.get(),
                        ModBlocks.RAW_ALEXANDRITE_SLAB.get(),

                        ModBlocks.ALEXANDRITE_FENCE.get(),
                        ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                        ModBlocks.ALEXANDRITE_WALL.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(
                        ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
                        ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
                        ModBlocks.NETHER_ALEXANDRITE_ORE.get());

        this.tag(BlockTags.FENCES)
                .add(
                  ModBlocks.ALEXANDRITE_FENCE.get()
                );

        this.tag(BlockTags.FENCE_GATES)
                .add(
                        ModBlocks.ALEXANDRITE_FENCE_GATE.get()
                );

        this.tag(BlockTags.WALLS)
                .add(
                        ModBlocks.ALEXANDRITE_WALL.get()
                );
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
