package me.supermaxman.xenpc.objects;

import net.minecraft.server.*;

public class XeNPCBase extends EntityPlayer {
    protected Entity targetEntity;

    public XeNPCBase(MinecraftServer minecraftserver, World world, String s,
                     ItemInWorldManager iteminworldmanager) {
        super(minecraftserver, world, s, iteminworldmanager);
    }


}