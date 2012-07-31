package me.supermaxman.xenpc.objects;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class XeNPCHuman {
	private final String name;
	private final int UID;
	private final XeNPCBase entity;
	
	public XeNPCHuman(XeNPCBase entity, int UID, String name) {
		this.name = ChatColor.stripColor(name);
	    this.UID = UID;
	    this.entity = entity;
	}
	    

	public XeNPCBase getHandle() {
	    return this.entity;
	}
	    
	public PlayerInventory getInventory() {
		return this.getPlayer().getInventory();
	}

    public ItemStack getItemInHand() {
        return this.getPlayer().getItemInHand();
    }

    public Location getLocation() {
        return this.getPlayer().getLocation();
    }
    
    public Player getPlayer() {
        return (Player) this.entity.getBukkitEntity();
    }
    
    public World getWorld() {
        return this.getPlayer().getWorld();
    }
    

    public void setItemInHand(ItemStack item) {
        this.getPlayer().setItemInHand(item);
    }
    
    public void teleport(double x, double y, double z, float yaw, float pitch) {
        this.entity.setLocation(x, y, z, yaw, pitch);
    }
    
    public void teleport(Location loc) {
        boolean multiworld = loc.getWorld() != this.getWorld();
        this.getPlayer().teleport(loc);
        if (multiworld) {
            ((CraftServer) Bukkit.getServer()).getHandle().players
                    .remove(this.entity);
        }
    }
    
	
	public String getName() {
		return this.name;
	}

	public int getUID() {
		return this.UID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		XeNPCHuman other = (XeNPCHuman) obj;
		return UID == other.UID;
	}
}