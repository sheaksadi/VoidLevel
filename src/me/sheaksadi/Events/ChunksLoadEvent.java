package me.sheaksadi.Events;

import me.sheaksadi.VoidLevel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunksLoadEvent implements Listener {
    VoidLevel plugin;

    public ChunksLoadEvent(VoidLevel plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e){
        int X = e.getChunk().getX() * 16;
        int Z = e.getChunk().getZ() * 16;
        int configVoidLevel=Integer.parseInt( plugin.getConfig().get("voidLevel."+e.getWorld().getName()).toString());

       // Bukkit.broadcast(String.valueOf(X),"sda");
        for (int x=0;x<16;x++){
            for (int z=0;z<16;z++){
                for (int y=0;y<configVoidLevel;y++){
                    Location location = new Location(e.getWorld(), X+x, y, Z+z);

                    e.getWorld().getBlockAt(location).setType(Material.AIR,false);
//                   .setType(Material.AIR);
                }
            }
        }

    }
}
