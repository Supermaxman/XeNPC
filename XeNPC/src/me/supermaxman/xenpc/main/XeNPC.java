package me.supermaxman.xenpc.main;

import me.supermaxman.xenpc.listeners.XeNPCListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class XeNPC extends JavaPlugin {

    //Required
    public static Logger log;
    public static FileConfiguration conf;

    private final XeNPCListener Listener = new XeNPCListener(this);

    public static XeNPC plugin;

    @Override
    public void onDisable() {
        log.info("Disabled.");
    }

    @Override
    public void onEnable() {
        plugin = this;
        log = getLogger();
        conf = getConfig();

        getServer().getPluginManager().registerEvents(Listener, this);
        log.info("Enabled! Version:" + this.getDescription().getVersion());


    }


}