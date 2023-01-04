package dev.boostio.Events;

import dev.boostio.ModFunctionality;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {
    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event){
            Player player = event.getPlayer();
            boolean isFrozen = ModFunctionality.getInstance().getFreezeData().containsKey(player.getUniqueId());
            if (isFrozen) {
                event.setCancelled(true);
            }
        }
    }

