package me.supermaxman.xenpc.listeners;

import me.supermaxman.xenpc.main.XeNPC;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

public class XeNPCListener implements Listener {
    final XeNPC plugin;

    public XeNPCListener(XeNPC plugin) {
        this.plugin = plugin;
    }
    BukkitScheduler scheduler = Bukkit.getScheduler();
    
    
}
