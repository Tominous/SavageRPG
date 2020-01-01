package net.prosavage.illyriarpg.builder;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import net.prosavage.illyriarpg.utils.Color;
import net.prosavage.illyriarpg.utils.INumber;
import net.prosavage.illyriarpg.utils.NullValues;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.text.DecimalFormat;
import java.util.*;

public class Armor {

    Color Color = new Color();
    INumber INumber = new INumber();
    NullValues NullValues = new NullValues();

    private final ItemStack itemStack;
    private final ItemMeta meta;

    private String name;
    private String material;
    private String rarity;
    private double chance;
    private List<String> backgroundLore = Collections.singletonList("");
    private int level;

    private double minimumProtection;
    private double maximumProtection;
    private double protection;
    private double minimumHealth;
    private double maximumHealth;
    private double health;
    private double minimumRegen;
    private double maximumRegen;
    private double regen;

    private int scrolls;
    private int gems;

    private byte spawnedIn;
    private String itemCreator;
    private boolean unbreakable = true;

    public Armor(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
    }

    public Armor(ItemStack itemStack, String name, String material, String rarity, double chance,
                 List<String> backgroundLore, int level) {
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.chance = chance;
        this.backgroundLore = backgroundLore;
        this.level = level;
    }

    public Armor setName(String name) {
        this.name = name;
        return this;
    }

    public Armor setMaterial(String material) {
        this.material = material;
        return this;
    }

    public Armor setRarity(String rarity) {
        this.rarity = rarity;
        return this;
    }

    public Armor setChance(double chance) {
        this.chance = chance;
        return this;
    }

    public Armor setBackgroundLore(String string) {
        this.backgroundLore = Arrays.asList(string.split("\\|\\|"));
        return this;
    }

    public Armor setBackgroundLore(List<String> string) {
        this.backgroundLore = string;
        return this;
    }

    public Armor setLevel(int level) {
        this.level = level;
        return this;
    }

    public Armor setMinimumProtection(double minimumProtection) {
        this.minimumProtection = minimumProtection;
        return this;
    }

    public Armor setMaximumProtection(double maximumProtection) {
        this.maximumProtection = maximumProtection;
        return this;
    }

    public Armor setMaximumHealth(double maximumHealth) {
        this.maximumHealth = maximumHealth;
        return this;
    }

    public Armor setMinimumHealth(double minimumHealth) {
        this.minimumHealth = minimumHealth;
        return this;
    }

    public Armor setMaximumRegen(double maximumRegen) {
        this.maximumRegen = maximumRegen;
        return this;
    }

    public Armor setMinimumRegen(double minimumRegen) {
        this.minimumRegen = minimumRegen;
        return this;
    }

    public Armor setScrolls(int scrolls) {
        this.scrolls = scrolls;
        return this;
    }

    public Armor setGems(int gems) {
        this.gems = gems;
        return this;
    }

    public Armor setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public Armor setSpawnedIn(byte spawnedIn) {
        this.spawnedIn = spawnedIn;
        return this;
    }

