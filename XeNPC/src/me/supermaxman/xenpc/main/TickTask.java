package me.supermaxman.xenpc.main;

import me.supermaxman.xenpc.objects.Manager;
import me.supermaxman.xenpc.objects.XeNPCHuman;
import me.supermaxman.xenpc.util.PathUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class TickTask implements Runnable {
    @Override
    public void run() {
        Player[] online = Bukkit.getServer().getOnlinePlayers();
        if (XeNPC.debug) {
            for (Map.Entry<UUID, XeNPCHuman> entry : Manager.npcs.entrySet()) {
                XeNPC.log.info(entry.getKey() + " - " + entry.getValue().toString());
            }
        }

        for (XeNPCHuman npc : Manager.npcs.values()) {
            npc.doTick();
            if (npc.getTarget() == null) {
                for (Player player : online) {
                    if (PathUtil.withinRange(npc.getLocation(), player.getLocation(), 10)) {
                        PathUtil.faceEntity(npc, player);
                        break;
                    }
                }
            }

        }

    }
}