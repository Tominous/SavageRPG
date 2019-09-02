package net.prosavage.savagerpg.api.entities;

import net.prosavage.savagerpg.api.itemstacks.SArmor;
import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class SPlayer {

    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    SArmor SArmor = new SArmor();
    Player player;

    public SPlayer(OfflinePlayer offlinePlayer){
        this.player = offlinePlayer.getPlayer();
    }

    public Integer getLevel(){
        Integer level = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_LEVEL,PersistentDataType.INTEGER);
        if (level == null){
            return 0;
        }
        return level;
    }

    public double getAbilityCooldown(){
        Double cooldown = this.player.getPersistentDataContainer().get(SNamespacedKeys.ITEM_ABIILTY_COOLDOWN, PersistentDataType.DOUBLE);
        if (cooldown == null){
            return 0.0;
        }
        return cooldown;
    }

    public double getExp() {
        Double exp = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_EXP,PersistentDataType.DOUBLE);
        if (exp == null){
            return 0.0;
        }
        return exp;
    }

    public double getMaxExp(){
        Double maxexp = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_MAX_EXP,PersistentDataType.DOUBLE);
        if (maxexp == null){
            return 0.0;
        }
        return maxexp;
    }

    public int getAttributePoints() {
        Integer attribute = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_ATTRIBUTE_POINTS,PersistentDataType.INTEGER);
        if (attribute == null){
            return 0;
        }
        return attribute;
    }

    public int getStrengthPoints(){
        Integer strength = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_STRENGTH_ATTRIBUTES,PersistentDataType.INTEGER);
        if (strength == null){
            return 0;
        }
        return strength;
    }

    public int getIntelligencePoints() {
        Integer intelligence = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_INTELLIGENCE_ATTRIBUTES,PersistentDataType.INTEGER);
        if (intelligence == null){
            return 0;
        }
        return intelligence;
    }

    public int getConstitutionPoints() {
        Integer constitution = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_CONSTITUTION_ATTRIBUTES,PersistentDataType.INTEGER);
        if (constitution == null){
            return 0;
        }
        return constitution;
    }

    public int getDexterityPoints() {
        Integer dexterity = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_DEXTERITY_ATTRIBUTES,PersistentDataType.INTEGER);
        if (dexterity == null){
            return 0;
        }
        return dexterity;
    }

    public int getCharismaPoints() {
        Integer charisma = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_CHARISMA_ATTRIBUTES,PersistentDataType.INTEGER);
        if (charisma == null){
            return 0;
        }
        return charisma;
    }

    public int getWisdomPoints() {
        Integer wisdom = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_WISDOM_ATTRIBUTES,PersistentDataType.INTEGER);
        if (wisdom == null){
            return 0;
        }
        return wisdom;
    }

    public int getLuckPoints() {
        Integer luck = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_LUCK_ATTRIBUTES,PersistentDataType.INTEGER);
        if (luck == null){
            return 0;
        }
        return luck;
    }

    public double getRecentDamage() {
        Double recent = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_RECENT_DAMAGE,PersistentDataType.DOUBLE);
        if (recent == null){
            return 0.0;
        }
        return recent;
    }

    public double getDamage() {
        Double damage = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_DAMAGE,PersistentDataType.DOUBLE);
        if (damage == null){
            return 0.0;
        }
        return damage;
    }

    public double getDefense(){
        Double defense = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_DEFENSE,PersistentDataType.DOUBLE);
        if (defense == null){
            return 0.0;
        }
        return defense;
    }

    public double getGearScore(){
        Double gearscore = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_GEAR_SCORE,PersistentDataType.DOUBLE);
        if (gearscore == null){
            return 0.0;
        }
        return gearscore;
    }

    public double getMana(){
        Double mana = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_MANA,PersistentDataType.DOUBLE);
        if (mana == null){
            return 0.0;
        }
        return mana;
    }

    public double getMaxMana(){
        Double mana = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_MAX_MANA,PersistentDataType.DOUBLE);
        if (mana == null){
            return 0.0;
        }
        return mana;
    }

    public double getRegen(){
        Double regen = this.player.getPersistentDataContainer().get(SNamespacedKeys.PLAYER_REGENERATION, PersistentDataType.DOUBLE);
        if (regen == null){
            return 0.0;
        }
        return regen;
    }

    public void setLevel(int level) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_LEVEL, PersistentDataType.INTEGER, level);
    }

    public void setAbilityCooldown(double exp) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.ITEM_ABIILTY_COOLDOWN, PersistentDataType.DOUBLE, exp);
    }

    public void setExp(double exp) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_EXP, PersistentDataType.DOUBLE, exp);
    }

    public void setMaxExp() {
        double maxExp = Double.parseDouble(String.format("%.2f", Formula.eval(Placeholder.parsePlayerInfo(this.player, String.valueOf(SavageRPG.getInstance().getConfig().get("formulas.exp"))))));
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_MAX_EXP, PersistentDataType.DOUBLE, maxExp);
    }

    public void setAttributePoints(int attributePoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_ATTRIBUTE_POINTS, PersistentDataType.INTEGER, attributePoints);
    }

    public void setStrengthPoint(int strengthPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_STRENGTH_ATTRIBUTES, PersistentDataType.INTEGER, strengthPoints);
    }

    public void setIntelligencePoint(int intelligencePoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_INTELLIGENCE_ATTRIBUTES, PersistentDataType.INTEGER, intelligencePoints);
    }

    public void setConstitutionPoint(int constitutionPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_CONSTITUTION_ATTRIBUTES, PersistentDataType.INTEGER, constitutionPoints);
    }

    public void setDexterityPoint(int dexterityPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_DEXTERITY_ATTRIBUTES, PersistentDataType.INTEGER, dexterityPoints);
    }

    public void setCharismaPoint(int charismaPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_CHARISMA_ATTRIBUTES, PersistentDataType.INTEGER, charismaPoints);
    }

    public void setWisdomPoint(int wisdomPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_WISDOM_ATTRIBUTES, PersistentDataType.INTEGER, wisdomPoints);
    }

    public void setLuckPoints(int luckPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_LUCK_ATTRIBUTES, PersistentDataType.INTEGER, luckPoints);
    }

    public void setDefense(double defensePoints){
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_DEFENSE, PersistentDataType.DOUBLE, defensePoints);
    }

    public void setGearScore(double gearScore){
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_GEAR_SCORE, PersistentDataType.DOUBLE, gearScore);
    }

    public void setMana(double mana){
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_MANA, PersistentDataType.DOUBLE, mana);
    }

    public void setMaxMana(double mana){
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_MAX_MANA, PersistentDataType.DOUBLE, mana);
    }

    public void setRecentDamage(double recentDamage){
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_RECENT_DAMAGE, PersistentDataType.DOUBLE, recentDamage);
    }

    public void setRegen(double regen){
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_REGENERATION, PersistentDataType.DOUBLE, regen);
    }

    public void setDamage(double recentDamage){
        this.player.getPersistentDataContainer().set(SNamespacedKeys.PLAYER_DAMAGE, PersistentDataType.DOUBLE, recentDamage);
    }

    public void setDefault(){
        SPlayer player = new SPlayer(this.player);
        player.setLevel(1);
        player.setExp(0.00);
        player.setMaxExp();
        player.setAttributePoints(0);
        player.setStrengthPoint(0);
        player.setIntelligencePoint(0);
        player.setConstitutionPoint(0);
        player.setDexterityPoint(0);
        player.setCharismaPoint(0);
        player.setWisdomPoint(0);
        player.setLuckPoints(0);
        player.setDefense(0);
        player.setGearScore(0.0);
        player.setMana(0.0);
        player.setMaxMana(0.0);
        player.setRecentDamage(0.0);
    }

    public void updateDefense(){
        SPlayer player = new SPlayer(this.player);
        double helmetProtection = SArmor.getProtection(this.player.getInventory().getHelmet());
        double chestplateProtection = SArmor.getProtection(this.player.getInventory().getChestplate());
        double leggingsProtection = SArmor.getProtection(this.player.getInventory().getLeggings());
        double bootsProtection = SArmor.getProtection(this.player.getInventory().getBoots());
        double protection = helmetProtection + chestplateProtection + leggingsProtection + bootsProtection;
        player.setDefense(protection);
    }

    public void updateHealth(){
        double helmetHealth = SArmor.getHealth(this.player.getInventory().getHelmet());
        double chestplateHealth = SArmor.getHealth(this.player.getInventory().getChestplate());
        double leggingsHealth = SArmor.getHealth(this.player.getInventory().getLeggings());
        double bootsHealth = SArmor.getHealth(this.player.getInventory().getBoots());
        double health = helmetHealth + chestplateHealth + leggingsHealth + bootsHealth;
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20 + health);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(0);
    }

    public void updateRegen(){
        SPlayer player = new SPlayer(this.player);
        double helmetRegen = SArmor.getHealth(this.player.getInventory().getHelmet());
        double chestplateRegen = SArmor.getHealth(this.player.getInventory().getChestplate());
        double leggingsRegen = SArmor.getHealth(this.player.getInventory().getLeggings());
        double bootsRegen = SArmor.getHealth(this.player.getInventory().getBoots());
        double regen = helmetRegen + chestplateRegen + leggingsRegen + bootsRegen;
        player.setRegen(regen);
    }




}
