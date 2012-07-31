package me.supermaxman.xenpc.executors;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.Manager;
import me.supermaxman.xenpc.objects.XeNPCBase;
import me.supermaxman.xenpc.objects.XeNPCHuman;
import net.minecraft.server.ItemInWorldManager;
import net.minecraft.server.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;

public class XeNPCCreateExecutor extends XeNPCBaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String name = args[0];
            final Location loc = player.getLocation();
            synchronized(Manager.npcs){
            
            final XeNPCBase npc =
                    new XeNPCBase(
                            ((CraftServer) plugin.getServer()).getServer(),
                            ((CraftWorld) loc.getWorld()).getHandle(),
                            name,
                            new ItemInWorldManager(((CraftWorld) player.getWorld()).getHandle())
                    );

            
            WorldServer ws = ((CraftWorld) player.getWorld()).getHandle();


            npc.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
            
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    npc.X = loc.getYaw();
                }
            });
            XeNPCHuman npchuman = new XeNPCHuman(npc, Manager.npcs.size()+1, name, player.getName());
            npchuman.setHealth(20);
            ws.addEntity(npchuman.getHandle());
            ws.players.remove(npchuman.getHandle());
            Manager.npcs.put(npchuman.getUID(),npchuman);
            }
        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeNPC]: SYNTAX ERROR, type /cnpc [NPCName].");
            
        }
    }

    public XeNPCCreateExecutor(XeNPC plugin) {
        super(plugin);
    }
}
