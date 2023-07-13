package net.mrscruffybeard.mccourse.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrscruffybeard.mccourse.MCCourseMod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {


    public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                            CompletableFuture<TagLookup<Block>> completableFuture, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //Add Tags
        //Works like Block Tags
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
