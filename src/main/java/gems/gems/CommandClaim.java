package gems.gems;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommandClaim implements CommandExecutor {

    List<Code> codes = new ArrayList<Code>();

    List<String> lore = new ArrayList<String>();


    public CommandClaim() {
        lore.add("§5Buy Now For Only 1.99$");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        for (Code c : codes)
        {
            if (c.name.equalsIgnoreCase(args[0]))
            {
                sender.sendMessage("You claimed " + c.gems + " gems!");
                codes.remove(c);
                CommandSender s = sender;

                ItemStack item = new ItemStack(Material.AMETHYST_CLUSTER, c.gems);

                item.getItemMeta().setDisplayName("§5Gem");
                item.getItemMeta().setLore(lore);

                return true;
            }
        }
        sender.sendMessage("Invalid code!");
        return false;
    }

}
