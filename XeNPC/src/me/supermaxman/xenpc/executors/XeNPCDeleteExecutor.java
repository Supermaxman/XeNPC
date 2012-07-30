package me.supermaxman.xenpc.executors;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.XeNPCBasic;
import net.minecraft.server.WorldServer;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class XeNPCDeleteExecutor extends XeNPCBaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            WorldServer ws = ((CraftWorld) player.getWorld()).getHandle();
            String name = args[0];
            synchronized(XeNPC.npcs){
            List<XeNPCBasic> npcs = new ArrayList<XeNPCBasic>();
             npcs.addAll(XeNPC.npcs);
            for(XeNPCBasic npcBasic : npcs){
            	if(npcBasic.name.equalsIgnoreCase(name)){
                    ws.removeEntity(npcBasic);
                    XeNPC.npcs.remove(npcBasic);
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
