package org.exampl.betterCooking.Commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.exampl.betterCooking.BetterCooking;
import org.jetbrains.annotations.NotNull;

public class SpawnChoppingBoardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Location location = player.getLocation();

        // Move the display slightly upward
        location.add(0, 0.1, 0);

        ItemStack item = new ItemStack(Material.PAPER);

        ItemMeta meta = item.getItemMeta();

        NamespacedKey modelKey = new NamespacedKey(
                BetterCooking.getBetterCooking(),
                "chopping_board"
        );

        meta.setItemModel(modelKey);

        item.setItemMeta(meta);

        ItemDisplay display = player.getWorld().spawn(
                location,
                ItemDisplay.class
        );

        display.setItemStack(item);
        display.setRotation(0, 0);

        NamespacedKey objectKey = new NamespacedKey(
                BetterCooking.getBetterCooking(),
                "custom_object"
        );


        NamespacedKey displayUUIDKey = new NamespacedKey(
                BetterCooking.getBetterCooking(),
                "item_display_UUID"
        );

        Interaction interaction = player.getWorld().spawn(location, Interaction.class);

        interaction.getPersistentDataContainer().set(
                objectKey,
                PersistentDataType.STRING,
                "chopping_board"
        );

        interaction.getPersistentDataContainer().set(
                displayUUIDKey,
                PersistentDataType.STRING,
                display.getUniqueId().toString()
        );



        return true;
    }

}
