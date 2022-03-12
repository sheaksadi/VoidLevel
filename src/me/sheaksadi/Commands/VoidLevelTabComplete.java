package me.sheaksadi.Commands;

import me.sheaksadi.VoidLevel;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VoidLevelTabComplete implements TabCompleter {
    VoidLevel plugin;

    public VoidLevelTabComplete(VoidLevel plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> arguments = new ArrayList<>();

        arguments.add("help");
        arguments.add("check");
        for (int i=0; i <=256;i++){
            arguments.add(String.valueOf(i));
        }


        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String argument : arguments) {
                if (argument.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(argument);
                }

            }
            return result;
        }

        List<String> arguments1 = new ArrayList<>(plugin.getConfig().getConfigurationSection("voidLevel").getKeys(false));

        List<String> result1 = new ArrayList<>();
        if (args.length == 2) {
            for (String argument1 : arguments1) {
                if (argument1.toLowerCase().startsWith(args[1].toLowerCase())) {
                    result1.add(argument1);
                }

            }
            return result1;
        }

        return null;
    }
}
