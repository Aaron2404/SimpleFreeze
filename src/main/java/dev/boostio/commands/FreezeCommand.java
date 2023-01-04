package dev.boostio.commands;

import dev.boostio.ModFunctionality;
import dev.boostio.Utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players are allowed to execute this command :(");
            return false;
        }

        Player player = (Player) sender;
        if (!sender.hasPermission("modfunctionality.freeze.use")) {
            player.sendMessage(ChatColor.RED + "You do not have the required permission to use this command.");
            return false;
        }

        if(args.equals(null)){
            player.sendMessage(ChatColor.RED + "You cannot freeze yourself.");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "The player " + args[0] + " is not online!");
            return false;
        }
        PlayerData playerData = ModFunctionality.getInstance().getPlayerData().get(target.getUniqueId());
        if(playerData.isFrozen()){
            playerData.setFrozen(false);
            player.sendMessage("You have unfrozen " + target.getDisplayName());
            target.sendMessage(ChatColor.GREEN + "You have been unfrozen.");
        }
        else{
            playerData.setFrozen(true);
            playerData.setLocation(target.getLocation());
            target.sendMessage(ChatColor.RED + "You have been frozen.");
            player.sendMessage("You have frozen " + target.getDisplayName());
        }



        return false;
    }
}
