package dev.boostio.Events;

import dev.boostio.ModFunctionality;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageBy implements Listener {
    @EventHandler
    public void EntityDamageBy(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player) {
            Player Attacker = (Player) event.getDamager();
            boolean isFrozen = ModFunctionality.getInstance().getFreezeData().containsKey(Attacker.getUniqueId());
            if (isFrozen) {
                event.setCancelled(true);
            }
        }
    }
}
