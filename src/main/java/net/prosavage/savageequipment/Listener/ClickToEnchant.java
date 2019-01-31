package net.prosavage.savageequipment.listener;

import net.prosavage.savageequipment.enchant.CustomEnchant;
import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class ClickToEnchant implements Listener {

    Color Color = new Color();
    CustomEnchant Item = new CustomEnchant();

    @EventHandler
    public void clickEvent (InventoryClickEvent e){
        if ((e.getWhoClicked() instanceof Player) && (e.getCursor().getType() == Material.ENCHANTED_BOOK) && (e.getCurrentItem().getType() != Material.AIR) && (e.getCurrentItem().hasItemMeta())){
            e.setCancelled(true);
            ItemStack cursorItem = e.getCursor();
            ItemStack clickedItem = e.getCurrentItem();

            String enchantName = Item.getCustomEnchantmentName(cursorItem);
            Integer enchantLevel = Item.getCustomEnchantmentLevel(cursorItem);
            String romanLevel = Item.intToRoman(enchantLevel);
            Double enchantApply = Item.getCustomEnchantmentApplyChance(cursorItem);
            Double enchantDestroy = Item.getCustomEnchantmentDestroyChance(cursorItem);
            ItemMeta clickedMeta = clickedItem.getItemMeta();
            List<String> clickedLore = clickedMeta.getLore();
            Integer ScrollIndex = Item.getScrollIndex(clickedItem);
            Integer EnchantIndex = Item.getLoreEnchantmentIndex(clickedItem, enchantName);
            Boolean isHigher = Item.isHigherThanLoreEnchantment(clickedItem, cursorItem);
            if ((EnchantIndex != null) && (Item.hasCustomEnchantmentItemStack(Item.getCustomEnchantmentItemStack(cursorItem), clickedItem)) && (isHigher)){
                clickedLore.set(EnchantIndex, Color.ify("&7" + enchantName + " " + romanLevel));
                clickedMeta.setLore(clickedLore);
                clickedItem.setItemMeta(clickedMeta);
            }
            else if ((ScrollIndex != null) && (Item.hasCustomEnchantmentItemStack(Item.getCustomEnchantmentItemStack(cursorItem), clickedItem))) {
                clickedLore.set(ScrollIndex, Color.ify("&7" + enchantName + " " + romanLevel));
                clickedMeta.setLore(clickedLore);
                clickedItem.setItemMeta(clickedMeta);

            }
        }
    }

}
