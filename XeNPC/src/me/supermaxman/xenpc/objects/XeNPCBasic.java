package me.supermaxman.xenpc.objects;

import net.minecraft.server.*;

import java.io.IOException;

public class XeNPCBasic extends XeNPCBase {
    public XeNPCBasic(MinecraftServer minecraftserver, World world, String s, ItemInWorldManager iteminworldmanager) {
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

    /**
     * Attacks entity
     *
     * @param entity
     */
    public void attackEntity(Entity entity){
        attack(entity);
    }

}