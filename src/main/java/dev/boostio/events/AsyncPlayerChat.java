package dev.boostio.events;

import dev.boostio.SimpleFreeze;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerChat(AsyncPlayerChatEvent event) {
        {
            Player player = event.getPlayer();
            String message = event.getMessage();

            boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(player.getUniqueId());
            if (isFrozen) {
                event.setCancelled(true);
                String frozenMessage = "§7(§4§l§nFROZEN§7) §c" + player.getDisplayName() + ": §f" + message;
                Bukkit.broadcast(frozenMessage, "SimpleFreeze.use");
                player.sendMessage(frozenMessage);
            }
        }
    }
}
