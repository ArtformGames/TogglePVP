package com.artformgames.plugin.togglepvp.command;

import com.artformgames.plugin.togglepvp.handler.PVPHandler;
import com.artformgames.plugin.togglepvp.conf.PluginMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TogglePVPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }

        boolean current = PVPHandler.isPVPEnabled(player);
        PVPHandler.setPVPStatus(player, !current);
        if (!current) {
            PluginMessages.ENABLED.send(player);
        } else {
            PluginMessages.DISABLED.send(player);
        }

        return true;
    }

}
