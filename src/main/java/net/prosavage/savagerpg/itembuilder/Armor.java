package net.prosavage.savagerpg.itembuilder;

import net.prosavage.savagerpg.utils.Chance;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Armor {

    Color Color = new Color();
    Number Number = new Number();
    FileConfiguration armorConfig = SavageRPG.getInstance().getArmorConfig();
    Chance Chance = new Chance();

    NamespacedKey NSK_ARMOR_TYPE = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Type");
    NamespacedKey NSK_ARMOR_RARITY = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Rarity");
    NamespacedKey NSK_ARMOR_CLASS = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Class");
    NamespacedKey NSK_ARMOR_LEVEL = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Level");
    NamespacedKey NSK_ARMOR_PROTECTION = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Protection");
    NamespacedKey NSK_ARMOR_HEALTH = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Health");
    NamespacedKey NSK_ARMOR_REGEN = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Regen");
    NamespacedKey NSK_ARMOR_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Gem");
    NamespacedKey NSK_ARMOR_HAVE_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-NoGem");

    public ItemStack getNewArmor() {
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

    public ItemStack setItem(ItemStack item) {

        double hpHighest = 0.0;
        double hpLowest = 0.0;
        double prHighest = 0.0;
        double prLowest = 0.0;
        double reHighest = 0.0;
        double reLowest = 0.0;
        int geHighest = 3;
        int geLowest = 1;
        String rarity = "broken";
        String itemType = "";

        boolean chanceOf = false;

        ItemMeta meta = item.getItemMeta();
        assert meta != null;

        LinkedList<String> itemlist = new LinkedList<String>();

        itemlist.addAll(Objects.requireNonNull(SavageRPG.getInstance().getArmorConfig().getConfigurationSection("rarity")).getKeys(false));

        for (int i = 0; i < 10; i++) {

            Integer randomNumber = Number.getInteger(0, itemlist.size() - 1);
            String string = itemlist.get(randomNumber);
            itemType = string;

            prHighest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-protection")));
            prLowest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-protection")));

            hpHighest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-health")));
            hpLowest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-health")));

            reHighest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-regen")));
            reLowest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-regen")));

            geHighest = Integer.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-gem")));
            geLowest = Integer.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-gem")));

            chanceOf = Chance.ofDouble(Double.parseDouble(String.valueOf(armorConfig.get("rarity." + itemType + ".chance"))));
            if (chanceOf) {
                rarity = itemType;
                i = 10;
            }
        }

        if (chanceOf) {
            int level = Number.getInteger(1, 100);

            double protection = Double.valueOf(String.format("%.2f", Number.getDouble(prLowest, prHighest)));

            double health = Double.valueOf(String.format("%.2f", Number.getDouble(hpLowest, hpHighest)));

            double regen = Double.valueOf(String.format("%.2f", Number.getDouble(reLowest, reHighest)));

            int gem = Number.getInteger(geLowest, geHighest);

            List<String> lore = SavageRPG.getInstance().getConfig().getStringList("armor.lore");

            boolean noGem = false;

            if (gem == 0) {
                noGem = true;
            }

            String itemName = item.getType().toString().replace("_", " ").replace("(?:(?<=^)|(?<=[^\\w]))\\w", "\\U$0\\E");

            meta.getPersistentDataContainer().set(NSK_ARMOR_TYPE, PersistentDataType.STRING, itemName);
            meta.getPersistentDataContainer().set(NSK_ARMOR_RARITY, PersistentDataType.STRING, rarity);
            meta.getPersistentDataContainer().set(NSK_ARMOR_CLASS, PersistentDataType.STRING, "null");
            meta.getPersistentDataContainer().set(NSK_ARMOR_LEVEL, PersistentDataType.INTEGER, level);
            meta.getPersistentDataContainer().set(NSK_ARMOR_PROTECTION, PersistentDataType.DOUBLE, protection);
            meta.getPersistentDataContainer().set(NSK_ARMOR_HEALTH, PersistentDataType.DOUBLE, health);
            meta.getPersistentDataContainer().set(NSK_ARMOR_REGEN, PersistentDataType.DOUBLE, regen);
            meta.getPersistentDataContainer().set(NSK_ARMOR_GEM, PersistentDataType.INTEGER, gem);
            meta.getPersistentDataContainer().set(NSK_ARMOR_HAVE_GEM, PersistentDataType.STRING, String.valueOf(noGem));

            meta.setLore(lore);
            item.setItemMeta(meta);

            return item;
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack setLore(ItemStack item) {
        if (item.getType() != Material.AIR) {
            ItemMeta meta = item.getItemMeta();
            assert meta != null;

            String itemName = meta.getPersistentDataContainer().get(NSK_ARMOR_TYPE, PersistentDataType.STRING);
            String rarity = meta.getPersistentDataContainer().get(NSK_ARMOR_RARITY, PersistentDataType.STRING);
            String classType = meta.getPersistentDataContainer().get(NSK_ARMOR_CLASS, PersistentDataType.STRING);
            Integer level = meta.getPersistentDataContainer().get(NSK_ARMOR_LEVEL, PersistentDataType.INTEGER);
            Double protection = meta.getPersistentDataContainer().get(NSK_ARMOR_PROTECTION, PersistentDataType.DOUBLE);
            Double health = meta.getPersistentDataContainer().get(NSK_ARMOR_HEALTH, PersistentDataType.DOUBLE);
            Double regen = meta.getPersistentDataContainer().get(NSK_ARMOR_REGEN, PersistentDataType.DOUBLE);
            Integer gem = meta.getPersistentDataContainer().get(NSK_ARMOR_GEM, PersistentDataType.INTEGER);
            String noGem = meta.getPersistentDataContainer().get(NSK_ARMOR_HAVE_GEM, PersistentDataType.STRING);

            assert gem != null;

            List<String> lore = SavageRPG.getInstance().getConfig().getStringList("armor.lore");


            for (int i = 0; i < lore.size(); i++) {
                String string = lore.get(i);
                string = string.replace("{armor-type}", itemName);
                string = string.replace("{armor-rarity}", rarity);
                string = string.replace("{armor-class}", classType);
                string = string.replace("{armor-required-level}", String.valueOf(level));
                string = string.replace("{armor-protection}", String.valueOf(protection));
                string = string.replace("{armor-health}", String.valueOf(health));
                string = string.replace("{armor-regen}", String.valueOf(regen));

                if (Boolean.valueOf(String.valueOf(noGem))) {
                    string = string.replace("{armor-gem-sockets}", "");
                }

                lore.set(i, Color.ify(string));

                if (!(Boolean.valueOf(String.valueOf(noGem))) && string.contains("{armor-gem-sockets}")) {
                    String gemName = lore.get(i);
                    int gemIndex = lore.indexOf(gemName);
                    lore.remove(gemName);
                    lore.remove(gemIndex);
                    for (int a = 0; a < gem; a++) lore.add(Color.ify("&7» [ Gems Socket # " + a + " ]"));
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }
        return new ItemStack(Material.AIR);
    }

    public String getType(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return null;
        ItemMeta meta = item.getItemMeta();
        if ((Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return null;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_TYPE, PersistentDataType.STRING));
    }

    public String getRarity(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return null;
        ItemMeta meta = item.getItemMeta();
        if ((Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return null;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_RARITY, PersistentDataType.STRING));
    }

    public String getClass(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return null;
        ItemMeta meta = item.getItemMeta();
        if ((Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return null;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_CLASS, PersistentDataType.STRING));
    }

    public int getLevel(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return 0;
        ItemMeta meta = item.getItemMeta();
        if ((Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return 0;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_LEVEL, PersistentDataType.INTEGER));
    }

    public double getProtection(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return 0.0;
        ItemMeta meta = item.getItemMeta();
        if ((Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return 0.0;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_PROTECTION, PersistentDataType.DOUBLE));
    }

    public double getHealth(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return 0.0;
        ItemMeta meta = item.getItemMeta();
        if ((Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return 0.0;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_HEALTH, PersistentDataType.DOUBLE));
    }

    public double getRegen(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return 0.0;
        ItemMeta meta = item.getItemMeta();
        if (!(Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return 0.0;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_REGEN, PersistentDataType.DOUBLE));
    }

    public int getGem(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return 0;
        ItemMeta meta = item.getItemMeta();
        if (!(Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return 0;

        return Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_GEM, PersistentDataType.INTEGER));
    }

    public boolean haveGem(ItemStack item) {
        if ((item == null) || (!item.hasItemMeta())) return false;
        ItemMeta meta = item.getItemMeta();
        if (!(Objects.requireNonNull(meta).getPersistentDataContainer().isEmpty())) return false;

        return Boolean.valueOf(Objects.requireNonNull(meta.getPersistentDataContainer().get(NSK_ARMOR_HAVE_GEM, PersistentDataType.STRING)));
    }
}