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

import java.util.ArrayList;
import java.util.List;

public class ClickToEnchant implements Listener {

    Color Color = new Color();
    CustomEnchant Item = new CustomEnchant();

    @EventHandler
    public void clickEvent (InventoryClickEvent e){
        if (e.getWhoClicked() instanceof Player){
            e.getWhoClicked().sendMessage(e.getCursor().getType().toString());
            if (e.getCursor().getType() == Material.ENCHANTED_BOOK) {
                if (e.getCurrentItem().getType() != Material.AIR) {
                    if (e.getCurrentItem().hasItemMeta()) {
                        e.setCancelled(true);
                        Player player = (Player) e.getWhoClicked();
                        ItemStack cursorItem = e.getCursor();
                        ItemStack clickedItem = e.getCurrentItem();

                        String enchantName = Item.getCustomEnchantmentName(cursorItem);
                        Integer enchantLevel = Item.getCustomEnchantmentLevel(cursorItem);
                        String romanLevel = Item.intToRoman(enchantLevel);
                        Double enchantApply = Item.getCustomEnchantmentApplyChance(cursorItem);
                        Double enchantDestroy = Item.getCustomEnchantmentDestroyChance(cursorItem);
//                        player.sendMessage("Name: " + enchantName + " Level: " + enchantLevel + " Apply: " + enchantApply + " Destroy: " + enchantDestroy);
                        ItemMeta clickedMeta = clickedItem.getItemMeta();
                        List<String> clickedLore = clickedMeta.getLore();
                        Integer ScrollIndex = Item.getScrollIndex(clickedItem);
//                        player.sendMessage(String.valueOf(ScrollIndex));
                        Integer EnchantIndex = Item.getLoreEnchantmentIndex(clickedItem, enchantName);
                        Boolean isHigher = Item.isHigherThanLoreEnchantment(clickedItem, cursorItem);
                        if (EnchantIndex != null) {
                            if (Item.hasCustomEnchantmentItemStack(Item.getCustomEnchantmentItemStack(cursorItem), clickedItem)) {
                                if (isHigher){
                                    player.sendMessage("hey " + EnchantIndex + " " + isHigher);
                                    clickedLore.set(EnchantIndex, Color.ify("&7" + enchantName + " " + romanLevel));
                                    clickedMeta.setLore(clickedLore);
                                    clickedItem.setItemMeta(clickedMeta);
                                }
                                if (!isHigher) {
                                    player.sendMessage("NO! " + isHigher);
                                }
                            }
                        }
                        else if (ScrollIndex != null) {
                            if (Item.hasCustomEnchantmentItemStack(Item.getCustomEnchantmentItemStack(cursorItem), clickedItem)) {
//                                player.sendMessage("hey " + ScrollIndex + " " + enchantName + " " + romanLevel);
                                clickedLore.set(ScrollIndex, Color.ify("&7" + enchantName + " " + romanLevel));
                                clickedMeta.setLore(clickedLore);
                                clickedItem.setItemMeta(clickedMeta);
//                                player.sendMessage(Color.ify("&7Enchantment &e" + enchantName + " " + romanLevel + " &7have applied to your item."));

                            }
                        }
                    }
                }
            }
        }
    }

}
