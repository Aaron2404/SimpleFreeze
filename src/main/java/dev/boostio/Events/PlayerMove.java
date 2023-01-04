package dev.boostio.Events;

import dev.boostio.SimpleFreeze;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(player.getUniqueId());
        if(isFrozen){
            Location locationNow = event.getPlayer().getLocation();
            Location locationFrozen = SimpleFreeze.getInstance().getFreezeData().get(player.getUniqueId()).getLocation();
            if(locationFrozen.getX() != locationNow.getX() || locationFrozen.getZ() != locationNow.getZ() || locationFrozen.getY() != locationNow.getY()){
                player.teleport(locationFrozen);
                player.sendMessage(ChatColor.RED + "\n⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠\n" + ChatColor.BOLD + "ATTENTION!" + ChatColor.RED + "\nYou have been frozen! join discord.gg/vanarchy\nIf you log out you will be BANNED.\n⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠\n");
            }
        }
    }
}
