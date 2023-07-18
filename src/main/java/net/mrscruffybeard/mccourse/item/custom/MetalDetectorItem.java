package net.mrscruffybeard.mccourse.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscruffybeard.mccourse.item.ModItems;
import net.mrscruffybeard.mccourse.util.InventoryUtil;
import net.mrscruffybeard.mccourse.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {

    public static final String FOUND_ORE_KEY = "mccourse.found_ore";
    private String comma = " ,";

    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i<= positionClicked.getY() + 64; i++) {
                BlockState blockState = pContext.getLevel().getBlockState(positionClicked.below(i));

                if (isValuableBlock(blockState)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundBlock = true;

                    if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET.get())){
                        addDataToDataTablet(player, positionClicked.below(i), blockState.getBlock());

                    }

                    break;
                }// if isValuableBlock
            }// for loop

            if(!foundBlock) {
                outPutNoValuableFound(player);
            }// if foundBlock is false
        }// if server side

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player. broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }//End useOn

    private void addDataToDataTablet(Player player, BlockPos below, Block block) {
        ItemStack dataTablet = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));

        CompoundTag data = new CompoundTag();
        data.putString(FOUND_ORE_KEY, "Valuable Found:  " + I18n.get(block.getDescriptionId())
                + " at (" + below.getX() + comma + below.getY() + comma + below.getZ() + ")");

        dataTablet.setTag(data);
    }

    private void outPutNoValuableFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.mccourse.metal_detector.no_valuables"));

    }//outPutNoValuableFound

    private void outputValuableCoordinates(BlockPos below, Player player, Block block) {
        String comma = " ,";
        player.sendSystemMessage(Component.literal("Valuable Found:  " + I18n.get(block.getDescriptionId())
                + " at (" + below.getX() + comma + below.getY() + comma + below.getZ() + ")"));
    }//End outputValuableCoordinates

    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }//End isValuableBlock

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tool.tip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tool.tip"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}//End MetalDetectorItem Class
