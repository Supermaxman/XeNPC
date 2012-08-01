package me.supermaxman.xenpc.objects;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.util.PathUtil;
import net.minecraft.server.Packet;
import net.minecraft.server.Packet18ArmAnimation;
import net.minecraft.server.WorldServer;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import java.util.UUID;

public class XeNPCHuman {
    private final String name;
    private final java.util.UUID UUID;
    private final XeNPCBase entity;
    private int health;
    private LivingEntity target;
    private boolean isGrounded;
    private boolean hasAttacked = false;
    private boolean pvp = true;
    private boolean isFalling = false;
    private boolean isFollowing = false;
    private int attackDelay = 20;
    private String owner;

    public XeNPCHuman(XeNPCBase entity, String name, String owner) {
        this.name = ChatColor.stripColor(name);
        this.UUID = entity.getBukkitEntity().getUniqueId();
        this.entity = entity;
        this.entity.setNPC(this);
        this.owner = owner;
    }

    public void setFollowing(Boolean bool) {
        this.isFollowing = bool;
    }

    public boolean isFollowing() {
        return this.isFollowing;
    }

    public void setPVP(Boolean bool) {
        this.pvp = bool;
    }

    public boolean isPVP() {
        return this.pvp;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void damage(int d) {
        this.health = this.health - d;
        this.getPlayer().playEffect(EntityEffect.HURT);
        if (this.health <= 0) {
            this.die();
        }
    }

    public void die() {
        this.getPlayer().playEffect(EntityEffect.DEATH);
        WorldServer ws = ((CraftWorld) this.getWorld()).getHandle();
        this.dropInventory();

        ws.removeEntity(this.entity);
        Manager.npcs.remove(UUID);
    }


    public XeNPCBase getHandle() {
        return this.entity;
    }

    public void dropInventory() {
        for (ItemStack i : this.getInventory().getContents()) {
            if (i != null) {
                this.getWorld().dropItem(this.getLocation(), i);
                this.getInventory().remove(i);
            }
        }
    }


    public String getOwner() {
        return this.owner;
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
        return this.entity.getBukkitEntity();
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

    public UUID getUUID() {
        return this.UUID;
    }

    public LivingEntity getTarget() {
        return this.target;
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
        return UUID == other.UUID;
    }


    public void doTick() {
        if (this.target != null) {
            if (this.target.isDead()) {
                this.target = null;
            }
        }
        if (this.isFollowing) {
            followOwner();
        }
        if (this.getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() != Material.AIR && isFalling) {
            this.teleport(this.getLocation().getX(), this.getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getLocation().add(0, 1, 0).getY(), this.getLocation().getZ(), this.getLocation().getYaw(), this.getLocation().getPitch());
            isFalling = false;
        }
        if (this.getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.AIR) {
            this.teleport(this.getLocation().add(0, -0.5, 0));
            isFalling = true;
        }
        if (this.getPlayer().getItemInHand().getType() == Material.BOW) {
            for (Entity e : this.getPlayer().getNearbyEntities(15, 10, 15)) {
                if (e instanceof Monster) {
                    this.target = (LivingEntity) e;
                    PathUtil.faceEntity(this, target);
                    if (attackDelay == 20) {
                        attackDelay = 0;
                        this.attackEntity(((CraftEntity) target).getHandle());
                    } else {
                        attackDelay++;
                    }
                    break;
                }
            }
        } else if (this.getPlayer().getItemInHand().getType() != Material.AIR) {
            for (Entity e : this.getPlayer().getNearbyEntities(5, 5, 5)) {
                if (e instanceof Monster) {
                    this.target = (LivingEntity) e;
                    PathUtil.faceEntity(this, target);
                    if (attackDelay == 20) {
                        attackDelay = 0;
                        this.attackEntity(((CraftEntity) target).getHandle());
                    } else {
                        attackDelay++;
                    }
                    break;
                }
            }
        }


    }

    private void attackEntity(net.minecraft.server.Entity entity) {
        if (this.getItemInHand().getType() != Material.AIR && this.target != null) {
            if (this.getItemInHand().getType() == Material.BOW) {
                ItemStack i = this.getItemInHand();
                PathUtil.faceEntity(this, target);
                if (!i.containsEnchantment(Enchantment.ARROW_INFINITE)) {
                    if (loseItem(Material.ARROW)) {
                        damageItem(i, 1);
                        this.entity.makeInaccuracies();
                        this.getPlayer().launchProjectile(Arrow.class);
                    }
                } else {
                    damageItem(i, 1);
                    this.entity.makeInaccuracies();
                    this.getPlayer().launchProjectile(Arrow.class);
                }
            } else {
                Location location = this.getLocation();
                final World world = location.getWorld();
                for (Player ply : Bukkit.getServer().getOnlinePlayers()) {
                    if (ply == null || ply.equals(this.getPlayer()) || world != ply.getWorld()) {
                        continue;
                    }
                    if (location.distanceSquared(ply.getLocation()) > 100) {
                        continue;
                    }
                    Packet packet = new Packet18ArmAnimation(this.entity, 1);
                    ((CraftPlayer) ply).getHandle().netServerHandler.sendPacket((packet));
                }
                LivingEntity other = target;
                ItemStack i = this.getItemInHand();
                damageItem(i, 1);
                other.damage(this.entity.inventory.a(entity));
            }
            hasAttacked = true;
        }
    }

    private synchronized void followOwner() {
        Player p = XeNPC.plugin.getServer().getPlayerExact(this.owner);
        if (p != null) {
            if (!this.getPlayer().getNearbyEntities(2, 2, 2).contains(p)) {

                Location ploc = p.getLocation();
                Location nloc = this.getLocation();
                Vector pv = ploc.toVector();
                Vector nv = nloc.toVector();
                double x = this.entity.x;
                double y = this.entity.y;
                double z = this.entity.z;
                if (pv.getX() > nv.getX()) {
                    x = x + 0.1;
                } else if (pv.getX() < nv.getX()) {
                    x = x - 0.1;
                }
                if (pv.getZ() > nv.getZ()) {
                    z = z + 0.1;
                } else if (pv.getZ() < nv.getZ()) {
                    z = z - 0.1;
                }
                if (pv.getY() > nv.getY()) {
                    y = y + 1.1;
                }
                this.entity.move(x, y, z);
                if (p.isSprinting()) {
                    this.entity.setSprinting(true);
                } else {
                    this.entity.setSprinting(false);
                }
                if (p.isSneaking()) {
                    this.entity.setSneak(true);
                } else {
                    this.entity.setSneak(false);
                }
            }
        }
    }

    /**
     * Adds damage points to the ItemStack
     *
     * @param itemStack
     * @param amt
     */
    private void damageItem(ItemStack itemStack, int amt) {
        itemStack.setDurability((short) (itemStack.getDurability() + amt));
    }

    /**
     * Drops item if material is contained within the NPCs inventory.
     * Returns true on drop.
     *
     * @param material
     * @return
     */
    private boolean loseItem(Material material) {
        if (this.getInventory().contains(material)) {

            for (ItemStack it : this.getInventory().getContents()) {
                if (it == null) {
                } else if ((it.getType() == material) && (it.getAmount() > 1)) {
                    it.setAmount(it.getAmount() - 1);
                } else if ((it.getType() == material) && (it.getAmount() == 1)) {
                    this.getInventory().removeItem(it);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "XeNPCHuman{" +
                "name='" + name + '\'' +
                ", UUID=" + UUID +
                ", entity=" + entity +
                ", health=" + health +
                ", target=" + target +
                ", isGrounded=" + isGrounded +
                ", hasAttacked=" + hasAttacked +
                ", pvp=" + pvp +
                ", isFalling=" + isFalling +
                ", isFollowing=" + isFollowing +
                ", attackDelay=" + attackDelay +
                ", owner='" + owner + '\'' +
                '}';
    }
}
