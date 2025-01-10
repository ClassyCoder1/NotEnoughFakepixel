package org.ginafro.notenoughfakepixel.features.skyblock.dungeons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.ginafro.notenoughfakepixel.Configuration;
import org.ginafro.notenoughfakepixel.utils.ScoreboardUtils;

public class AutoCloseChests {

    private static final String TARGET_CHEST_NAME = "Secret Chest"; // Replace with your desired chest name

    @SubscribeEvent
    public void onGuiBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        if (!Configuration.autoCloseChests) return; // Check if the feature is enabled
        if (!ScoreboardUtils.currentLocation.isDungeon()) return; // Check if the player is in a dungeon
        if (event.gui == null) return;

        // Check if the GUI is a chest
        if (event.gui instanceof GuiChest) {
            GuiChest chestGui = (GuiChest) event.gui;
            ContainerChest chestContainer = (ContainerChest) chestGui.inventorySlots;

            // Get the name of the chest
            String chestName = chestContainer.getLowerChestInventory().getDisplayName().getUnformattedText();
            System.out.println(chestName);
            System.out.println(TARGET_CHEST_NAME.equals(chestName));

            // Check if the chest name matches the target and cancel the event
            if (TARGET_CHEST_NAME.equals(chestName)) {


                //event.setCanceled(true); // Prevent the chest GUI from opening
                Minecraft.getMinecraft().thePlayer.closeScreen(); // Close the chest
            }
        }
    }

}