package net.prosavage.savagerpg.builder;

import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class Armor {

    Number Number = new Number();
    Color Color = new Color();

    private ItemStack itemStack;
    private ItemMeta meta;
    private String name;
    private String material;
    private String rarity;
    private int level;
    private double protection;
    private double health;
    private double regen;
    private int gem;

    public Armor(ItemStack itemStack){
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
    }

    public Armor(ItemStack itemStack, String name, String material, String rarity, int level, double protection, double health, double regen, int gem){
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.level = level;
        this.protection = protection;
        this.health = health;
        this.regen = regen;
        this.gem = gem;
    }

    public Armor setName(String name) {
        this.name = name;
        return this;
    }

    public Armor setMaterial(String type) {
        this.material = type;
        return this;
    }

    public Armor setRarity(String rarity) {
        this.rarity = rarity;
        return this;
    }

    public Armor setLevel(int level) {
        this.level = level;
        return this;
    }

    public Armor setProtection(double protection) {
        this.protection = protection;
        return this;
    }

    public Armor setHealth(double health) {
        this.health = health;
        return this;
    }

    public Armor setRegen(double regen) {
        this.regen = regen;
        return this;
    }

    public Armor setGem(int gem) {
        this.gem = gem;
        return this;
    }

    public Armor setUnbreakable(boolean bool){
        meta.setUnbreakable(bool);
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

    public int getLevel() {
        return this.level;
    }

    public double getProtection() {
        return this.protection;
    }

    public double getHealth() {
        return this.health;
    }

    public double getRegen() {
        return this.regen;
    }

    public int getGem() {
        return this.gem;
    }

    public void setPersistentDataContainers(){
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(SNamespacedKeys.ARMOR_NAME, PersistentDataType.STRING,this.name);
        persistentDataContainer.set(SNamespacedKeys.ARMOR_MATERIAL, PersistentDataType.STRING,this.material);
        persistentDataContainer.set(SNamespacedKeys.ARMOR_RARITY, PersistentDataType.STRING,this.rarity);
        persistentDataContainer.set(SNamespacedKeys.ARMOR_LEVEL, PersistentDataType.INTEGER,this.level);
        persistentDataContainer.set(SNamespacedKeys.ARMOR_PROTECTION, PersistentDataType.DOUBLE,this.protection);
        persistentDataContainer.set(SNamespacedKeys.ARMOR_HEALTH, PersistentDataType.DOUBLE,this.health);
        persistentDataContainer.set(SNamespacedKeys.ARMOR_REGEN, PersistentDataType.DOUBLE,this.regen);
        persistentDataContainer.set(SNamespacedKeys.ARMOR_GEM, PersistentDataType.INTEGER,this.gem);
    }

    public List<String> parseBasicLore(List<String> lore, String armorType, String armorRarity, int armorLevel, double armorProtection, double armorHealth, double armorRegen){
        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            s = s.replace("{armor-type}", armorType)
                    .replace("{armor-rarity}", armorRarity)
                    .replace("{armor-required-level}",String.valueOf(armorLevel))
                    .replace("{armor-protection}",String.valueOf(armorProtection))
                    .replace("{armor-health}",String.valueOf(armorHealth))
                    .replace("{armor-regen}",String.valueOf(armorRegen));
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
            if (Boolean.parseBoolean(String.valueOf(noGem)) && s.contains("{armor-gem-sockets}")) {
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(newLore.indexOf(Color.ify(s)) - 1);
                newLore.remove(Color.ify(s));
            }

            if (!(Boolean.parseBoolean(String.valueOf(noGem))) && s.contains("{armor-gem-sockets}")) {
                newLore.remove(Color.ify(s));
                for (int i = 0; i < gem; i++) newLore.add(Color.ify("&7» [ Gems Socket # " + i + " ]"));
            }
        }
        return newLore;
    }

    public ItemStack build() {
        setPersistentDataContainers();
        meta.setDisplayName(this.name);
        List<String> itemLore = SavageRPG.getInstance().getConfig().getStringList("armor.lore");
        itemLore = parseBasicLore(itemLore, this.material, this.rarity, this.level, this.protection, this.health, this.regen);
        for (String string : itemLore){
            SavageRPG.getInstance().sendConsole(string);
        }
        itemLore = parseGemLore(itemLore, this.gem);
        for (String string : itemLore){
            SavageRPG.getInstance().sendConsole(string);
        }
        meta.setLore(itemLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
