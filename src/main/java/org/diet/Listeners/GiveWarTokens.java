package org.diet.Listeners;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.event.NewDayEvent;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.diet.Util.WarTokenUtil;

import static org.diet.DIETLogger.*;

public class GiveWarTokens implements Listener {
    private final Plugin plugin;

    public GiveWarTokens(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * On each new Towny day, give all nations a war token. <br><br>
     * This checks if they are eligible for a new war token.
     * The nation must have < the configured max token count.
     * They also must not be at war, or in a warmup period.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onNewDayGiveNationsTokens(NewDayEvent event) {

        int maxTokens = plugin.getConfig().getInt("war-tokens.max-per-nation");

        for (Nation nation : TownyUniverse.getInstance().getNations()) {
            if (WarTokenUtil.getTokens(nation) < maxTokens) {
                WarTokenUtil.addToken(nation);
            }

            // TODO: BUG! nation.save() does not save the new metadata value. Unless I was stupid and just closed the server too fast.
            nation.save();  // Must be called, or it will not persist across reloads (in this version at least)

            log(INFO, "Gave nation war token to " + nation.getName());
        }
    }
}
