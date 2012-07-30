package me.supermaxman.xenpc.listeners;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.XeNPCBasic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import net.minecraft.server.ItemInWorldManager;
import net.minecraft.server.WorldServer;
import org.bukkit.craftbukkit.CraftWorld;

import org.bukkit.craftbukkit.CraftServer;

public class XeNPCListener implements Listener {
    final XeNPC plugin;

    public XeNPCListener(XeNPC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Villager) {
            final XeNPCBasic z = new XeNPCBasic(((CraftServer) plugin.getServer()).getServer(), ((CraftWorld) event.getEntity().getWorld()).getHandle(), "Xandvia", new ItemInWorldManager(((CraftWorld) event.getEntity().getWorld()).getHandle()));
            z.setLocation(event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 50, 50);
            WorldServer ws = ((CraftWorld) event.getEntity().getWorld()).getHandle();
            final Location loc = event.getEntity().getLocation();
            z.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    z.X = loc.getYaw();
                }
            });

            ws.addEntity(z);
            ws.players.remove(z);
            //z.setSneak(true);

            event.setCancelled(true);

        }


    }


}
