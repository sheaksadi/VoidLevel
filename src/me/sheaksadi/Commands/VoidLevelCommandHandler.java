package me.sheaksadi.Commands;

import me.sheaksadi.VoidLevel;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class VoidLevelCommandHandler implements CommandExecutor {
    VoidLevel plugin;

    public VoidLevelCommandHandler(VoidLevel plugin) {
        this.plugin = plugin;
    }
    boolean sent = false;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sent=false;
        if (label.equalsIgnoreCase("voidLevel")) {

            if (args.length <= 2 && args.length > 0) {
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage((ChatColor.DARK_RED + "Usage: /voidlevel {level} {world}"));
                }
                if (args[0].equalsIgnoreCase("Check")) {
                    sender.sendMessage((ChatColor.AQUA + "VoidLevel  " + ChatColor.GRAY + "All void levels - "));

                    plugin.getConfig().getConfigurationSection("voidLevel").getKeys(false).forEach( key -> {
                        sender.sendMessage(("   "+ ChatColor.AQUA +key + ChatColor.GREEN + " : " + plugin.getConfig().getString("voidLevel."+key)));
                    });
//                    for (String worldName: plugin.getConfig().getConfigurationSectio"voidLevel")){
//                        sender.sendMessage((ChatColor.AQUA +worldName + ChatColor.GREEN + " : " + plugin.getConfig().getString("voidLevel."+worldName)));
//                    }
                    return true;
                }
                if (isNumeric(args[0])) {
                    String worldName;
                    if (args.length == 1) {
                        worldName = "world";
                    } else {
                        worldName = args[1];
                    }
                    if (Bukkit.getWorld(worldName)==null){
                        sender.sendMessage((ChatColor.DARK_RED + "No world found named "+worldName));
                        return false;
                    }
                    plugin.getConfig().set("voidLevel." + worldName, args[0]);

                    plugin.saveConfig();
                    int configVoidLevel = Integer.parseInt(plugin.getConfig().get("voidLevel." + worldName).toString());


                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        sender.sendMessage((ChatColor.AQUA + "VoidLevel" + ChatColor.GRAY + " set to " + configVoidLevel));
                        for (Chunk chunk : Bukkit.getWorld(worldName).getLoadedChunks()) {
                            assert Bukkit.getWorld(worldName).isChunkInUse(chunk.getX(), chunk.getZ());
                            int X = chunk.getX() * 16;
                            int Z = chunk.getZ() * 16;


                            // Bukkit.broadcast(String.valueOf(X),"sda");
                            for (int x = 0; x < 16; x++) {
                                for (int z = 0; z < 16; z++) {
                                    for (int y = 0; y < configVoidLevel; y++) {
                                        Location location = new Location(Bukkit.getWorld(worldName), X + x, y, Z + z);

                                        Bukkit.getWorld(worldName).getBlockAt(location).setType(Material.AIR, false);
//                   .setType(Material.AIR);
                                    }
                                }
                            }
                        }


                    }
                }
            } else if (args.length == 0) {




                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    plugin.getConfig().getConfigurationSection("voidLevel").getKeys(false).forEach( key -> {
                        if (key.equalsIgnoreCase(player.getWorld().getName())){
                            setSend();
                            int configVoidLevel = Integer.parseInt(plugin.getConfig().get("voidLevel." + player.getWorld().getName()).toString());
                            sender.sendMessage((ChatColor.AQUA + "VoidLevel  " + ChatColor.GRAY + "of \"" + player.getWorld().getName() + "\" is " + configVoidLevel));
                        }
                    });

                }
                if (!sent){
                    int configVoidLevel = Integer.parseInt(plugin.getConfig().get("voidLevel.world").toString());

                    sender.sendMessage((ChatColor.AQUA + "VoidLevel  " + ChatColor.GRAY + "of \"world\" is " + configVoidLevel));
                }

            } else {
                sender.sendMessage((ChatColor.DARK_RED + "Usage: /voidlevel {level} {world}"));

            }

        }
        return true;
    }
    private void setSend(){
        sent=true;
    }

}