    public Armor setItemCreator(String itemCreator) {
        this.itemCreator = itemCreator;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getMaterial() {
        return this.material;
    }

    public String getRarity() {
        return this.rarity;
    }

    public double getChance() {
        return this.chance;
    }

    public List<String> getBackgroundLore() {
        return this.backgroundLore;
    }

    public int getLevel() {
        return this.level;
    }

    public double getMinimumProtection() {
        return this.minimumProtection;
    }

    public double getMaximumProtection() {
        return this.maximumProtection;
    }

    public double getMaximumHealth() {
        return this.maximumHealth;
    }

    public double getMinimumHealth() {
        return this.minimumHealth;
    }

    public double getMaximumRegen() {
        return this.maximumRegen;
    }

    public double getMinimumRegen() {
        return this.minimumRegen;
    }

    public int getScrolls() {
        return this.scrolls;
    }

    public int getGems() {
        return this.gems;
    }

    public boolean getUnbreakable() {
        return this.unbreakable;
    }

    public byte getSpawnedIn() {
        return this.spawnedIn;
    }

    public String getItemCreator() {
        return this.itemCreator;
    }

    public ItemStack build() {
        this.protection = INumber.getDouble(this.minimumProtection, maximumProtection);
        this.health = INumber.getDouble(this.minimumHealth, maximumHealth);
        this.regen = INumber.getDouble(this.minimumRegen, maximumRegen);
        setPersistentDataContainers();
        if (meta != null) {
            meta.setDisplayName(this.name);
            removeUnusedNamespacedKeys();
            List<String> itemLore = IllyriaRPG.getInstance().getConfig().getStringList("armor.lore");
            itemLore = parseBasicLore(itemLore, this.material, this.rarity, this.level, this.protection, this.health, this.regen);
            itemLore = parseGemLore(itemLore, this.gems);
            itemLore = parseScrollLore(itemLore, this.scrolls);
            itemLore = parseBackgroundLore(itemLore, this.backgroundLore);
            meta.setUnbreakable(this.unbreakable);
            meta.setLore(itemLore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

    public void setPersistentDataContainers(){
        String backgroundLore = null;
        if (this.backgroundLore != null){
            backgroundLore = String.join("||", this.backgroundLore);
        }
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(INamespacedKeys.ARMOR_NAME, PersistentDataType.STRING, this.name);
        persistentDataContainer.set(INamespacedKeys.ARMOR_MATERIAL, PersistentDataType.STRING, this.material);
        persistentDataContainer.set(INamespacedKeys.ARMOR_RARITY, PersistentDataType.STRING, this.rarity);
        persistentDataContainer.set(INamespacedKeys.ARMOR_CHANCE, PersistentDataType.DOUBLE, this.chance);
        if (backgroundLore != null && (!backgroundLore.equals("null"))) {
            persistentDataContainer.set(INamespacedKeys.ARMOR_BACKGROUND_LORE, PersistentDataType.STRING, backgroundLore);
        }
        persistentDataContainer.set(INamespacedKeys.ARMOR_LEVEL, PersistentDataType.INTEGER, this.level);
        persistentDataContainer.set(INamespacedKeys.ARMOR_MINIMUM_PROTECTION, PersistentDataType.DOUBLE, this.minimumProtection);
        persistentDataContainer.set(INamespacedKeys.ARMOR_MAXIMUM_PROTECTION, PersistentDataType.DOUBLE, this.maximumProtection);
        persistentDataContainer.set(INamespacedKeys.ARMOR_PROTECTION, PersistentDataType.DOUBLE, this.protection);
        persistentDataContainer.set(INamespacedKeys.ARMOR_MINIMUM_HEALTH, PersistentDataType.DOUBLE, this.minimumHealth);
        persistentDataContainer.set(INamespacedKeys.ARMOR_MAXIMUM_HEALTH, PersistentDataType.DOUBLE, this.maximumHealth);
        persistentDataContainer.set(INamespacedKeys.ARMOR_HEALTH, PersistentDataType.DOUBLE, this.health);
        persistentDataContainer.set(INamespacedKeys.ARMOR_MINIMUM_REGEN, PersistentDataType.DOUBLE, this.minimumRegen);
        persistentDataContainer.set(INamespacedKeys.ARMOR_MAXIMUM_REGEN, PersistentDataType.DOUBLE, this.maximumRegen);
        persistentDataContainer.set(INamespacedKeys.ARMOR_REGEN, PersistentDataType.DOUBLE, this.regen);
        persistentDataContainer.set(INamespacedKeys.ARMOR_GEM, PersistentDataType.INTEGER, this.gems);
        persistentDataContainer.set(INamespacedKeys.ARMOR_SCROLL, PersistentDataType.INTEGER, this.scrolls);
        persistentDataContainer.set(INamespacedKeys.ARMOR_IS_SPAWNED_IN, PersistentDataType.BYTE, this.spawnedIn);
        if (this.itemCreator != null) {
            persistentDataContainer.set(INamespacedKeys.CREATOR_ARMOR_PLAYER, PersistentDataType.STRING, this.itemCreator);
        }
    }

    private List<String> parseBasicLore(List<String> lore, String armorType, String armorRarity, int armorLevel,
                                        double armorProtection, double armorHealth, double armorRegen){
        List<String> newLore = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (String s : lore) {
            s = s.replace("{armor-type}", armorType)
                    .replace("{armor-rarity}", armorRarity)
                    .replace("{armor-required-level}", String.valueOf(armorLevel))
                    .replace("{armor-protection}", decimalFormat.format(armorProtection))
                    .replace("{armor-health}", decimalFormat.format(armorHealth))
                    .replace("{armor-regen}", decimalFormat.format(armorRegen));
            newLore.add(Color.ify(s));
        }
        return newLore;
    }

    private List<String> parseGemLore(List<String> lore, int gem) {
        boolean noGem = false;
        if (gem <= 0) {
            noGem = true;
        }
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.ify(s));
            if (noGem && s.contains("{armor-gem-sockets}")) {
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(Color.ify(s));
                newLore.remove(Color.ify(s));
            }

            if (!noGem && s.contains("{armor-gem-sockets}")) {
                newLore.remove(Color.ify(s));
                for (int i = 0; i < gem; i++) newLore.add(Color.ify("&7» [ Gem Socket # " + i + " ]"));
            }
        }
        return newLore;
    }

    private List<String> parseScrollLore(List<String> lore, int scroll) {
        boolean noScroll = false;
        if (scroll <= 0) {
            noScroll = true;
        }
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.ify(s));
            if (noScroll && s.contains("{armor-scroll-sockets}")) {
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(Color.ify(s));
            }

            if (!noScroll && s.contains("{armor-scroll-sockets}")) {
                newLore.remove(Color.ify(s));
                for (int i = 0; i < scroll; i++) newLore.add(Color.ify("&7» [ Scroll Socket # " + i + " ]"));
            }
        }
        return newLore;
    }

    private List<String> parseBackgroundLore(List<String> lore, List<String> backgroundLore) {
        boolean hasBackgroundLore = false;
        if (backgroundLore != null && !(backgroundLore.equals(Collections.singletonList("null"))) && !(backgroundLore.equals(Collections.singletonList("")))) {
            hasBackgroundLore = true;
        }
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.ify(s));
            if (!hasBackgroundLore && (s.contains("Armor Background Lore") && s.contains("{armor-background-lore}"))){
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(Color.ify(s));
            }
            if (hasBackgroundLore && s.contains("{armor-background-lore}")) {
                newLore.remove(Color.ify(s));
                newLore.add(Color.ify(s).replace("{armor-background-lore}", "").replace(":", ""));
                for (String e : backgroundLore) {
                    newLore.add(Color.ify("&7» &f" + e));
                }
            }
        }
        return newLore;
    }

    public void removeUnusedNamespacedKeys() {
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        PersistentDataType[] namespacedKeyDataType = INamespacedKeys.getAllItemDataType();
        NamespacedKey[] namespacedKeys = INamespacedKeys.getAllItemNamespacedKeys();
        int i = 0;
        for (NamespacedKey key : namespacedKeys) {
            PersistentDataType a = namespacedKeyDataType[i];
            if (persistentDataContainer.has(key, a)){
                if (NullValues.checkForNullValues(persistentDataContainer.get(key, a))) {
                    persistentDataContainer.remove(key);
                }
            }
            i = i + 1;
        }
    }

}
