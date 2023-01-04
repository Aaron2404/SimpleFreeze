package dev.boostio;

import dev.boostio.Events.*;
import dev.boostio.Utils.FreezeData;
import dev.boostio.commands.FreezeCommand;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

@Getter
public final class ModFunctionality extends JavaPlugin {

    @Getter
    private static ModFunctionality instance;
    private final HashMap<UUID, FreezeData> freezeData = new HashMap<>();
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("ratio");

        //events
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);

        //Command
        getCommand("freeze").setExecutor(new FreezeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
