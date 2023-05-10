package gems.gems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandForceGemMessage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)){
            Gems.instance.sendGemMessage();
            Gems.instance.getLogger().info("Force gem message sent!");
            return true;
        }

        if(!sender.isOp())
        {
            sender.sendMessage("You must be an operator to use this command!");
            return true;
        }

        Gems.instance.sendGemMessage();
        sender.sendMessage("forcing gem event...");
        // logs to console
        Gems.instance.getLogger().info("Force gem message sent!");
        return true;
    }
}
