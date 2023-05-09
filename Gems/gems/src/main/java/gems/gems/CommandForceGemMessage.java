package gems.gems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandForceGemMessage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Gems.instance.sendGemMessage();
        sender.sendMessage("forcing gem event...");
        // logs to console
        Gems.instance.getLogger().info("Force gem message sent!");
        return false;
    }
}
