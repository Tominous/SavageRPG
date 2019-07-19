package net.prosavage.savagerpg.itembuilder;

import com.google.common.collect.ImmutableList;
import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Chance;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.files.SArmorFiles;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Armor {

    net.prosavage.savagerpg.utils.Number Number = new Number();
    net.prosavage.savagerpg.SNamespacedKey SNamespacedKey = new SNamespacedKey();
    SArmorFiles SArmorFiles = new SArmorFiles();
    net.prosavage.savagerpg.utils.Chance Chance = new Chance();
    net.prosavage.savagerpg.utils.Color Color = new Color();

    public ItemStack getRandomItem(){
        List<ItemStack> armorTypes = new ArrayList<>(ImmutableList.of(new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.GOLDEN_HELMET), new ItemStack(Material.GOLDEN_CHESTPLATE), new ItemStack(Material.GOLDEN_LEGGINGS), new ItemStack(Material.GOLDEN_BOOTS), new ItemStack(Material.CHAINMAIL_HELMET), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.CHAINMAIL_BOOTS), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.LEATHER_BOOTS)));
        List<String> fileRarity = SArmorFiles.getRarityNames();
        Integer randomNumber = Number.getInteger(0, armorTypes.size() - 1);
        ItemStack armorType = armorTypes.get(randomNumber);
        String selectedRarity = "common";
        String string = null;
        boolean chanceOf = false;
        for (int i = 0; i < 10; i++) {
            randomNumber = Number.getInteger(0,fileRarity.size() - 1);
            string = fileRarity.get(randomNumber);
            chanceOf = Chance.ofDouble(Double.parseDouble(String.valueOf(SArmorFiles.getArmorConfig().get("rarity." + string + ".chance"))));
            if (chanceOf) {
                selectedRarity = string;
                i = 10;
            }
        }
        if (chanceOf){
            List<String> weaponNames = SArmorFiles.getRarityArmorNames(selectedRarity);
            randomNumber = Number.getInteger(0, weaponNames.size() - 1);
            string = weaponNames.get(randomNumber);
            string = string.replace(".yml", "");
            ItemMeta meta = armorType.getItemMeta();
            double minProtection = Double.valueOf(String.format("%.2f", getYAMLMinProtection(selectedRarity, string)));
            double maxProtection = Double.valueOf(String.format("%.2f", getYAMLMaxProtection(selectedRarity, string)));
            double minHealth = Double.valueOf(String.format("%.2f", getYAMLMinHealth(selectedRarity, string)));
            double maxHealth = Double.valueOf(String.format("%.2f", getYAMLMaxHealth(selectedRarity, string)));
            double minRegen = Double.valueOf(String.format("%.2f", getYAMLMinRegen(selectedRarity, string)));
            double maxRegen = Double.valueOf(String.format("%.2f", getYAMLMaxRegen(selectedRarity, string)));
            double protection = Double.valueOf(String.format("%.2f", Number.getDouble(minProtection, maxProtection)));
            double health = Double.valueOf(String.format("%.2f", Number.getDouble(minHealth, maxHealth)));
            double regen = Double.valueOf(String.format("%.2f", Number.getDouble(minRegen, maxRegen)));
            int level = Number.getInteger(getYAMLMinLevel(selectedRarity, string), getYAMLMaxLevel(selectedRarity, string));
            int gem = Number.getInteger(getYAMLMinGem(selectedRarity, string), getYAMLMaxGem(selectedRarity, string));

            if (gem < 0){
                Objects.requireNonNull(armorType.getItemMeta()).getPersistentDataContainer().set(SNamespacedKey.getDoesItemHaveGems(), PersistentDataType.STRING, "true");
            }
            String noGem = Objects.requireNonNull(armorType.getItemMeta()).getPersistentDataContainer().get(SNamespacedKey.getDoesItemHaveGems(), PersistentDataType.STRING);


            assert meta != null;
            setName(meta, getYAMLName(selectedRarity, string));
            setMaterial(meta, getYAMLMaterial(selectedRarity, string));
            setRarity(meta, selectedRarity);
            setLevel(meta, level);
            setMaxProtection(meta, maxProtection);
            setMinProtection(meta, minProtection);
            setProtection(meta, protection);
            setMaxHealth(meta, maxHealth);
            setMinHealth(meta, minHealth);
            setHealth(meta, health);
            setMaxRegen(meta, maxRegen);
            setMinRegen(meta, minRegen);
            setRegen(meta, regen);
            setGem(meta, gem);

            armorType.setItemMeta(meta);
            String material = getMaterial(armorType);

            List<String> lore = SavageRPG.getInstance().getConfig().getStringList("armor.lore");
            for (int i = 0; i < lore.size(); i++) {
                String s = lore.get(i);
                if ((material != null) && (selectedRarity != null)) {
                    s = s.replace("{armor-type}", material)
                            .replace("{armor-rarity}", selectedRarity)
                            .replace("{armor-required-level}", String.valueOf(level))
                            .replace("{armor-protection}", String.valueOf(protection))
                            .replace("{armor-health}", String.valueOf(health))
                            .replace("{armor-regen}", String.valueOf(regen));

                    if (Boolean.valueOf(String.valueOf(noGem))) {
                        s = s.replace("{armor-gem-sockets}", "");
                    }

                    lore.set(i, Color.ify(s));

                    if (!(Boolean.valueOf(String.valueOf(noGem))) && s.contains("{armor-gem-sockets}")) {
                        String gemName = lore.get(i);
                        int gemIndex = lore.indexOf(gemName);
                        lore.remove(gemName);
                        lore.remove(gemIndex);
                        for (int a = 0; a < gem; a++) lore.add(Color.ify("&7» [ Gems Socket # " + a + " ]"));
                    }
                }

            }

            meta.setLore(lore);
            if (SavageRPG.getInstance().getConfig().getBoolean("armor.unbreakable")){
                meta.setUnbreakable(true);
            }
            armorType.setItemMeta(meta);
            return armorType;

        }
        return new ItemStack(Material.AIR);
    }

    public void setName(ItemMeta meta,String name) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorName(), PersistentDataType.STRING, name);
        meta.setDisplayName(name);
    }

    public void setMaterial(ItemMeta meta, String material) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorMaterial(), PersistentDataType.STRING, material);
    }

    public void setRarity(ItemMeta meta, String rarity) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorRarity(), PersistentDataType.STRING, rarity);
    }

    public void setChance(ItemMeta meta, double chance) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorChance(), PersistentDataType.DOUBLE, chance);
    }

    public void setLore(ItemMeta meta, String string) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorLore(), PersistentDataType.STRING, string);
    }

    public void setLevel(ItemMeta meta, int level) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorLevel(), PersistentDataType.INTEGER, level);
    }

    public void setMaxProtection(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorMaxProtection(), PersistentDataType.DOUBLE, damage);
    }

    public void setProtection(ItemMeta meta, double protection){
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorProtection(), PersistentDataType.DOUBLE, protection);
    }

    public void setMinProtection(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorMinProtection(), PersistentDataType.DOUBLE, damage);
    }

    public void setMaxHealth(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorMaxHealth(), PersistentDataType.DOUBLE, damage);
    }

    public void setHealth(ItemMeta meta, double protection){
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorHealth(), PersistentDataType.DOUBLE, protection);
    }

    public void setMinHealth(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorMinHealth(), PersistentDataType.DOUBLE, damage);
    }

    public void setMaxRegen(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorMaxRegen(), PersistentDataType.DOUBLE, damage);
    }

    public void setRegen(ItemMeta meta, double protection){
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorRegen(), PersistentDataType.DOUBLE, protection);
    }

    public void setMinRegen(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorMinRegen(), PersistentDataType.DOUBLE, damage);
    }

    public void setGem(ItemMeta meta, int gem) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getArmorGem(), PersistentDataType.INTEGER, gem);
    }

    public String getMaterial(ItemStack item){
        if (item.getType() == Material.AIR){
            return "";
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorMaterial(), PersistentDataType.STRING);
    }

    public String getName(ItemStack item){
        if (item.getType() == Material.AIR){
            return "";
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorName(), PersistentDataType.STRING);
    }

    public String getRarity(ItemStack item){
        if (item.getType() == Material.AIR){
            return "";
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorRarity(), PersistentDataType.STRING);
    }

    public Integer getLevel(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorLevel(), PersistentDataType.INTEGER);
    }

    public Double getMaxProtection(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorMaxProtection(), PersistentDataType.DOUBLE);
    }

    public Double getProtection(ItemStack item){
        if (item == null){
            return 0.0;
        }
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorProtection(), PersistentDataType.DOUBLE);
    }

    public Double getMinProtection(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorMinProtection(), PersistentDataType.DOUBLE);
    }

    public Double getMaxHealth(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorMaxHealth(), PersistentDataType.DOUBLE);
    }

    public Double getHealth(ItemStack item){
        if (item == null){
            return 0.0;
        }
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorHealth(), PersistentDataType.DOUBLE);
    }

    public Double getMinHealth(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorMinHealth(), PersistentDataType.DOUBLE);
    }

    public Double getMaxRegen(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorMaxRegen(), PersistentDataType.DOUBLE);
    }

    public Double getRegen(ItemStack item){
        if (item == null){
            return 0.0;
        }
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorRegen(), PersistentDataType.DOUBLE);
    }

    public Double getMinRegen(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorMinRegen(), PersistentDataType.DOUBLE);
    }

    public Integer getGem(ItemStack item){
        if (item.getType() == Material.AIR){
            return 0;
        }
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.getPersistentDataContainer().get(SNamespacedKey.getArmorGem(), PersistentDataType.INTEGER);
    }

    public String getYAMLName(String rarity,  String name){
        return SArmorFiles.getStat(rarity, name, "item-name");
    }
    public String getYAMLMaterial(String rarity,  String name){
        return SArmorFiles.getStat(rarity, name, "materials");
    }

    public String getYAMLRarity(String rarity,  String name){
        return SArmorFiles.getStat(rarity, name, "rarity");
    }

    public String getYAMLChance(String rarity,  String name){
        return SArmorFiles.getStat(rarity, name, "chance");
    }

    public String[] getYAMLLore(String rarity, String name){
        return SArmorFiles.getLore(rarity, name);
    }

    public int getYAMLMaxLevel(String rarity, String name){
        return Integer.valueOf(SArmorFiles.getStat(rarity, name, "max-level"));
    }

    public int getYAMLMinLevel(String rarity, String name){
        return Integer.valueOf(SArmorFiles.getStat(rarity, name, "min-level"));
    }

    public double getYAMLMaxProtection(String rarity,  String name){
        return Double.valueOf(SArmorFiles.getStat(rarity, name, "max-protection"));
    }

    public double getYAMLMinProtection(String rarity,  String name){
        return Double.valueOf(SArmorFiles.getStat(rarity, name, "min-protection"));
    }

    public double getYAMLMaxHealth(String rarity,  String name){
        return Double.valueOf(SArmorFiles.getStat(rarity, name, "max-health"));
    }

    public double getYAMLMinHealth(String rarity,  String name){
        return Double.valueOf(SArmorFiles.getStat(rarity, name, "min-health"));
    }

    public double getYAMLMaxRegen(String rarity,  String name){
        return Double.valueOf(SArmorFiles.getStat(rarity, name, "max-regen"));
    }

    public double getYAMLMinRegen(String rarity,  String name){
        return Double.valueOf(SArmorFiles.getStat(rarity, name, "min-regen"));
    }

    public int getYAMLMaxGem(String rarity,  String name){
        return Integer.valueOf(SArmorFiles.getStat(rarity, name, "max-gem"));
    }

    public int getYAMLMinGem(String rarity,  String name){
        return Integer.valueOf(SArmorFiles.getStat(rarity, name, "min-gem"));
    }


}
