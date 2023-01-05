package dev.boostio;

import dev.boostio.Events.*;
import dev.boostio.Utils.FreezeData;
import dev.boostio.commands.FreezeCommand;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

@Getter
public final class SimpleFreeze extends JavaPlugin {

    @Getter
    private static SimpleFreeze instance;
    private final HashMap<UUID, FreezeData> freezeData = new HashMap<>();
    public static String StrikeTrough = ChatColor.GRAY + "§m" + StringUtils.repeat(" ", 64);

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        //Try to get the settings from the config file.
        try {
            config = getConfig();

            if(!SimpleFreeze.config.getBoolean("playerFreezeMessageLines"))
                StrikeTrough = "";

        } catch (Exception e) {
            Bukkit.getLogger().warning("Something went wrong while getting the settings from the config file");
        }

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
