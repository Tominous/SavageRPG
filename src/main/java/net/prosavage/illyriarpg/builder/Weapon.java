package net.prosavage.illyriarpg.builder;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import net.prosavage.illyriarpg.utils.Chance;
import net.prosavage.illyriarpg.utils.Color;
import net.prosavage.illyriarpg.utils.Number;
import org.apache.commons.lang.WordUtils;
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

    private Number Number = new Number();
    private Chance Chance = new Chance();
    private Color Color = new Color();

    private final ItemStack itemStack;
    private final ItemMeta meta;
    private String name;
    private String material;
    private String rarity;
    private double chance;
    private List<String> lore = Collections.singletonList("");
    private int level;
    private double maximumDamage;
    private double minimumDamage;
    private double weaponAttackCooldown = -1;
    private int scrolls = 0;
    private int gems = 0;
    private String ability = "null";
    private List<String> description = Collections.singletonList("");
    private String castType = "null";
    private double manaCost = -1.0;
    private String actionType = "null";
    private double abilityCooldown = -1;

    private boolean unbreakable = true;

    public Weapon(ItemStack itemStack){
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
    }


    public Weapon(ItemStack itemStack,String name,String material,String rarity,double chance,List<String> lore,int level,double maximumDamage,double minimumDamage,int gem, int scroll) {
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.chance = chance;
        this.lore = lore;
        this.level = level;
        this.maximumDamage = maximumDamage;
        this.minimumDamage = minimumDamage;
        this.gems = gem;
        this.scrolls = scroll;
    }

    public Weapon(ItemStack itemStack,String name,String material,String rarity,double chance,List<String> lore,int level,double maximumDamage,double minimumDamage,int gem, int scroll,String abilityName,List<String> abilityDescription,String castType,double mana, double abilityCooldown) {
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.chance = chance;
        this.lore = lore;
        this.level = level;
        this.maximumDamage = maximumDamage;
        this.minimumDamage = minimumDamage;
        this.gems = gem;
        this.scrolls = scroll;
        this.abilityCooldown = abilityCooldown;
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

    public Weapon setAttackCooldown(double attackCooldown) {
        this.weaponAttackCooldown = attackCooldown;
        return this;
    }

    public Weapon setMaximumDamage(double damage) {
        this.maximumDamage = damage;
        return this;
    }

    public Weapon setMinimumDamage(double damage) {
        this.minimumDamage = damage;
        return this;
    }

    public Weapon setGems(int gem) {
        this.gems = gem;
        return this;
    }

    public Weapon setScrolls(int scrolls) {
        this.scrolls = scrolls;
        return this;
    }

    public Weapon setAbilityCooldown(double abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
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

    private String setDefaultMaterial(ItemStack itemStack){
        String key = String.valueOf(itemStack.getType().getKey()).replace("minecraft:", "");
        String[] split = key.split("_");
        List<String> words = new java.util.ArrayList<>(Collections.singletonList(""));
        for (String s : split){
            s = WordUtils.capitalize(s);
            words.add(s);
        }
        return words.get(0);
    }

    private String setDefaultName(ItemStack itemStack){
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
        this.unbreakable = unbreakable;
        return this;
    }

    public Weapon setCustomModelData(Integer i){
        meta.setCustomModelData(i);
        return this;
    }

    private void setPersistentDataContainers(){
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
        if (this.material == null){
            this.material = setDefaultMaterial(this.itemStack);
        }
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(INamespacedKeys.ITEM_NAME, PersistentDataType.STRING,this.name);
        persistentDataContainer.set(INamespacedKeys.ITEM_MATERIAL,PersistentDataType.STRING,this.material);
        persistentDataContainer.set(INamespacedKeys.ITEM_RARITY,PersistentDataType.STRING,this.rarity);
        persistentDataContainer.set(INamespacedKeys.ITEM_CHANCE,PersistentDataType.DOUBLE,this.chance);
        assert itemLore != null;
        persistentDataContainer.set(INamespacedKeys.ITEM_LORE,PersistentDataType.STRING, itemLore);
        persistentDataContainer.set(INamespacedKeys.ITEM_LEVEL,PersistentDataType.INTEGER,this.level);
        persistentDataContainer.set(INamespacedKeys.ITEM_MAX_DAMAGE,PersistentDataType.DOUBLE,this.maximumDamage);
        persistentDataContainer.set(INamespacedKeys.ITEM_MIN_DAMAGE,PersistentDataType.DOUBLE,this.minimumDamage);
        persistentDataContainer.set(INamespacedKeys.ITEM_SCROLL,PersistentDataType.INTEGER,this.scrolls);
        persistentDataContainer.set(INamespacedKeys.ITEM_GEM,PersistentDataType.INTEGER,this.gems);
        persistentDataContainer.set(INamespacedKeys.ITEM_ABIILTY_COOLDOWN,PersistentDataType.DOUBLE,this.abilityCooldown);
        persistentDataContainer.set(INamespacedKeys.ITEM_ABIILTY_NAME,PersistentDataType.STRING,this.name);
        assert itemDescription != null;
        persistentDataContainer.set(INamespacedKeys.ITEM_ABIILTY_DESCRIPTION,PersistentDataType.STRING,itemDescription);
        persistentDataContainer.set(INamespacedKeys.ITEM_ABIILTY_CAST_TYPE,PersistentDataType.STRING,this.castType);
        persistentDataContainer.set(INamespacedKeys.ITEM_ABIILTY_MANA_COST,PersistentDataType.DOUBLE,this.manaCost);
        persistentDataContainer.set(INamespacedKeys.ITEM_ABIILTY_ACTION_TYPE,PersistentDataType.STRING,this.actionType);
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

    public double getWeaponAttackCooldown() {
        return this.weaponAttackCooldown;
    }

    public double getMaximumDamage() {
        return this.maximumDamage;
    }

    public double getMinimumDamage() {
        return this.minimumDamage;
    }

    public int getGems() {
        return this.gems;
    }

    public int getScrolls() {
        return this.scrolls;
    }

    public double getAbilityCooldown() {
        return this.abilityCooldown;
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

    public ItemStack build() {
        setPersistentDataContainers();
        meta.setDisplayName(this.name);
        List<String> itemLore = IllyriaRPG.getInstance().getConfig().getStringList("weapon.lore");
        itemLore = parseBasicLore(itemLore, this.material, this.rarity, this.level, this.minimumDamage, this.maximumDamage, this.weaponAttackCooldown);
        itemLore = parseGemLore(itemLore, this.gems);
        itemLore = parseScrollLore(itemLore, this.scrolls);
        itemLore = parseAbilityLore(itemLore, this.ability, this.description, this.abilityCooldown, this.manaCost);
        meta.setUnbreakable(this.unbreakable);
        meta.setLore(itemLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    private List<String> parseBasicLore(List<String> lore, String weaponType, String weaponRarity, int weaponLevel, double weaponMinDamage, double weaponMaxDamage, double weaponAttackCooldown){
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            s = s.replace("{weapon-type}", weaponType)
                    .replace("{weapon-rarity}", weaponRarity)
                    .replace("{weapon-required-level}",String.valueOf(weaponLevel))
                    .replace("{weapon-min-damage}",String.valueOf(weaponMinDamage))
                    .replace("{weapon-max-damage}",String.valueOf(weaponMaxDamage))
                    .replace("{weapon-attack-cooldown}",String.valueOf(weaponAttackCooldown));
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
            if (noGem && s.contains("{weapon-gem-sockets}")) {
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
            }

            if (!noGem && s.contains("{weapon-gem-sockets}")) {
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
            if (noScroll && s.contains("{weapon-scroll-sockets}")) {
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
            }

            if (!(noScroll && s.contains("{weapon-scroll-sockets}"))){
                newLore.remove(Color.ify(s));
                for (int i = 0; i < scroll; i++) newLore.add(Color.ify("&7» [ Scroll Socket # " + i + " ]"));
            }
        }
        return newLore;
    }

    private List<String> parseAbilityLore(List<String> lore, String abilityName,List<String> abilityDescription,double cooldown, double mana) {
        boolean hasability = false;
        if (abilityName != null && !abilityName.equals("null")) {
            hasability = true;
        }
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            if (abilityName != null && !abilityName.equals("null")) {
                s = s.replace("{weapon-ability-cooldown}",String.valueOf(cooldown))
                        .replace("{weapon-ability-name}",abilityName)
                        .replace("{weapon-ability-mana}",String.valueOf(mana));
            }
            newLore.add(Color.ify(s));
            if (!hasability && (s.contains("Weapon Ability") || s.contains("{weapon-ability-name}") || s.contains("{weapon-ability-cooldown}") || s.contains("{weapon-ability-mana}") || s.contains("{weapon-ability-description}"))) {
                newLore.remove(Color.ify(s));
            }
            if (hasability && s.contains("{weapon-ability-description}")) {
                newLore.remove(Color.ify(s));
                for (String e : abilityDescription) {
                    newLore.add(Color.ify("&7» &f" + e));
                }
            }
        }
        return newLore;
    }

}
