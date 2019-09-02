package net.prosavage.savagerpg.builder;

import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Chance;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.api.files.SWeaponFiles;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Weapon {

    Number Number = new Number();
    SWeaponFiles SWeaponFiles = new SWeaponFiles();
    Chance Chance = new Chance();
    Color Color = new Color();

    private final ItemStack itemStack;
    private final ItemMeta meta;
    private String name;
    private String material;
    private String rarity;
    private double chance;
    private List<String> lore = Collections.singletonList("");
    private int level;
    private double maxDamage;
    private double minDamage;
    private int gem = 0;
    private double cooldown = -1.0;
    private String ability = "null";
    private List<String> description = Collections.singletonList("");
    private String castType = "null";
    private double manaCost = -1.0;
    private String actionType = "null";

    public Weapon(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
    }

    public Weapon(ItemStack itemStack,String name,String material,String rarity,double chance,List<String> lore,int level,double maxDamage,double minDamage,int gem) {
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.chance = chance;
        this.lore = lore;
        this.level = level;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.gem = gem;
    }

    public Weapon(ItemStack itemStack,String name,String material,String rarity,double chance,List<String> lore,int level,double maxDamage,double minDamage,int gem,double cooldown,String abilityName,List<String> abilityDescription,String castType,double mana) {
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.chance = chance;
        this.lore = lore;
        this.level = level;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.gem = gem;
        this.cooldown = cooldown;
        this.ability = abilityName;
        this.description = abilityDescription;
        this.castType = castType;
        this.manaCost = mana;
    }

    public Weapon setName(String name) {
        this.name = name;
        return this;
    }

    public Weapon setMaterial(String material) {
        this.material = material;
        return this;
    }

    public Weapon setRarity(String rarity) {
        this.rarity = rarity;
        return this;
    }

    public Weapon setChance(double chance) {
        this.chance = chance;
        return this;
    }

    public Weapon setLore(String string) {
        this.lore = Arrays.asList(string.split("\\|\\|"));
        return this;
    }

    public Weapon setLore(List<String> string) {
        this.lore = string;
        return this;
    }

    public Weapon setLevel(int level) {
        this.level = level;
        return this;
    }

    public Weapon setMaxDamage(double damage) {
        this.maxDamage = damage;
        return this;
    }

    public Weapon setMinDamage(double damage) {
        this.minDamage = damage;
        return this;
    }

    public Weapon setGem(int gem) {
        this.gem = gem;
        return this;
    }

    public Weapon setAbilityCooldown(double cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public Weapon setAbility(String name) {
        this.ability = name;
        return this;
    }

    public Weapon setAbilityDescription(List<String> description) {
        this.description = description;
        return this;
    }

    public Weapon setAbilityCastType(String castType) {
        this.castType = castType;
        return this;
    }

    public Weapon setAbilityManaCost(double mana) {
        this.manaCost = mana;
        return this;
    }

    public Weapon setAbilityActionType(String actionType) {
        this.actionType = actionType;
        return this;
    }

    public String setDefaultName(ItemStack itemStack){
        String key = String.valueOf(itemStack.getType().getKey()).replace("minecraft:", "");
        String[] split = key.split("_");
        List<String> words = new java.util.ArrayList<>(Collections.singletonList(""));
        for (String s : split){
            s = WordUtils.capitalize(s);
            words.add(s);
        }
        return String.join(" ", words);
    }

    public Weapon setUnbreakable(Boolean unbreakable){
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public Weapon setCustomModelData(Integer i){
        meta.setCustomModelData(i);
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

    public List<String> getLore() {
        return this.lore;
    }

    public int getLevel() {
        return this.level;
    }

    public double getMaxDamage() {
        return this.maxDamage;
    }

    public double getMinDamage() {
        return this.minDamage;
    }

    public int getGem() {
        return this.gem;
    }

    public double getAbilityCooldown() {
        return this.cooldown;
    }

    public String getAbility() {
        return this.ability;
    }

    public List<String> getAbilityDescription() {
        return this.description;
    }

    public String getAbilityCastType() {
        return this.castType;
    }

    public double getAbilityManaCost() {
        return this.manaCost;
    }

    public String getAbilityActionType() {
        return this.actionType;
    }

    public ItemStack createItemFromFiles(String rarity,String weaponName,boolean createItemStack){
        this.name = SWeaponFiles.getName(rarity, weaponName);
        this.material = SWeaponFiles.getMaterial(rarity, weaponName);
        this.rarity = SWeaponFiles.getRarity(rarity, weaponName);
        this.lore = SWeaponFiles.getLore(rarity, weaponName);
        this.level = Number.getInteger(SWeaponFiles.getMinLevel(rarity, weaponName), SWeaponFiles.getMaxLevel(rarity, weaponName));
        this.minDamage = Number.getDouble(SWeaponFiles.getMinDamage(rarity, weaponName), SWeaponFiles.getMaxDamage(rarity, weaponName));
        this.maxDamage = Number.getDouble(this.minDamage, SWeaponFiles.getMaxDamage(rarity, weaponName));
        this.gem = Number.getInteger(SWeaponFiles.getMinGem(rarity, weaponName), SWeaponFiles.getMaxGem(rarity, weaponName));
        this.ability = SWeaponFiles.getAbility(rarity, weaponName);
        this.description = SWeaponFiles.getAbilityDescription(rarity, weaponName);
        this.castType = SWeaponFiles.getAbilityCastType(rarity, weaponName);
        this.actionType = SWeaponFiles.getAbilityActionType(rarity, weaponName);
        this.cooldown = SWeaponFiles.getAbilityCooldown(rarity, weaponName);
        if (createItemStack){
            return build();
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack build() {
        setPersistentDataContainers();
        meta.setDisplayName(this.name);
        List<String> itemLore = SavageRPG.getInstance().getConfig().getStringList("weapon.lore");
        itemLore = parseBasicLore(itemLore, this.material, this.rarity, this.level, this.minDamage, this.maxDamage);
        itemLore = parseGemLore(itemLore, this.gem);
        itemLore = parseAbilityLore(itemLore, this.ability, this.description, this.cooldown, this.manaCost);
        meta.setUnbreakable(true);
        meta.setLore(itemLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public void setPersistentDataContainers(){
        String itemLore = null;
        String itemDescription = null;
        if (this.lore != null){
            itemLore = String.join("||", this.lore);
        }
        if (this.description != null){
            itemDescription = String.join("||", this.description);
        }
        if (this.name == null){
            this.name = setDefaultName(this.itemStack);
        }
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(SNamespacedKeys.ITEM_NAME,PersistentDataType.STRING,this.name);
        persistentDataContainer.set(SNamespacedKeys.ITEM_MATERIAL,PersistentDataType.STRING,this.material);
        persistentDataContainer.set(SNamespacedKeys.ITEM_RARITY,PersistentDataType.STRING,this.rarity);
        persistentDataContainer.set(SNamespacedKeys.ITEM_CHANCE,PersistentDataType.DOUBLE,this.chance);
        persistentDataContainer.set(SNamespacedKeys.ITEM_LORE,PersistentDataType.STRING, itemLore);
        persistentDataContainer.set(SNamespacedKeys.ITEM_LEVEL,PersistentDataType.INTEGER,this.level);
        persistentDataContainer.set(SNamespacedKeys.ITEM_MAX_DAMAGE,PersistentDataType.DOUBLE,this.maxDamage);
        persistentDataContainer.set(SNamespacedKeys.ITEM_MIN_DAMAGE,PersistentDataType.DOUBLE,this.minDamage);
        persistentDataContainer.set(SNamespacedKeys.ITEM_GEM,PersistentDataType.INTEGER,this.gem);
        persistentDataContainer.set(SNamespacedKeys.ITEM_ABIILTY_COOLDOWN,PersistentDataType.DOUBLE,this.cooldown);
        persistentDataContainer.set(SNamespacedKeys.ITEM_ABIILTY_NAME,PersistentDataType.STRING,this.name);
        persistentDataContainer.set(SNamespacedKeys.ITEM_ABIILTY_DESCRIPTION,PersistentDataType.STRING,itemDescription);
        persistentDataContainer.set(SNamespacedKeys.ITEM_ABIILTY_CAST_TYPE,PersistentDataType.STRING,this.castType);
        persistentDataContainer.set(SNamespacedKeys.ITEM_ABIILTY_MANA_COST,PersistentDataType.DOUBLE,this.manaCost);
        persistentDataContainer.set(SNamespacedKeys.ITEM_ABIILTY_ACTION_TYPE,PersistentDataType.STRING,this.actionType);
    }

    public List<String> parseBasicLore(List<String> lore, String weaponType, String weaponRarity, int weaponLevel, double weaponMinDamage, double weaponMaxDamage){
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            s = s.replace("{weapon-type}", weaponType)
            .replace("{weapon-rarity}", weaponRarity)
            .replace("{weapon-required-level}",String.valueOf(weaponLevel))
            .replace("{weapon-min-damage}",String.valueOf(weaponMinDamage))
            .replace("{weapon-max-damage}",String.valueOf(weaponMaxDamage));
            newLore.add(Color.ify(s));
        }
        return newLore;
    }

    public List<String> parseGemLore(List<String> lore, int gem) {
        boolean noGem = false;
        if (Number.isLessOrEqualTo(gem,0)) {
            noGem = true;
        }
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.ify(s));
            if (Boolean.parseBoolean(String.valueOf(noGem)) && s.contains("{weapon-gem-sockets}")) {
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
            }

            if (!(Boolean.parseBoolean(String.valueOf(noGem))) && s.contains("{weapon-gem-sockets}")) {
                newLore.remove(Color.ify(s));
                for (int i = 0; i < gem; i++) newLore.add(Color.ify("&7» [ Gems Socket # " + i + " ]"));
            }
        }
    return newLore;
    }

    public List<String> parseAbilityLore(List<String> lore, String abilityName,List<String> abilityDescription,double cooldown, double mana) {
        boolean hasability = false;
        if (abilityName != null && !abilityName.equals("null")) {
            hasability = true;
        }
        SavageRPG.getInstance().sendConsole("" + abilityName);
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            if (abilityName != null && !abilityName.equals("null")) {
                s = s.replace("{weapon-ability-cooldown}",String.valueOf(cooldown))
                        .replace("{weapon-ability-name}",abilityName)
                        .replace("{weapon-ability-mana}",String.valueOf(mana));
            }
            newLore.add(Color.ify(s));
            if (!(Boolean.parseBoolean(String.valueOf(hasability))) && s.contains("Weapon Ability")) {
                newLore.remove(Color.ify(s));
            }
            if (!(Boolean.parseBoolean(String.valueOf(hasability))) && s.contains("{weapon-ability-name}")) {
                newLore.remove(Color.ify(s));
            }
            if (!(Boolean.parseBoolean(String.valueOf(hasability))) && s.contains("{weapon-ability-cooldown}")) {
                newLore.remove(Color.ify(s));
            }
            if (!(Boolean.parseBoolean(String.valueOf(hasability))) && s.contains("{weapon-ability-mana}")) {
                newLore.remove(Color.ify(s));
            }
            if (!(Boolean.parseBoolean(String.valueOf(hasability))) && s.contains("{weapon-ability-description}")) {
                newLore.remove(Color.ify(s));
            }
            if ((Boolean.parseBoolean(String.valueOf(hasability))) && s.contains("{weapon-ability-description}")) {
                newLore.remove(Color.ify(s));
                for (String e : abilityDescription) {
                    newLore.add(Color.ify("&7» &f" + e));
                }
            }
        }
        return newLore;
    }

}