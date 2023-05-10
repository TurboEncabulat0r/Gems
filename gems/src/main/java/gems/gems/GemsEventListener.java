package gems.gems;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class GemsEventListener implements Listener {

    private final Random rand = new Random();

    float minTime = 10;
    float maxTime = 50;

    float lastGemTime = 0;

    public void init(){
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Gems.instance, new Runnable() {
            @Override
            public void run() {
                if ((System.currentTimeMillis() * 1000) > lastGemTime)
                    return;

                lastGemTime = (System.currentTimeMillis() / 1000f) + ((rand.nextFloat() * (maxTime - minTime) + minTime) * 60);
                Gems.instance.sendGemMessage();
            }
        }, 0L, 20L);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            Player player = (Player)event.getEntity();
            Player killer = player.getKiller();
            if(killer != null)
            {
                killer.sendMessage("You killed " + player.getName() + "and recieved 1 gem!");
                Gems.instance.GiveGems(killer, 1);

            }
        }
    }

}


