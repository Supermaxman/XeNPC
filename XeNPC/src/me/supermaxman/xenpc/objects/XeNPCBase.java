package me.supermaxman.xenpc.objects;

import java.io.IOException;

import org.bukkit.entity.Arrow;

import net.minecraft.server.*;

public class XeNPCBase extends EntityPlayer {
	private XeNPCHuman human;
	private static final double JUMP_FACTOR = 0.07D;
	private float pathingRange = 16;
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
    
    PathEntity createPathEntity(int x, int y, int z) {
        return this.world.a(this, x, y, z, pathingRange, true, false, false, true);
    }
    
    protected void makeInaccuracies() {
        boolean up = this.random.nextBoolean();
        this.yaw += this.random.nextInt(5) * (up ? 1 : -1);
        up = this.random.nextBoolean();
        this.pitch += this.random.nextInt(5) * (up ? 1 : -1);
    }
}