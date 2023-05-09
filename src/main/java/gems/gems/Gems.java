package gems.gems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import org.bukkit.scheduler.BukkitScheduler;

public final class Gems extends JavaPlugin {
    public static Gems instance;
    Random rand = new Random();

    String[] codeNames = {"MRBEAST", "MR.BEAST", "TURBO", "JORDVANN", "DANTDM"};

    CommandClaim claimCmd = new CommandClaim();

    int minGems = 5;
    int maxGems = 13;

    float minTime = 10;
    float maxTime = 50;

    float lastGemTime = 0;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("claim").setExecutor(claimCmd);
        this.getCommand("forceEvent").setExecutor(new CommandForceGemMessage());

        instance = this;

        // event that runs every 40 ticks
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if ((System.currentTimeMillis() * 1000) > lastGemTime)
                    return;

                lastGemTime = (System.currentTimeMillis() * 1000) + ((rand.nextFloat() * (maxTime - minTime) + minTime) * 60);
                sendGemMessage();
            }
        }, 0L, 40L);

    }

    public void sendGemMessage(){
        int gems = rand.nextInt((maxGems - minGems) + 1) + minGems;

        String code = codeNames[rand.nextInt(codeNames.length)];
        int id = rand.nextInt(100000);

        addCode(new Code(code, gems, id));

        SendMessage("[GEM] USE CODE " + code + " FOR " + gems + " GEMS! (/claim {code})");
    }

    public void addCode(Code c){
        claimCmd.codes.add(c);
    }

    public void SendMessage(String msg){
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(msg);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onLoad() {
        // Plugin load logic
        this.getLogger().info("Gems plugin loaded!");
    }
}


