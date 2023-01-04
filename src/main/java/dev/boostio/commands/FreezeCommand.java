package dev.boostio.commands;

import dev.boostio.SimpleFreeze;
import dev.boostio.Utils.FreezeData;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {
    private final String StrikeTrough = ChatColor.GRAY + "§m" + StringUtils.repeat(" ", 64);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("SimpleFreeze.use")) {
            sender.sendMessage(ChatColor.RED + "You do not have the required permission to use this command.");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You cannot freeze yourself.");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "The player " + args[0] + " is not online!");
            return false;
        }

        if (target.hasPermission("SimpleFreeze.bypass")) {
            sender.sendMessage(ChatColor.RED + "This player cannot be frozen.");
            return false;
        }


        if (SimpleFreeze.getInstance().getFreezeData().containsKey(target.getUniqueId())) {
            SimpleFreeze.getInstance().getFreezeData().remove(target.getUniqueId());
            sender.sendMessage(ChatColor.GREEN + "You have unfrozen " + target.getDisplayName() + "!");
            target.sendMessage(ChatColor.GREEN + "You have been unfrozen.");
        } else {
            FreezeData freezeData = new FreezeData();

            Location location = target.getLocation();
            World world = target.getWorld();
            Block safeBlock  = world.getHighestBlockAt(location);
            Location safeLocation = safeBlock.getLocation();

            freezeData.setLocation(safeLocation);

            SimpleFreeze.getInstance().getFreezeData().put(target.getUniqueId(), freezeData);
            target.sendMessage(StrikeTrough + ChatColor.BOLD + "\n§cATTENTION!\n" + ChatColor.RED + "\nYou have been frozen! join discord.gg/vanarchy\nIf you log out you will be BANNED.\n" + StrikeTrough);
            sender.sendMessage(ChatColor.GREEN + "You have frozen " + target.getDisplayName() + "!");
        }

        return false;
    }
}
