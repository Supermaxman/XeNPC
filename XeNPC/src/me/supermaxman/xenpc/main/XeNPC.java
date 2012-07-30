package me.supermaxman.xenpc.main;

import me.supermaxman.xenpc.executors.XeNPCCreateExecutor;
import me.supermaxman.xenpc.executors.XeNPCDeleteExecutor;
import me.supermaxman.xenpc.listeners.XeNPCListener;
import me.supermaxman.xenpc.objects.XeNPCBasic;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

public class XeNPC extends JavaPlugin {

    //Required
    public static Logger log;
    public static FileConfiguration conf;

    private final XeNPCListener Listener = new XeNPCListener(this);
    public static ArrayList<XeNPCBasic> npcs = new ArrayList<XeNPCBasic>();
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
        getCommand("cnpc").setExecutor(new XeNPCCreateExecutor(this));
        getCommand("createnpc").setExecutor(new XeNPCCreateExecutor(this));
        getCommand("deletenpc").setExecutor(new XeNPCDeleteExecutor(this));
        getCommand("dnpc").setExecutor(new XeNPCDeleteExecutor(this));

    }


}