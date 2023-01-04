package dev.boostio.Events;

import dev.boostio.SimpleFreeze;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageBy implements Listener {
    @EventHandler
    public void EntityDamageBy(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player) {
            Player Attacker = (Player) event.getDamager();
            boolean isFrozen = SimpleFreeze.getInstance().getFreezeData().containsKey(Attacker.getUniqueId());
            if (isFrozen) {
                event.setCancelled(true);
            }
        }
    }
}
