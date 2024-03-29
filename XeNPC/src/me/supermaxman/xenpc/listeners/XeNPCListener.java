package me.supermaxman.xenpc.listeners;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.XeNPCBase;
import me.supermaxman.xenpc.objects.XeNPCHuman;
import me.supermaxman.xenpc.util.InventoryUtil;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class XeNPCListener implements Listener {
    final XeNPC plugin;

    public XeNPCListener(XeNPC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        net.minecraft.server.Entity mcEntity = ((CraftEntity) event.getRightClicked()).getHandle();
        if (mcEntity instanceof XeNPCBase) {
            XeNPCHuman npc = ((XeNPCBase) mcEntity).getNPC();
            Player p = event.getPlayer();
            if (npc.getOwner().equalsIgnoreCase(p.getName())) {
                if (p.getItemInHand().getType() == Material.AIR) {
                    p.openInventory(((CraftPlayer) event.getRightClicked()).getInventory());
                } else {
                    if (InventoryUtil.isBoots(p.getItemInHand())) {
                        ((CraftPlayer) event.getRightClicked()).getInventory().setBoots(p.getItemInHand());
                    } else if (InventoryUtil.isHelmet(p.getItemInHand())) {
                        ((CraftPlayer) event.getRightClicked()).getInventory().setHelmet(p.getItemInHand());
                    } else if (InventoryUtil.isChestplate(p.getItemInHand())) {
                        ((CraftPlayer) event.getRightClicked()).getInventory().setChestplate(p.getItemInHand());
                    } else if (InventoryUtil.isLeggings(p.getItemInHand())) {
                        ((CraftPlayer) event.getRightClicked()).getInventory().setLeggings(p.getItemInHand());
                    } else {
                        if (npc.isFollowing()) {
                            npc.setFollowing(false);
                        } else {
                            npc.setFollowing(true);
                        }
                    }
                }
            }

        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        net.minecraft.server.Entity mcEntity = ((CraftEntity) event.getEntity()).getHandle();
        if (mcEntity instanceof XeNPCBase && !event.isCancelled()) {
            event.setCancelled(true);
            XeNPCHuman npc = ((XeNPCBase) mcEntity).getNPC();
            if (npc.isPVP()) {
                npc.damage(event.getDamage());
            }

        }
    }
}
