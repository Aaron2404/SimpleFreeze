package dev.boostio.Events;

import dev.boostio.SimpleFreeze;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreProcess implements Listener {
    @EventHandler
    public void PlayerCommandPreProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(player.getUniqueId());
        if (isFrozen) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + SimpleFreeze.config.getString("playerCommandMessage"));
        }
    }
}

