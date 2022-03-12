package me.sheaksadi;

import me.sheaksadi.Commands.VoidLevelCommandHandler;
import me.sheaksadi.Commands.VoidLevelTabComplete;
import me.sheaksadi.Events.ChunksLoadEvent;
import me.sheaksadi.Events.PlayerMove;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidLevel extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "VoidLevel plugin is working");
        this.saveDefaultConfig();
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new ChunksLoadEvent(this),this);
        manager.registerEvents(new PlayerMove(this),this);
        this.getCommand("voidlevel").setExecutor(new VoidLevelCommandHandler(this));
        this.getCommand("voidlevel").setTabCompleter(new VoidLevelTabComplete(this));
    }

    @Override
    public void onDisable() {

    }
}
