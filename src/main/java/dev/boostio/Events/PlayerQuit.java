package dev.boostio.Events;

import dev.boostio.SimpleFreeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(player.getUniqueId());

        if(isFrozen){
            Bukkit.broadcast(player.getDisplayName() + ChatColor.RED + " Has left whilst frozen!", "modfunctionality.freeze.use");
        }
    }
}
