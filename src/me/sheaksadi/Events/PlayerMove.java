package me.sheaksadi.Events;

import me.sheaksadi.VoidLevel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    VoidLevel plugin;

    public PlayerMove(VoidLevel plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){

        int X = e.getPlayer().getLocation().getChunk().getX() * 16;
        int Z = e.getPlayer().getLocation().getChunk().getZ() * 16;
       // e.getPlayer().sendMessage(e.getPlayer().getWorld().getName());
        int configVoidLevel=Integer.parseInt( plugin.getConfig().getString("voidLevel."+e.getPlayer().getWorld().getName()));

        // Bukkit.broadcast(String.valueOf(X),"sda");
        for (int x=0;x<16;x++){
            for (int z=0;z<16;z++){
                for (int y=0;y<configVoidLevel;y++){
                    Location location = new Location(e.getPlayer().getWorld(), X+x, y, Z+z);

                    e.getPlayer().getWorld().getBlockAt(location).setType(Material.AIR,false);
//                   .setType(Material.AIR);
                }
            }
        }

        if (e.getPlayer().getLocation().getBlockY()<=configVoidLevel){
            e.getPlayer().damage(4);
        }

    }
//
//    @EventHandler
//    public void onEntityMove(EntityEvent e){
//        int configVoidLevel=Integer.parseInt( plugin.getConfig().get("voidLevel").toString());
//        Entity entity = e.getEntity();
//        if (entity.getLocation().getBlockY()<=configVoidLevel){
//            if (entity instanceof LivingEntity) {
//                ((org.bukkit.entity.LivingEntity)entity).damage(4);
//            }
//        }else {
//            entity.remove();
//        }
//
//    }






}
