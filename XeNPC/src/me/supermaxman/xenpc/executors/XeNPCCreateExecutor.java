package me.supermaxman.xenpc.executors;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.XeNPCBasic;
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
            synchronized(XeNPC.npcs){
            for(XeNPCBasic npcBasic : XeNPC.npcs){
            	if(npcBasic.name.equalsIgnoreCase(name)){
                    WorldServer ws = ((CraftWorld) player.getWorld()).getHandle();
                    ws.removeEntity(npcBasic);
                    XeNPC.npcs.remove(npcBasic);
            	}
            }

            final XeNPCBasic npcBasic =
                    new XeNPCBasic(
                            ((CraftServer) plugin.getServer()).getServer(),
                            ((CraftWorld) loc.getWorld()).getHandle(),
                            name,
                            new ItemInWorldManager(((CraftWorld) player.getWorld()).getHandle())
                    );

            
            WorldServer ws = ((CraftWorld) player.getWorld()).getHandle();


            npcBasic.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
            
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    npcBasic.X = loc.getYaw();
                }
            });

            ws.addEntity(npcBasic);
            ws.players.remove(npcBasic);
            XeNPC.npcs.add(npcBasic);
            }
        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeNPC]: SYNTAX ERROR, type /cnpc [NPCName].");
            
        }
    }

    public XeNPCCreateExecutor(XeNPC plugin) {
        super(plugin);
    }
}
