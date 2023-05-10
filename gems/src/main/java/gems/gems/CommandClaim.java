package gems.gems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandClaim implements CommandExecutor {

    List<Code> codes = new ArrayList<Code>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) {
            Gems.instance.getLogger().info("Only players can use this command!");
            return true;
        }

        try {
            for (Code c : codes) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    sender.sendMessage("You claimed " + c.gems + " gems!");
                    codes.remove(c);
                    CommandSender s = sender;

                    Gems.instance.GiveGems((Player) s, c.gems);

                    Gems.instance.SendMessage("§f" + s.getName() + "§f claimed §5" + c.gems + "§f gems!");

                    return true;
                }
            }
        }
        catch (Exception e){
            sender.sendMessage("Invalid code!");
            return true;
        }
        sender.sendMessage("Invalid code!");
        return true;
    }

}
