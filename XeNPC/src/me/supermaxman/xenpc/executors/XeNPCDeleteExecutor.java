package me.supermaxman.xenpc.executors;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.Manager;
import me.supermaxman.xenpc.objects.XeNPCHuman;
import net.minecraft.server.WorldServer;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class XeNPCDeleteExecutor extends XeNPCBaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            WorldServer ws = ((CraftWorld) player.getWorld()).getHandle();
            String name = args[0];
            synchronized(Manager.npcs){
            HashMap<Integer,XeNPCHuman> npcs = new HashMap<Integer, XeNPCHuman>();
             npcs.putAll(Manager.npcs);
                for (Map.Entry<Integer, XeNPCHuman> xeNPCHumanEntry : npcs.entrySet()) {
                	XeNPCHuman npc = xeNPCHumanEntry.getValue();
                    if(npc.getName().equalsIgnoreCase(name)){
                        ws.removeEntity(npc.getHandle());
                        Manager.npcs.remove(npc);
                        player.sendMessage(ChatColor.RED + "[XeNPC]: NPC "+name+" has been removed.");
                    }
                }

            }
            
        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeNPC]: SYNTAX ERROR, type /dnpc [NPCName].");
            
        }
    }

    public XeNPCDeleteExecutor(XeNPC plugin) {
        super(plugin);
    }
}
