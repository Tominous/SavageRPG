package net.prosavage.savagerpg.itembuilder;

import com.google.common.collect.ImmutableList;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.utils.files.SWeaponFiles;
import net.prosavage.savagerpg.utils.Chance;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Weapon {

    Number Number = new Number();
    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    SWeaponFiles SWeaponFiles = new SWeaponFiles();
    Chance Chance = new Chance();
    Color Color = new Color();


    public ItemStack getRandomItem() {
        List<ItemStack> weapons = new ArrayList<>(ImmutableList.of(new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.IRON_SWORD), new ItemStack(Material.STONE_SWORD), new ItemStack(Material.GOLDEN_SWORD), new ItemStack(Material.WOODEN_SWORD)));
        List<String> fileRarity = SWeaponFiles.getRarityNames();
        Integer randomNumber = Number.getInteger(0, weapons.size() - 1);
        ItemStack weaponType = weapons.get(randomNumber);
        String selectedRarity = "common";
        String string = null;
        boolean chanceOf = false;
        for (int i = 0; i < 10; i++) {
            randomNumber = Number.getInteger(0, fileRarity.size() - 1);
            string = fileRarity.get(randomNumber);
            chanceOf = Chance.ofDouble(Double.parseDouble(String.valueOf(SWeaponFiles.getWeaponConfig().get("rarity." + string + ".chance"))));
            if (chanceOf) {
                selectedRarity = string;
                i = 10;
            }
        }
        if (chanceOf){
            List<String> weaponNames = SWeaponFiles.getRarityWeaponNames(selectedRarity);
            randomNumber = Number.getInteger(0, weaponNames.size() - 1);
            string = weaponNames.get(randomNumber);
            string = string.replace(".yml", "");
            ItemMeta meta = weaponType.getItemMeta();
            double minDamage = Double.valueOf(String.format("%.2f", getYAMLMinDamage(selectedRarity, string)));
            double maxDamage = Double.valueOf(String.format("%.2f", getYAMLMaxDamage(selectedRarity, string)));
            int level = Number.getInteger(getYAMLMinLevel(selectedRarity, string), getYAMLMaxLevel(selectedRarity, string));
            int gem = Number.getInteger(getYAMLMinGem(selectedRarity, string), getYAMLMaxGem(selectedRarity, string));
            double cooldown = Double.valueOf(String.format("%.2f", Number.getDouble(getYAMLMinCooldown(selectedRarity, string), getYAMLMaxCooldown(selectedRarity, string))));

            if (gem < 0){
                Objects.requireNonNull(weaponType.getItemMeta()).getPersistentDataContainer().set(SNamespacedKey.getDoesItemHaveGems(), PersistentDataType.STRING, "true");
            }
            String noGem = Objects.requireNonNull(weaponType.getItemMeta()).getPersistentDataContainer().get(SNamespacedKey.getDoesItemHaveGems(), PersistentDataType.STRING);


            assert meta != null;
            setName(meta, getYAMLName(selectedRarity, string));
            setMaterial(meta, getYAMLMaterial(selectedRarity, string));
            setRarity(meta, selectedRarity);
            setLevel(meta, level);
            setMaxDamage(meta, maxDamage);
            setMinDamage(meta, minDamage);
            setGem(meta, gem);
            setCooldown(meta, cooldown);

            weaponType.setItemMeta(meta);
            String material = getMaterial(weaponType);

            List<String> lore = SavageRPG.getInstance().getConfig().getStringList("weapon.lore");
            for (int i = 0; i < lore.size(); i++) {
                String s = lore.get(i);
                if ((material != null) && (selectedRarity != null)) {
                    s = s.replace("{weapon-type}", material)
                            .replace("{weapon-rarity}", selectedRarity)
                            .replace("{weapon-required-level}", String.valueOf(level))
                            .replace("{weapon-min-damage}", String.valueOf(minDamage))
                            .replace("{weapon-max-damage}", String.valueOf(maxDamage))
                            .replace("{weapon-cooldown}", String.valueOf(cooldown));
                    if (Boolean.valueOf(String.valueOf(noGem))) {
                        s = s.replace("{weapon-gem-sockets}", "");
                    }

                    lore.set(i, Color.ify(s));

                    if (!(Boolean.valueOf(String.valueOf(noGem))) && s.contains("{weapon-gem-sockets}")) {
                        String gemName = lore.get(i);
                        int gemIndex = lore.indexOf(gemName);
                        lore.remove(gemName);
                        lore.remove(gemIndex);
                        for (int a = 0; a < gem; a++) lore.add(Color.ify("&7» [ Gems Socket # " + a + " ]"));
                    }
                }

            }

            meta.setLore(lore);
            if (SavageRPG.getInstance().getConfig().getBoolean("weapon.unbreakable")){
                meta.setUnbreakable(true);
            }
            weaponType.setItemMeta(meta);

            return weaponType;

        }
        return new ItemStack(Material.AIR);
    }

    public void setName(ItemMeta meta, String name) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemName(), PersistentDataType.STRING, name);
        meta.setDisplayName(name);
    }

    public void setMaterial(ItemMeta meta, String material) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemMaterial(), PersistentDataType.STRING, material);
    }

    public void setRarity(ItemMeta meta, String rarity) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemRarity(), PersistentDataType.STRING, rarity);
    }

    public void setChance(ItemMeta meta, double chance) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemChance(), PersistentDataType.DOUBLE, chance);
    }

    public void setLore(ItemMeta meta, String string) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemLore(), PersistentDataType.STRING, string);
    }

    public void setLevel(ItemMeta meta, int level) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemLevel(), PersistentDataType.INTEGER, level);
    }

    public void setMaxDamage(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemMaxDamage(), PersistentDataType.DOUBLE, damage);
    }

    public void setMinDamage(ItemMeta meta, double damage) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemMinDamage(), PersistentDataType.DOUBLE, damage);
    }

    public void setGem(ItemMeta meta, int gem) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemGem(), PersistentDataType.INTEGER, gem);
    }

    public void setCooldown(ItemMeta meta, double cooldown) {
        assert meta != null;
        meta.getPersistentDataContainer().set(SNamespacedKey.getItemCooldown(), PersistentDataType.DOUBLE, cooldown);
    }

    public String getMaterial(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemMaterial(), PersistentDataType.STRING);
        }
        return "";
    }

    public String getName(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemName(), PersistentDataType.STRING);
        }
        return "";
    }

    public String getRarity(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemRarity(), PersistentDataType.STRING);
        }
        return "";
    }

    public Integer getLevel(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemLevel(),PersistentDataType.INTEGER);
        }
        return 1;
    }

    public Double getMaxDamage(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemMaxDamage(),PersistentDataType.DOUBLE);
        }
        return 1.0;
    }

    public Double getMinDamage(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemMinDamage(),PersistentDataType.DOUBLE);
        }
        return 1.0;
    }

    public Integer getGem(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemGem(),PersistentDataType.INTEGER);
        }
        return 0;
    }

    public Double getCooldown(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (!(meta == null)) {
            return meta.getPersistentDataContainer().get(SNamespacedKey.getItemCooldown(),PersistentDataType.DOUBLE);
        }
        return 1.0;
    }
    public String getYAMLName(String rarity,  String name){
        return SWeaponFiles.getStat(rarity, name, "item-name");
    }
    public String getYAMLMaterial(String rarity,  String name){
        return SWeaponFiles.getStat(rarity, name, "materials");
    }

    public String getYAMLRarity(String rarity,  String name){
        return SWeaponFiles.getStat(rarity, name, "rarity");
    }

    public String getYAMLChance(String rarity,  String name){
        return SWeaponFiles.getStat(rarity, name, "chance");
    }

    public String[] getYAMLLore(String rarity, String name){
        return SWeaponFiles.getLore(rarity, name);
    }

    public int getYAMLMaxLevel(String rarity, String name){
        return Integer.valueOf(SWeaponFiles.getStat(rarity, name, "max-level"));
    }

    public int getYAMLMinLevel(String rarity, String name){
        return Integer.valueOf(SWeaponFiles.getStat(rarity, name, "min-level"));
    }

    public double getYAMLMaxDamage(String rarity,  String name){
        return Double.valueOf(SWeaponFiles.getStat(rarity, name, "max-damage"));
    }

    public double getYAMLMinDamage(String rarity,  String name){
        return Double.valueOf(SWeaponFiles.getStat(rarity, name, "min-damage"));
    }

    public int getYAMLMaxGem(String rarity,  String name){
        return Integer.valueOf(SWeaponFiles.getStat(rarity, name, "max-gem"));
    }

    public int getYAMLMinGem(String rarity,  String name){
        return Integer.valueOf(SWeaponFiles.getStat(rarity, name, "min-gem"));
    }

    public double getYAMLMaxCooldown(String rarity,  String name){
        return Double.valueOf(SWeaponFiles.getStat(rarity, name, "max-cooldown"));
    }

    public double getYAMLMinCooldown(String rarity,  String name){
        return Double.valueOf(SWeaponFiles.getStat(rarity, name, "min-cooldown"));
    }

    public String getYAMLAbility(String rarity,  String name){
        return SWeaponFiles.getStat(rarity, name, "ability");
    }


}

