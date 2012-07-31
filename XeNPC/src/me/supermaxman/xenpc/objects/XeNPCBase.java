package me.supermaxman.xenpc.objects;

import java.io.IOException;

import net.minecraft.server.*;

public class XeNPCBase extends EntityPlayer {
	private XeNPCHuman human;
    public XeNPCBase(MinecraftServer minecraftserver, World world, String s, ItemInWorldManager iteminworldmanager) {
        super(minecraftserver, world, s, iteminworldmanager);
        iteminworldmanager.setGameMode(0);

        XeNPCSocket socket = new XeNPCSocket();
        NetworkManager netMgr = new XeNPCNetworkManager(socket, "xez mgr", new NetHandler() {
            @Override
            public boolean c() {
                return false;
            }
        });
        this.netServerHandler = new XeNPCNetHandler(minecraftserver, this, netMgr);
        netMgr.a(this.netServerHandler);

        try {
            socket.close();
        } catch (IOException ex) {
        }
    }

    public XeNPCHuman getNPC(){
    	return human;
    }
    public void setNPC(XeNPCHuman human){
    	this.human = human;
    }
}