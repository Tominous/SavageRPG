package net.prosavage.savageequipment.enchant;

import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CustomEnchant {

    Color Color = new Color();

    public Integer getLevel(ItemStack item, String s) {
        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        String line = null;
        for (String string : meta.getLore()) {
            if (string.contains(s)) {
                String line2359 = Color.strip(string).replace(s, "");
                line = line2359.replace(" ", "");
                Integer romanToInt = romanToInt(line);
                return romanToInt;
            }
        }
        return null;
    }

    public static Boolean chanceOf(Integer chance) {
        Random r = new Random();
        if (r.nextInt(100) < 5) {
            return true;
        }
        return false;
    }

    public String intToRoman(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for (Map.Entry<String, Integer> entry : roman_numerals.entrySet()) {
            int matches = Int / entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }

    public String repeat(String s, int n) {
        if (s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public int romanToInt(String s) {
        int total = 0, currentVal = 0, prevVal = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I':
                    currentVal = 1;
                    break;
                case 'V':
                    currentVal = 5;
                    break;
                case 'X':
                    currentVal = 10;
                    break;
                case 'L':
                    currentVal = 50;
                    break;
                case 'C':
                    currentVal = 100;
                    break;
                case 'D':
                    currentVal = 500;
                    break;
                case 'M':
                    currentVal = 1000;
                    break;
                default:
                    break;
            }
            total += (currentVal < prevVal) ? -1 * currentVal : currentVal;
            prevVal = currentVal;
        }
        return total;
    }

    public String getCustomEnchantmentName(ItemStack item) {
        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        String name = null;
        for (String string : meta.getLore()) {
            if (string.contains("Name: ")) {
                name = Color.strip(string).replace("Name: ", "");
                return name;

            }
        }
        return null;
    }

    public Integer getCustomEnchantmentLevel(ItemStack item) {
        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        Integer level = null;
        for (String string : meta.getLore()) {
            if (string.contains("Level: ")) {
                level = Integer.valueOf(Color.strip(string).replace("Level: ", ""));
                return level;

            }
        }
        return null;
    }

    public Double getCustomEnchantmentApplyChance(ItemStack item) {
        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        Double applyChance = null;
        for (String string : meta.getLore()) {
            if (string.contains("Apply Chance: ")) {
                applyChance = Double.valueOf(Color.strip(string).replace("Apply Chance: ", ""));
                return applyChance;

            }
        }
        return null;
    }

    public Double getCustomEnchantmentDestroyChance(ItemStack item) {
        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        Double applyChance = null;
        for (String string : meta.getLore()) {
            if (string.contains("Destroy Chance: ")) {
                applyChance = Double.valueOf(Color.strip(string).replace("Destroy Chance: ", ""));
                return applyChance;

            }
        }
        return null;
    }

    public String getCustomEnchantmentItemStack(ItemStack item) {

        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        String itemStack = null;
        for (String string : meta.getLore()) {
            if (string.contains("Material: ")) {
                itemStack = Color.strip(string).replace("Material: ", "");
                return itemStack;

            }
        }
        return null;

    }

    public Boolean hasCustomEnchantmentItemStack(String name, ItemStack item){
        if (name == null) return false;

        if (item.getType().toString().contains(name)){
            return true;
        }

    return false;
    }

    public Integer getScrollIndex(ItemStack item){
        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        List<String> lore = meta.getLore();

        for (String string : lore) {
            if (string.contains("Scroll Socket #")) {

                return lore.indexOf(string);

            }
        }
    return null;
    }

    public Integer getLoreEnchantmentIndex(ItemStack item, String name){
        if (item == null) return null;

        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return null;

        List<String> lore = meta.getLore();

        for (String string : lore) {
            if (string.contains(name)) {

                return lore.indexOf(string);

            }
        }
        return null;
    }

    public Boolean hasLoreEnchantment(ItemStack item, String name){
        if (item == null) return false;

        if (!item.hasItemMeta()) return false;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return false;

        List<String> lore = meta.getLore();

        for (String string : lore) {
            if (string.contains(name)) {

                return true;

            }
        }
        return false;
    }

    public Boolean isHigherThanLoreEnchantment(ItemStack item, ItemStack customEnchant){
        if (item == null) return false;

        if (!item.hasItemMeta()) return false;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return false;

        List<String> lore = meta.getLore();

        Integer customEnchantmentLevel = getCustomEnchantmentLevel(customEnchant);
        String customEnchantName = getCustomEnchantmentName(customEnchant);

        for (String string : lore) {
            if (string.contains(customEnchantName)) {
                Integer loreLevel = romanToInt(Color.strip(string).replace(customEnchantName + " ", ""));

                if (customEnchantmentLevel > loreLevel) {
                    return true;
                }
                else if (customEnchantmentLevel.equals(loreLevel)){
                    return false;
                }
                else if (loreLevel > customEnchantmentLevel){
                    return false;
                }

            }
        }
        return false;
    }

}
