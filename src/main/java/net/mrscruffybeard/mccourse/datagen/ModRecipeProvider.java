package net.mrscruffybeard.mccourse.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.mrscruffybeard.mccourse.MCCourseMod;
import net.mrscruffybeard.mccourse.block.ModBlocks;
import net.mrscruffybeard.mccourse.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALEXANDRITE_SMELTABLE = List.of(ModItems.RAW_ALEXANDRITE.get(),
            ModBlocks.ALEXANDRITE_ORE.get(), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
            ModBlocks.END_STONE_ALEXANDRITE_ORE.get(), ModBlocks.NETHER_ALEXANDRITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALEXANDRITE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite_block", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                "mccourse:raw_alexandrite", "alexandrite","mccourse:raw_alexandrite_block", "alexandrite");

        oreSmelting(pWriter,ALEXANDRITE_SMELTABLE, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 200, "alexandrite");
        oreBlasting(pWriter,ALEXANDRITE_SMELTABLE, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 100, "alexandrite");

        stairBuilder(pWriter, RecipeCategory.MISC, ModBlocks.ALEXANDRITE_STAIRS.get(), ModBlocks.ALEXANDRITE_BLOCK.get());
        stairBuilder(pWriter, RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_STAIRS.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

        slabBuilder(pWriter, RecipeCategory.MISC, ModBlocks.ALEXANDRITE_SLAB.get(), ModBlocks.ALEXANDRITE_BLOCK.get());
        slabBuilder(pWriter, RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_SLAB.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

        pressurePlateBuilder(pWriter, RecipeCategory.MISC, ModBlocks.ALEXANDRITE_PESSURE_PLATE.get(), ModBlocks.ALEXANDRITE_BLOCK.get());

        buttonBuilder(pWriter,RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BUTTON.get(), ModItems.ALEXANDRITE.get());
    }

    private String hasUnlock(Block block) {
        return "has_" + block.getName();
    }
    private String hasUnlock(Item item) {
        return "has_" + item.getDescriptionId();
    }

    protected void buttonBuilder(Consumer<FinishedRecipe> pWriter,RecipeCategory recipeCategory, Block result, Block ingredient) {
        ShapelessRecipeBuilder.shapeless(recipeCategory, result)
                .requires(ingredient)
                .unlockedBy(this.hasUnlock(ingredient), inventoryTrigger(ItemPredicate.Builder.item().of(ingredient).build()))
                .save(pWriter);
    }
    protected void buttonBuilder(Consumer<FinishedRecipe> pWriter,RecipeCategory recipeCategory, Block result, Item ingredient) {
                ShapelessRecipeBuilder.shapeless(recipeCategory, result)
                .requires(ingredient)
                .unlockedBy(this.hasUnlock(ingredient), inventoryTrigger(ItemPredicate.Builder.item().of(ingredient).build()))
                .save(pWriter);
    }
    protected void pressurePlateBuilder(Consumer<FinishedRecipe> pWriter,RecipeCategory recipeCategory, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(recipeCategory, result)
                .pattern("AA")
                .define('A', ingredient)
                .unlockedBy(this.hasUnlock(ingredient), inventoryTrigger(ItemPredicate.Builder.item().of(ingredient).build()))
                .save(pWriter);
    }

    protected void stairBuilder(Consumer<FinishedRecipe> pWriter, RecipeCategory recipeCategory, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(recipeCategory, result)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', ingredient)
                .unlockedBy(this.hasUnlock(ingredient), inventoryTrigger(ItemPredicate.Builder.item().of(ingredient).build()))
                .save(pWriter);
    }

    protected void slabBuilder(Consumer<FinishedRecipe> pWriter,RecipeCategory recipeCategory, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(recipeCategory, result)
                .pattern("AAA")
                .define('A', ingredient)
                .unlockedBy(this.hasUnlock(ingredient), inventoryTrigger(ItemPredicate.Builder.item().of(ingredient).build()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                      ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience,
                pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                      ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                            pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, MCCourseMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

