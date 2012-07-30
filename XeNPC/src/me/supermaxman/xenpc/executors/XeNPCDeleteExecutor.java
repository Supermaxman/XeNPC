package me.supermaxman.xenpc.executors;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.XeNPCBasic;
import net.minecraft.server.WorldServer;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;

public class XeNPCDeleteExecutor extends XeNPCBaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            synchronized(XeNPC.npcs){

                String name = args[0];
            for(XeNPCBasic npcBasic : XeNPC.npcs){
            	if(npcBasic.name.equalsIgnoreCase(name)){
                    WorldServer ws = ((CraftWorld) player.getWorld()).getHandle();
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
