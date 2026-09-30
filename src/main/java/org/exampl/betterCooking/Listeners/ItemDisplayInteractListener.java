package org.exampl.betterCooking.Listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.exampl.betterCooking.BetterCooking;

public class ItemDisplayInteractListener implements Listener {

    private final NamespacedKey objectKey = new NamespacedKey(
            BetterCooking.getBetterCooking(),
            "custom_object"
    );

    @EventHandler
    public void onItemDisplayInteract(PlayerInteractEntityEvent event) {

        System.out.println("Fire");

        if (!(event.getRightClicked() instanceof Interaction interaction)) {
            return;
        }

        if (!interaction.getPersistentDataContainer().has(objectKey)) {
            return;
        }

        String objectType = interaction.getPersistentDataContainer().get(
                objectKey,
                PersistentDataType.STRING
        );

        if(objectType == null){
           return;
        }

        if(objectType.equals("chopping_board")) {
            // This is your chopping board
            Player player = event.getPlayer();

            player.sendMessage("Chopping board clicked!");

            event.setCancelled(true);
        }
    }
}
