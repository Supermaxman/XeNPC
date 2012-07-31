package me.supermaxman.xenpc.listeners;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.XeNPCBase;
import me.supermaxman.xenpc.objects.XeNPCHuman;
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

                }
            }

        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        net.minecraft.server.Entity mcEntity = ((CraftEntity) event.getEntity()).getHandle();
        if (mcEntity instanceof XeNPCBase && event.isCancelled() != true) {
            event.setCancelled(true);
            XeNPCHuman npc = ((XeNPCBase) mcEntity).getNPC();
            if (npc.isPVP()) {
                npc.damage(event.getDamage());
            }

        }
    }
}
