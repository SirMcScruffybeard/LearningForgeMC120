package net.mrscruffybeard.mccourse.block.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.mrscruffybeard.mccourse.item.ModItems;

public class KohlrabiCropBlock extends ModCropBlock {
    public static final String KHOLRABI = "kohlrabi";
    public static final String PATH_NAME = KHOLRABI + "_crop";
    public static final String SEED_PATH_NAME = KHOLRABI + "_seeds";

    public static final float KHOLRABI_COMPOST_CHANCE = 0.35f;
    public static final float KHOLRABI_SEED_COMPOST_CHANCE = 0.20f;

    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 6;
     public static final IntegerProperty AGE = IntegerProperty.create("age", MIN_AGE, MAX_AGE);

    public KohlrabiCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.KOHLRABI_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
