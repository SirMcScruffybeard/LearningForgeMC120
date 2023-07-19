package net.mrscruffybeard.mccourse.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscruffybeard.mccourse.MCCourseMod;
import net.mrscruffybeard.mccourse.block.ModBlocks;
import net.mrscruffybeard.mccourse.block.custom.KohlrabiCropBlock;
import net.mrscruffybeard.mccourse.item.custom.DataTablet;
import net.mrscruffybeard.mccourse.item.custom.FuelItem;
import net.mrscruffybeard.mccourse.item.custom.MetalDetectorItem;

public class ModItems {

    private static final String ALEXANDRITE_NAME= "alexandrite";

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCCourseMod.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register(ALEXANDRITE_NAME,
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_" + ALEXANDRITE_NAME,
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(512)));

    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI)));

    public static final RegistryObject<Item> PEAT_BRICK = ITEMS.register("peat_brick",
            () -> new FuelItem(new Item.Properties(), 200));

    public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register(ALEXANDRITE_NAME + "_sword",
            () -> new SwordItem(ModToolTiers.ALEXANDRITE, 2, 3,
                    new Item.Properties().durability(256)));

    public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register(ALEXANDRITE_NAME + "_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ALEXANDRITE, 1, 3,
                    new Item.Properties().durability(256)));

    public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register(ALEXANDRITE_NAME + "_axe",
            () -> new AxeItem(ModToolTiers.ALEXANDRITE, 1, 3,
                    new Item.Properties().durability(256)));

    public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register(ALEXANDRITE_NAME + "_shovel",
            () -> new ShovelItem(ModToolTiers.ALEXANDRITE, 2, 2,
                    new Item.Properties().durability(256)));

    public static final RegistryObject<Item> ALEXANDRITE_HOE = ITEMS.register(ALEXANDRITE_NAME + "_hoe",
            () -> new HoeItem(ModToolTiers.ALEXANDRITE, 1, 3,
                    new Item.Properties().durability(256)));

    public static final RegistryObject<Item> DATA_TABLET = ITEMS.register("data_tablet",
            () -> new DataTablet(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> KOHLRABI_SEEDS = ITEMS.register(KohlrabiCropBlock.SEED_PATH_NAME,
            () -> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(), new Item.Properties()));
    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
