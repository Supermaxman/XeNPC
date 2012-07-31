package me.supermaxman.xenpc.executors;

import me.supermaxman.xenpc.main.XeNPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

abstract class XeNPCBaseExecutor implements CommandExecutor {
    protected static XeNPC plugin;
    // Permission permission = xEssentials.permission;

    XeNPCBaseExecutor(XeNPC plugin) {
        XeNPCBaseExecutor.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        final String commandName = command.getName().toLowerCase();
        Player player;
        final boolean isPlayer = (sender instanceof Player);

        if (isPlayer) {
            player = (Player) sender;
        } else {
            return false;
        }

        this.run(player, args);

        return true;
    }

    protected abstract void run(Player player, String[] args);

}