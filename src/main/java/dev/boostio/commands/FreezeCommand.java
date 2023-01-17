package dev.boostio.commands;

import dev.boostio.SimpleFreeze;
import dev.boostio.Utils.FreezeData;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static dev.boostio.SimpleFreeze.StrikeTrough;

public class FreezeCommand implements CommandExecutor {

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

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (target.equals(player)) {
                sender.sendMessage(ChatColor.RED + "You cannot freeze yourself.");
                return false;
            }
        }

        if (target.hasPermission("SimpleFreeze.bypass")) {
            sender.sendMessage(ChatColor.RED + "This player cannot be frozen.");
            return false;
        }


        if (SimpleFreeze.getInstance().getFreezeData().containsKey(target.getUniqueId())) {
            SimpleFreeze.getInstance().getFreezeData().remove(target.getUniqueId());
            target.removePotionEffect(PotionEffectType.BLINDNESS);
            target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
            target.resetTitle();
            Bukkit.broadcast(ChatColor.GREEN + target.getDisplayName() + " has been unfrozen by " + sender.getName() + "!", "SimpleFreeze.use");
            target.sendMessage(ChatColor.GREEN + "You have been unfrozen.");
            target.sendTitle(ChatColor.GREEN + "You have been unfrozen!", null, 0, 60, 20);
        } else {
            FreezeData freezeData = new FreezeData();

            Location location = target.getLocation();

            if (SimpleFreeze.config.getBoolean("safeLocation")) {
                World world = target.getWorld();
                Block safeBlock = target.getWorld().getHighestBlockAt(location);
                Location safeLocation = new Location(world, safeBlock.getX(), safeBlock.getY() + 1, safeBlock.getZ(), location.getYaw(), location.getPitch());
                freezeData.setLocation(safeLocation);
            } else
                freezeData.setLocation(location);

            SimpleFreeze.getInstance().getFreezeData().put(target.getUniqueId(), freezeData);
            if (SimpleFreeze.config.getBoolean("freezeTitle")) {
                target.sendTitle(SimpleFreeze.config.getString("primaryFreezeMessage"), SimpleFreeze.config.getString("secondaryFreezeMessage"), SimpleFreeze.config.getInt("fadeIn"), SimpleFreeze.config.getInt("stay"), SimpleFreeze.config.getInt("fadeOut"));
                target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000000, 0));
                target.playSound(target.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            }
            target.sendMessage(StrikeTrough + SimpleFreeze.config.getString("playerFreezeMessage") + StrikeTrough);
            Bukkit.broadcast(ChatColor.RED + target.getDisplayName() + " has been frozen by " + sender.getName() + "!", "SimpleFreeze.use");
        }

        return false;
    }
}
