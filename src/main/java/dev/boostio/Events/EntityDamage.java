package dev.boostio.Events;

import dev.boostio.ModFunctionality;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
        Player victim = (Player) event.getEntity();
        boolean isFrozen = ModFunctionality.getInstance().getFreezeData().containsKey(victim.getUniqueId());
            if (isFrozen) {
                event.setCancelled(true);
            }
        }
    }
}
