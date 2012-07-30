package me.supermaxman.xenpc.objects;


import net.minecraft.server.*;

public class XeNPCNetHandler extends NetServerHandler {
    public XeNPCNetHandler(MinecraftServer minecraftserver, EntityPlayer entityplayer, NetworkManager netMgr) {
        super(minecraftserver, netMgr, entityplayer);
    }

    @Override
    public void a() {
    }

    @Override
    public void sendMessage(String s) {
    }

    @Override
    public void a(String s, Object[] aobject) {
    }

    @Override
    public void sendPacket(Packet packet) {
    }

    @Override
    public void onUnhandledPacket(Packet packet) {
    }

    @Override
    public void a(Packet3Chat packet3chat) {
    }

    @Override
    public void a(Packet10Flying packet10flying) {
    }

    @Override
    public void a(Packet14BlockDig packet14blockdig) {
    }

    @Override
    public void a(Packet15Place packet15place) {
    }

    @Override
    public void a(Packet16BlockItemSwitch packet16blockitemswitch) {
    }

    @Override
    public void a(Packet28EntityVelocity packet28entityvelocity) {
    }

    @Override
    public void a(Packet51MapChunk packet50mapchunk) {
    }

    @Override
    public void a(Packet102WindowClick packet102windowclick) {
    }

    @Override
    public void a(Packet106Transaction packet106transaction) {
    }

    @Override
    public void a(Packet255KickDisconnect packet255kickdisconnect) {
    }

    @Override
    public void a(Packet130UpdateSign packet130updatesign) {
    }
}