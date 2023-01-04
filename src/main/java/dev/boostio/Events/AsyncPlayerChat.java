package dev.boostio.Events;

import dev.boostio.SimpleFreeze;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void PlayerChat(AsyncPlayerChatEvent event){{
        Player player = event.getPlayer();
        boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(player.getUniqueId());
        if(isFrozen){
            String Message = event.getMessage();
            event.setCancelled(true);
            String frozenMessage = "§7(§4§l§nFROZEN§7) §c" + player.getDisplayName() + ": §f" + Message;
            Bukkit.broadcast(frozenMessage, "SimpleFreeze.use");
            player.sendMessage(frozenMessage);
        }
    }}
}
