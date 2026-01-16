package org.diet;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import org.diet.Commands.CommandManager;
import org.diet.Listeners.GiveWarTokens;

import static org.diet.DIETLogger.*;

public final class StartAllWars extends JavaPlugin {

    @Override
    public void onEnable() {
        DIETLogger.initialize(this);

        saveDefaultConfig();    // Fails silently if config.yml already exists.

        // Check if we are allowed to start
        boolean isEnabledInConfig = getConfig().getBoolean("saw.enabled", true);
        if (!isEnabledInConfig) {
            log(WARNING, "StartAllWars is disabled in config.yml and will not start.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        // Ensure dependencies are present and enabled
        // TODO: Towny, and that it is enabled. otherwise do not start.
        // TODO: DiscordSRV. Being enabled is optional, as its not critical.


        // Set up commands (reminder, needs to happen after objects are initialized)
        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands();


        // Set up listeners
        getServer().getPluginManager().registerEvents(new GiveWarTokens(this), this);


        log(INFO, ChatColor.AQUA + "StartAllWars " + ChatColor.GOLD + "v" + getDescription().getVersion() + ChatColor.RESET + " started!");
    }

    @Override
    public void onDisable() {
        // TODO: save stuff to the SQLite DB.

        log(INFO, ChatColor.AQUA + "StartAllWars " + ChatColor.GOLD + "v" + getDescription().getVersion() + ChatColor.RESET + " stopped!");
    }
}
