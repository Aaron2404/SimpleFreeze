package dev.boostio.events;

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

        if (isFrozen) {
            Bukkit.broadcast(ChatColor.RED + "§7[§l§cWARNING§r§7]§c " + player.getDisplayName() + " has left whilst frozen!", "simplefreeze.use");
        }
    }
}
