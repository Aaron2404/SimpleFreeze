package dev.boostio.Events;

import dev.boostio.SimpleFreeze;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {
    @EventHandler
    public void BreakBlock(BlockBreakEvent event){
            Player player = event.getPlayer();
            boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(player.getUniqueId());
            if (isFrozen) {
                event.setCancelled(true);
            }
        }
    }

