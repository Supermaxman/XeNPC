package me.supermaxman.xenpc.util;

import me.supermaxman.xenpc.objects.XeNPCHuman;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * User: Rainbow
 * Date: 01/08/12
 * Time: 18:03
 */
public class PathUtil {
    public static boolean withinRange(Location loc, Location pLoc, double range) {
        if (loc == null || pLoc == null || loc.getWorld() != pLoc.getWorld()) {
            return false;
        }
        return Math.pow(range, 2) > loc.distanceSquared(pLoc);
    }

    public static void faceEntity(XeNPCHuman npc, Entity entity) {


        if (npc.getWorld() != entity.getWorld()) return;

        Location loc = npc.getLocation(), pl = entity.getLocation();
        double xDiff = pl.getX() - loc.getX();
        double yDiff = pl.getY() - loc.getY();
        double zDiff = pl.getZ() - loc.getZ();
        double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
        double yaw = (Math.acos(xDiff / DistanceXZ) * 180 / Math.PI);
        double pitch = (Math.acos(yDiff / DistanceY) * 180 / Math.PI) - 90;
        if (zDiff < 0.0) {
            yaw = yaw + (Math.abs(180 - yaw) * 2);
        }
        npc.getHandle().yaw = (float) yaw - 90;
        npc.getHandle().X = npc.getHandle().yaw;
        npc.getHandle().pitch = (float) pitch;
    }
}
