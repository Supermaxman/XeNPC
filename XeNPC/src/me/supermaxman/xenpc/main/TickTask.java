package me.supermaxman.xenpc.main;

import me.supermaxman.xenpc.objects.Manager;
import me.supermaxman.xenpc.objects.XeNPCHuman;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class TickTask implements Runnable {
    @Override
    public void run() {
        Player[] online = Bukkit.getServer().getOnlinePlayers();
        if (XeNPC.debug) {
            for (Map.Entry<UUID, XeNPCHuman> entry : Manager.npcs.entrySet()) {
                XeNPC.log.info(entry.getKey() + " - " + entry.getValue().toString());
            }
        }

        for (XeNPCHuman npc : Manager.npcs.values()) {
            npc.doTick();
            if (npc.getTarget() == null) {
                for (Player player : online) {
                    if (withinRange(npc.getLocation(), player.getLocation(), 10)) {
                        faceEntity(npc, player);
                        break;
                    }
                }
            }

        }

    }

    public static boolean withinRange(Location loc, Location pLoc, double range) {
        if (loc == null || pLoc == null || loc.getWorld() != pLoc.getWorld()) {
            return false;
        }
        return Math.pow(range, 2) > loc.distanceSquared(pLoc);
    }

    public static void faceEntity(XeNPCHuman npc, Entity entity) {

    	
    	if (npc.getWorld() != entity.getWorld()) return;

        Location loc = npc.getLocation(), pl = entity.getLocation();
        double xDiff = pl.getX() - loc.getX();
        double yDiff = pl.getY() - loc.getY();
        double zDiff = pl.getZ() - loc.getZ();
        double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
        double yaw = (Math.acos(xDiff / DistanceXZ) * 180 / Math.PI);
        double pitch = (Math.acos(yDiff / DistanceY) * 180 / Math.PI) - 90;
        if (zDiff < 0.0) {
            yaw = yaw + (Math.abs(180 - yaw) * 2);
        }
        npc.getHandle().yaw = (float) yaw - 90;
        npc.getHandle().X = npc.getHandle().yaw;
        npc.getHandle().pitch = (float) pitch;
    }


    public static boolean isHelmet(ItemStack i){
    	if(i.getType()==Material.LEATHER_HELMET||i.getType()==Material.IRON_HELMET||i.getType()==Material.DIAMOND_HELMET||i.getType()==Material.GOLD_HELMET||i.getType()==Material.CHAINMAIL_HELMET){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    public static boolean isLeggings(ItemStack i){
    	if(i.getType()==Material.LEATHER_LEGGINGS||i.getType()==Material.IRON_LEGGINGS|i.getType()==Material.DIAMOND_LEGGINGS||i.getType()==Material.GOLD_LEGGINGS||i.getType()==Material.CHAINMAIL_LEGGINGS){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    public static boolean isBoots(ItemStack i){
    	if(i.getType()==Material.LEATHER_BOOTS||i.getType()==Material.IRON_BOOTS|i.getType()==Material.DIAMOND_BOOTS||i.getType()==Material.GOLD_BOOTS||i.getType()==Material.CHAINMAIL_BOOTS){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    public static boolean isChestplate(ItemStack i){
    	if(i.getType()==Material.LEATHER_CHESTPLATE||i.getType()==Material.IRON_CHESTPLATE|i.getType()==Material.DIAMOND_CHESTPLATE||i.getType()==Material.GOLD_CHESTPLATE||i.getType()==Material.CHAINMAIL_CHESTPLATE){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
}