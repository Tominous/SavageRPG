package net.prosavage.savageequipment.listener;

import net.prosavage.savageequipment.enchant.CustomEnchant;
import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickToEnchant implements Listener {

    Color Color = new Color();
    CustomEnchant Item = new CustomEnchant();

    @EventHandler
    public void clickEvent (InventoryClickEvent e){
        if (e.getWhoClicked() instanceof Player){
            e.getWhoClicked().sendMessage(e.getCursor().getType().toString());
            if (e.getCursor().getType() == Material.ENCHANTED_BOOK){
                e.setCancelled(true);
                Player player = (Player) e.getWhoClicked();
                ItemStack cursorItem = e.getCursor();
                ItemStack clickedItem = e.getCurrentItem();
                String enchantName = Item.getCustomEnchantmentName(cursorItem);
                Integer enchantLevel = Item.getCustomEnchantmentLevel(cursorItem);
                Double enchantApply = Item.getCustomEnchantmentApplyChance(cursorItem);
                Double enchantDestroy = Item.getCustomEnchantmentDestroyChance(cursorItem);
                player.sendMessage("Name: " + enchantName + " Level: " + enchantLevel + " Apply: " + enchantApply + " Destroy: " + enchantDestroy);
            }
        }
    }

}
