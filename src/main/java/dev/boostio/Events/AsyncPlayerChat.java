package dev.boostio.Events;

import dev.boostio.ModFunctionality;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event){{
        Player player = event.getPlayer();
        boolean isFrozen = ModFunctionality.getInstance().getPlayerData().get(player.getUniqueId()).isFrozen();
        if(isFrozen){
            String Message = event.getMessage();
            event.setCancelled(true);
            String frozenMessage = "§7(§4§l§nFROZEN§7) §c" + player.getDisplayName() + ": §f" + Message;
            Bukkit.broadcast(frozenMessage, "modfunctionality.freeze.use");
            player.sendMessage(frozenMessage);
        }
    }}
}
