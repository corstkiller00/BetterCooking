package org.exampl.betterCooking;

import org.bukkit.plugin.java.JavaPlugin;
import org.exampl.betterCooking.Commands.SpawnChoppingBoardCommand;
import org.exampl.betterCooking.Listeners.ItemDisplayInteractListener;

public final class BetterCooking extends JavaPlugin {

    private static BetterCooking betterCooking;

    @Override
    public void onEnable() {
        // Plugin startup logic
        betterCooking = this;
        getServer().getPluginManager().registerEvents(new ItemDisplayInteractListener(), this);
        getCommand("spawnchoppingboard").setExecutor(
                new SpawnChoppingBoardCommand()
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BetterCooking getBetterCooking() {
        return betterCooking;
    }
}
