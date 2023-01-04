package dev.boostio.commands;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dev.boostio.ModFunctionality;
import dev.boostio.Utils.FreezeData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

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

        if(args.length == 0){
            player.sendMessage(ChatColor.RED + "You cannot freeze yourself.");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "The player " + args[0] + " is not online!");
            return false;
        }
        HashMap<UUID, FreezeData> freezedata = ModFunctionality.getInstance().getFreezeData();
        if(freezedata.containsKey(target.getUniqueId())){
            freezedata.remove(target.getUniqueId());
            player.sendMessage("You have unfrozen " + target.getDisplayName());
            target.sendMessage(ChatColor.GREEN + "You have been unfrozen.");
        }
        else{
            FreezeData freezeData = new FreezeData();
            freezeData.setLocation(target.getLocation());
            freezedata.put(target.getUniqueId(), freezeData);
            target.sendMessage(ChatColor.RED + "You have been frozen.");
            player.sendMessage("You have frozen " + target.getDisplayName());
        }



        return false;
    }
}
