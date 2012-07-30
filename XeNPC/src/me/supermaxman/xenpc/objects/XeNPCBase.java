package me.supermaxman.xenpc.objects;

import net.minecraft.server.Entity;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemInWorldManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.World;

public class XeNPCBase extends EntityPlayer {
	protected Entity targetEntity;
    
    public XeNPCBase(MinecraftServer minecraftserver, World world, String s,
			ItemInWorldManager iteminworldmanager) {
		super(minecraftserver, world, s, iteminworldmanager);
	}
    

    

   
    
    
    
}