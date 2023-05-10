package gems.gems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdShowAllCodes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!sender.isOp()) {
            sender.sendMessage("You must be an operator to use this command!");
            return true;
        }

        sender.sendMessage("§f§lAll codes:");
        for (Code c : Gems.instance.claimCmd.codes) {
            sender.sendMessage(c.getName() + ": " + c.gems + " gems");
        }
        return true;
    }

}
