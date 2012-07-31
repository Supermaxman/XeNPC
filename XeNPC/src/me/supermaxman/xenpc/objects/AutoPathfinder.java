package me.supermaxman.xenpc.objects;

import net.minecraft.server.PathEntity;

public interface AutoPathfinder {
	PathEntity find(XeNPCBase npc);
}