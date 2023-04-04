package ua.kotarych.kabibank.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ua.kotarych.kabibank.config.Config;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.config.Message;

import java.util.List;

public class KabibankCommand implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            if (sender.hasPermission("Kabibank.reload")) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Message.reload();
                    Config.reload();
                    MaterialGui.reload();

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.getReloadMessage()));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.getErrorPermission()));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1){
            return List.of("reload");
        }
        return null;
    }
}
