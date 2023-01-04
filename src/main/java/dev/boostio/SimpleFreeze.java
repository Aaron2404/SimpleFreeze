package dev.boostio;

import dev.boostio.Events.*;
import dev.boostio.Utils.FreezeData;
import dev.boostio.commands.FreezeCommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

@Getter
public final class SimpleFreeze extends JavaPlugin {

    @Getter
    private static SimpleFreeze instance;
    private final HashMap<UUID, FreezeData> freezeData = new HashMap<>();
    @Override
    public void onEnable() {
        instance = this;

        //events
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageBy(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreProcess(), this);


        //Command
        getCommand("freeze").setExecutor(new FreezeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
