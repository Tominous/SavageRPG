package net.prosavage.savageequipment.builder;

import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import net.prosavage.savageequipment.somewhatusefulstuff.RandomNum;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Armor {

    Color Color = new Color();
    RandomNum Random = new RandomNum();

    public ItemStack getNewArmor(){
        ItemStack Item = null;
        LinkedList<ItemStack> items = new LinkedList();

        items.add(new ItemStack(Material.LEATHER_HELMET));
        items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
        items.add(new ItemStack(Material.LEATHER_LEGGINGS));
        items.add(new ItemStack(Material.LEATHER_BOOTS));

        items.add(new ItemStack(Material.GOLDEN_HELMET));
        items.add(new ItemStack(Material.GOLDEN_CHESTPLATE));
        items.add(new ItemStack(Material.GOLDEN_LEGGINGS));
        items.add(new ItemStack(Material.GOLDEN_BOOTS));

        items.add(new ItemStack(Material.CHAINMAIL_HELMET));
        items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        items.add(new ItemStack(Material.CHAINMAIL_BOOTS));

        items.add(new ItemStack(Material.IRON_HELMET));
        items.add(new ItemStack(Material.IRON_CHESTPLATE));
        items.add(new ItemStack(Material.IRON_LEGGINGS));
        items.add(new ItemStack(Material.IRON_BOOTS));

        items.add(new ItemStack(Material.DIAMOND_HELMET));
        items.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
        items.add(new ItemStack(Material.DIAMOND_LEGGINGS));
        items.add(new ItemStack(Material.DIAMOND_BOOTS));

        Collections.shuffle(items);

        Item = items.pop();
        return Item;
    }

    public ItemStack setInfo(ItemStack item){

        Integer hpHighest = 0;
        Integer hpLowest = 0;
        Integer prHighest = 0;
        Integer prLowest = 0;
        Integer reHighest = 0;
        Integer reLowest = 0;
        Integer scHighest = 5;
        Integer scLowest = 1;
        Integer geHighest = 5;
        Integer geLowest = 1;

        ItemMeta meta = item.getItemMeta();

        if (item.getType().toString().contains("LEATHER"))
        {
            hpHighest = 10;
            prHighest = 1;
            hpLowest = 5;
            prLowest = -1;
            reHighest = 3;
            reLowest = 0;
        }
        if (item.getType().toString().contains("GOLDEN"))
        {
            hpHighest = 7;
            prHighest = 2;
            hpLowest = 3;
            prLowest = 0;
            reHighest = 3;
            reLowest = 0;
        }
        if (item.getType().toString().contains("CHAINMAIL"))
        {
            hpHighest = 5;
            prHighest = 3;
            hpLowest = 1;
            prLowest = 1;
            reHighest = 2;
            reLowest = 0;
        }
        if (item.getType().toString().contains("IRON"))
        {
            hpHighest = 3;
            prHighest = 4;
            hpLowest = -1;
            prLowest = 2;
            reHighest = 2;
            reLowest = 0;
        }
        if (item.getType().toString().contains("DIAMOND"))
        {
            hpHighest = 1;
            prHighest = 5;
            hpLowest = -3;
            prLowest = 3;
            reHighest = 1;
            reLowest = 0;
        }

        List<String> lore = new ArrayList();
        String randomLevel = String.valueOf(Random.getInteger(1, 100));

        String randomProtection = String.format("%.2f", Random.getDouble(Double.valueOf(prLowest), Double.valueOf(prHighest)));

        String randomHealth = String.format("%.2f", Random.getDouble(Double.valueOf(hpLowest), Double.valueOf(hpHighest)));

        String randomRegen = String.format("%.2f", Random.getDouble(Double.valueOf(reLowest), Double.valueOf(reHighest)));

        Integer randomScroll = Random.getInteger(scLowest, scHighest);

        Integer randomGem = Random.getInteger(geLowest, geHighest);

        lore.add(Color.ify("&7&m----------------"));
        lore.add(Color.ify("&7Level: &e" + randomLevel));
        lore.add(Color.ify("&7Protection: &e" + randomProtection));
        lore.add(Color.ify("&7Health: &e" + randomHealth));
        lore.add(Color.ify("&7Regen: &e" + randomRegen));
        lore.add(Color.ify("&7&m----------------"));

        for(int i = 1; i < randomScroll; i++){
            lore.add(Color.ify("&7Scroll Socket [#&7" + i + "&7]"));
        }
        lore.add(Color.ify("&7&m----------------"));

        for(int i = 1; i < randomGem; i++){
            lore.add(Color.ify("&7Gem Socket [#&7" + i + "&7]"));
        }

        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        meta.setUnbreakable(true);

        item.setItemMeta(meta);
        return item;

    }

    public Double getProtection(ItemStack item){
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return 0.0;

        String line = null;
        for (String string : meta.getLore()){
            if (string.contains("Protection: ")){
                line = Color.strip(string).replace("Protection: ", "");
                return Double.parseDouble(line);
            }
        }
        return 0.0;
    }

    public Double getHealth(ItemStack item){
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        if (!((ItemMeta) meta).hasLore()) return 0.0;

        String line = null;
        for (String string : meta.getLore()){
            if (string.contains("Health: ")){
                line = Color.strip(string).replace("Health: ", "");
                return Double.parseDouble(line);
            }
        }
        return 0.0;
    }

    public Double getRegen(ItemStack item){
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        if (!((ItemMeta) meta).hasLore()) return 0.0;

        String line = null;
        for (String string : meta.getLore()){
            if (string.contains("Regen: ")){
                line = Color.strip(string).replace("Regen: ", "");
                return Double.parseDouble(line);
            }
        }
        return 0.0;
    }


    public double getDefaultProtection(ItemStack item){
        if (item == null) return 0;

        Material material = item.getType();
        switch (material){
            case LEATHER_HELMET:
            case LEATHER_BOOTS:
            case GOLDEN_BOOTS:
            case CHAINMAIL_BOOTS:
                return 1;
            case LEATHER_LEGGINGS:
            case GOLDEN_HELMET:
            case CHAINMAIL_HELMET:
            case IRON_HELMET:
            case IRON_BOOTS:
                return 2;
            case LEATHER_CHESTPLATE:
            case GOLDEN_LEGGINGS:
            case DIAMOND_HELMET:
            case DIAMOND_BOOTS:
                return 3;
            case CHAINMAIL_LEGGINGS:
                return 4;
            case GOLDEN_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case IRON_LEGGINGS:
                return 5;
            case IRON_CHESTPLATE:
            case DIAMOND_LEGGINGS:
                return 6;
            case DIAMOND_CHESTPLATE:
                return 8;
            default:
                return 0;
        }
    }

}
