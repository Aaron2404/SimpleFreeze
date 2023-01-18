package dev.boostio.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
@Setter
public class FreezeData {
    private UUID uuid;
    private boolean isFrozen;
    private Location Location;

}
