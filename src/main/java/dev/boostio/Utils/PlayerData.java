package dev.boostio.Utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
@Setter
public class PlayerData {
    private UUID uuid;
    private boolean isFrozen;

    private Location Location;

}
