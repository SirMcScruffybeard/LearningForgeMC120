package net.mrscruffybeard.mccourse.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.mrscruffybeard.mccourse.MCCourseMod;
import net.mrscruffybeard.mccourse.command.ReturnHomeCommand;
import net.mrscruffybeard.mccourse.command.SetHomeCommand;
import net.mrscruffybeard.mccourse.item.ModItems;

@Mod.EventBusSubscriber(modid = MCCourseMod.MOD_ID)
public class ModEvents {



    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        event.getEntity().getPersistentData().putIntArray(SetHomeCommand.HOME_POS,
                event.getOriginal().getPersistentData().getIntArray(SetHomeCommand.HOME_POS));
    }
    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if(event.getEntity() instanceof Sheep) {
            if(event.getSource().getDirectEntity() instanceof Player player) {
                if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.ALEXANDRITE_AXE.get()) {
                    MCCourseMod.LOGGER.info("Sheep was hit with Alexandrite Axe by " + player.getName().getString());
                }

                if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.DIAMOND) {
                    MCCourseMod.LOGGER.info("Sheep was hit with Diamond by " + player.getName().getString());
                } else {
                    MCCourseMod.LOGGER.info("Sheep was hit by " + player.getName().getString());
                }
            }
        }
    }

}//End Class
