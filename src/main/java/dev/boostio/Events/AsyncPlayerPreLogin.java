package dev.boostio.Events;

import dev.boostio.ModFunctionality;
import dev.boostio.Utils.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class AsyncPlayerPreLogin implements Listener {
    @EventHandler
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        PlayerData playerData = new PlayerData();
        ModFunctionality.getInstance().getPlayerData().put(uuid, playerData);
    }
}
