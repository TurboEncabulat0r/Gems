package gems.gems;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Gems extends JavaPlugin {

    public static Gems instance;

    public CommandClaim claimCmd = new CommandClaim();
    public CommandForceGemMessage forceGemMessage = new CommandForceGemMessage();
    public GemsEventListener eventListener = new GemsEventListener();

    private final String[] codeNames = {"MRBEAST", "MR.BEAST", "TURBO", "JORDVANN", "DANTDM"};

    private final Random rand = new Random();

    private int minGems = 5;
    private int maxGems = 13;

    List<String> lore = new ArrayList<String>();

    @Override
    public void onEnable() {
        lore.add(ChatColor.DARK_PURPLE + "Buy Now For Only !1.99$");

        this.getCommand("claim").setExecutor(claimCmd);
        this.getCommand("forceevent").setExecutor(forceGemMessage);
        this.getCommand("displaycodes").setExecutor(new CmdShowAllCodes());

        getServer().getPluginManager().registerEvents(eventListener, this);

        eventListener.init();
    }

    public void sendGemMessage(){
        int gems = rand.nextInt((maxGems - minGems) + 1) + minGems;

        String code = codeNames[rand.nextInt(codeNames.length)];
        int id = rand.nextInt(100000);

        addCode(new Code(code, gems, id));

        SendMessage( "§5[GEM]§f USE CODE " + (code + id) + " FOR §5" + gems + "§f GEMS! (/claim {code})");
    }

    public void addCode(Code c){
        claimCmd.codes.add(c);
    }


    public void SendMessage(String msg){
        Bukkit.getLogger().info(msg);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(msg);
        }
    }

    public void GiveGems(Player s, int ammount){
        ItemStack item = new ItemStack(Material.AMETHYST_CLUSTER, ammount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Gems");
        meta.setLore(lore);
        item.setItemMeta(meta);

        s.getInventory().addItem(item);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public void onLoad() {
        // Plugin load logic
        instance = this;
        this.getLogger().info("Gems plugin loaded!");
    }
}


