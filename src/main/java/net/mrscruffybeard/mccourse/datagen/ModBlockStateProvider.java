package net.mrscruffybeard.mccourse.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscruffybeard.mccourse.MCCourseMod;
import net.mrscruffybeard.mccourse.block.ModBlocks;
import net.mrscruffybeard.mccourse.block.custom.AlexandriteLampBlock;
import net.mrscruffybeard.mccourse.block.custom.KohlrabiCropBlock;
import net.mrscruffybeard.mccourse.block.custom.ModCropBlock;

import java.util.function.Function;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MCCourseMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);

        blockWithItem(ModBlocks.ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.END_STONE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.NETHER_ALEXANDRITE_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock((StairBlock) ModBlocks.ALEXANDRITE_STAIRS.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        stairsBlock((StairBlock) ModBlocks.RAW_ALEXANDRITE_STAIRS.get(), blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()));


        slabBlock((SlabBlock) ModBlocks.ALEXANDRITE_SLAB.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()),
                blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        slabBlock((SlabBlock) ModBlocks.RAW_ALEXANDRITE_SLAB.get(), blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()),
                blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()));


        buttonBlock((ButtonBlock) ModBlocks.ALEXANDRITE_BUTTON.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        pressurePlateBlock((PressurePlateBlock) ModBlocks.ALEXANDRITE_PESSURE_PLATE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        fenceBlock((FenceBlock) ModBlocks.ALEXANDRITE_FENCE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.ALEXANDRITE_FENCE_GATE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
        wallBlock((WallBlock) ModBlocks.ALEXANDRITE_WALL.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        doorBlockWithRenderType((DoorBlock) ModBlocks.ALEXANDRITE_DOOR.get(),
                modLoc("block/alexandrite_door_bottom"),modLoc("block/alexandrite_door_top"), "cutout");

        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.ALEXANDRITE_TRAP_DOOR.get(), modLoc("block/alexandrite_trapdoor"), true, "cutoute");

        blockItem(ModBlocks.ALEXANDRITE_STAIRS);
        blockItem(ModBlocks.ALEXANDRITE_SLAB);
        blockItem(ModBlocks.RAW_ALEXANDRITE_STAIRS);
        blockItem(ModBlocks.RAW_ALEXANDRITE_SLAB);
        blockItem(ModBlocks.ALEXANDRITE_PESSURE_PLATE);
        blockItem(ModBlocks.ALEXANDRITE_FENCE_GATE);
        blockItem(ModBlocks.ALEXANDRITE_TRAP_DOOR, "_bottom");

        customLamp();

        makeCrop(((ModCropBlock)ModBlocks.KOHLRABI_CROP.get()), KohlrabiCropBlock.KHOLRABI + "_stage", KohlrabiCropBlock.KHOLRABI + "_stage");

        horizontalBlock(ModBlocks.GEM_EMPOWERING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_empowering_station")));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((ModCropBlock) block).getAgeProperty()),
                new ResourceLocation(MCCourseMod.MOD_ID, "block/" + textureName +
                        state.getValue(((ModCropBlock) block).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.ALEXANDRITE_LAMP.get()).forAllStates(state -> {
            if(state.getValue(AlexandriteLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("alexandrite_lamp_on",
                        new ResourceLocation(MCCourseMod.MOD_ID, "block/" + "alexandrite_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("alexandrite_lamp_off",
                        new ResourceLocation(MCCourseMod.MOD_ID, "block/" + "alexandrite_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.ALEXANDRITE_LAMP.get(), models().cubeAll("alexandrite_lamp_on",
                new ResourceLocation(MCCourseMod.MOD_ID, "block/" + "alexandrite_lamp_on")));
    }
    private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(),
                new ModelFile.UncheckedModelFile("mccourse:block/"
                        + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }

    public void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(),
                new ModelFile.UncheckedModelFile("mccourse:block/"
                        + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}