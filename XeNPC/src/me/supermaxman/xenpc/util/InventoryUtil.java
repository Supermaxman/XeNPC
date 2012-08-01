package me.supermaxman.xenpc.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * User: Rainbow
 * Date: 01/08/12
 * Time: 18:02
 */
public class InventoryUtil {
    public static boolean isHelmet(ItemStack i) {
        if (i.getType() == Material.LEATHER_HELMET || i.getType() == Material.IRON_HELMET || i.getType() == Material.DIAMOND_HELMET || i.getType() == Material.GOLD_HELMET || i.getType() == Material.CHAINMAIL_HELMET) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isLeggings(ItemStack i) {
        if (i.getType() == Material.LEATHER_LEGGINGS || i.getType() == Material.IRON_LEGGINGS | i.getType() == Material.DIAMOND_LEGGINGS || i.getType() == Material.GOLD_LEGGINGS || i.getType() == Material.CHAINMAIL_LEGGINGS) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isBoots(ItemStack i) {
        if (i.getType() == Material.LEATHER_BOOTS || i.getType() == Material.IRON_BOOTS | i.getType() == Material.DIAMOND_BOOTS || i.getType() == Material.GOLD_BOOTS || i.getType() == Material.CHAINMAIL_BOOTS) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isChestplate(ItemStack i) {
        if (i.getType() == Material.LEATHER_CHESTPLATE || i.getType() == Material.IRON_CHESTPLATE | i.getType() == Material.DIAMOND_CHESTPLATE || i.getType() == Material.GOLD_CHESTPLATE || i.getType() == Material.CHAINMAIL_CHESTPLATE) {
            return true;
        } else {
            return false;
        }

    }
}
