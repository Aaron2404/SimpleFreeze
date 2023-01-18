package dev.boostio.events;

import dev.boostio.SimpleFreeze;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void EntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(victim.getUniqueId());
            if (isFrozen) {
                event.setCancelled(true);
            }
        }
    }
}
