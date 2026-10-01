package org.exampl.betterCooking.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.exampl.betterCooking.BetterCooking;

import java.util.UUID;

public class CustomEntityTakesDamageListener implements Listener {

    private final NamespacedKey objectKey = new NamespacedKey(
            BetterCooking.getBetterCooking(),
            "custom_object"
    );

    private final NamespacedKey modelKey = new NamespacedKey(
            BetterCooking.getBetterCooking(),
            "chopping_board"
    );

    NamespacedKey displayUUIDKey = new NamespacedKey(
            BetterCooking.getBetterCooking(),
            "item_display_UUID"
    );

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {


        if (!(event.getEntity() instanceof Interaction interaction)) {
            return;
        }

        if (!(event.getDamager() instanceof Player player)) {
            return;
        }



        PersistentDataContainer pdc =
                interaction.getPersistentDataContainer();


        if (!pdc.has(objectKey, PersistentDataType.STRING)) {
            return;
        }

        event.setCancelled(true);

        String itemDisplayUUIDString = pdc.get(
                displayUUIDKey,
                PersistentDataType.STRING
        );

        if(itemDisplayUUIDString == null){
            return;
        }

        UUID itemDisplayUUID = UUID.fromString(itemDisplayUUIDString);

        Entity entity = Bukkit.getEntity(itemDisplayUUID);

        if (entity instanceof ItemDisplay display) {
            display.remove();
        }

        interaction.remove();
    }
}
