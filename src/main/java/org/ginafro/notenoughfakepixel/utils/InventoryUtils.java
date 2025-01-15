package org.ginafro.notenoughfakepixel.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;

public class InventoryUtils {

    public static int getCurrentSlot() {
        return Minecraft.getMinecraft().thePlayer.inventory.currentItem;
    }

    public static void goToSlot(int targetSlot) {
        int currentSlot = Minecraft.getMinecraft().thePlayer.inventory.currentItem;
        if (targetSlot > currentSlot) {
            for (int i = 0; i < targetSlot-currentSlot; i++) {
                Minecraft.getMinecraft().thePlayer.inventory.changeCurrentItem(-1);
            }
        } else {
            for (int i = 0; i < currentSlot-targetSlot; i++) {
                Minecraft.getMinecraft().thePlayer.inventory.changeCurrentItem(1);
            }
        }
    }

    public static ItemStack getHeldItem() {
        return Minecraft.getMinecraft().thePlayer.getHeldItem();
    }

    public static int getSlot(String name) {
        EntityPlayerSP p = Minecraft.getMinecraft().thePlayer;
        for (int i = 0; i < 7; i++) {
            if (p.inventory.mainInventory[i] != null) {
                if (p.inventory.mainInventory[i].getDisplayName().contains(name)) {
                    return i;
                }
            }
        }
        return -1;
    }
}