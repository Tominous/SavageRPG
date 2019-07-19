package net.prosavage.savagerpg;

import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class PDCPlayer {

    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    Armor Armor = new Armor();

    Player player;

    public PDCPlayer(OfflinePlayer p){
        this.player = p.getPlayer();
    }

    public Integer getLevel(){
        Integer level = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerLevel(),PersistentDataType.INTEGER);
        if (level == null){
            return 0;
        }
        return level;
    }


    public double getExp() {
        Double exp = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerEXP(),PersistentDataType.DOUBLE);
        if (exp == null){
            return 0.0;
        }
        return exp;
    }

    public double getMaxExp(){
        Double maxexp = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerMaxEXP(),PersistentDataType.DOUBLE);
        if (maxexp == null){
            return 0.0;
        }
        return maxexp;
    }

    public int getAttributePoints() {
        Integer attribute = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerAttributePoints(),PersistentDataType.INTEGER);
        if (attribute == null){
            return 0;
        }
        return attribute;
    }

    public int getStrengthPoints(){
        Integer strength = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerStrengthAttributes(),PersistentDataType.INTEGER);
        if (strength == null){
            return 0;
        }
        return strength;
    }

    public int getIntelligencePoints() {
        Integer intelligence = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerIntelligenceAttributes(),PersistentDataType.INTEGER);
        if (intelligence == null){
            return 0;
        }
        return intelligence;
    }

    public int getConstitutionPoints() {
        Integer constitution = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerConstitutionAttributes(),PersistentDataType.INTEGER);
        if (constitution == null){
            return 0;
        }
        return constitution;
    }

    public int getDexterityPoints() {
        Integer dexterity = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerDexterityAttributes(),PersistentDataType.INTEGER);
        if (dexterity == null){
            return 0;
        }
        return dexterity;
    }

    public int getCharismaPoints() {
        Integer charisma = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerCharismaAttributes(),PersistentDataType.INTEGER);
        if (charisma == null){
            return 0;
        }
        return charisma;
    }

    public int getWisdomPoints() {
        Integer wisdom = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerWisdomAttributes(),PersistentDataType.INTEGER);
        if (wisdom == null){
            return 0;
        }
        return wisdom;
    }

    public int getLuckPoints() {
        Integer luck = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerLuckAttributes(),PersistentDataType.INTEGER);
        if (luck == null){
            return 0;
        }
        return luck;
    }

    public double getRecentDamage() {
        Double recent = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerRecentDamage(),PersistentDataType.DOUBLE);
        if (recent == null){
            return 0.0;
        }
        return recent;
    }

    public double getDamage() {
        Double damage = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerDamage(),PersistentDataType.DOUBLE);
        if (damage == null){
            return 0.0;
        }
        return damage;
    }

    public double getDefense(){
        Double defense = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerDefense(),PersistentDataType.DOUBLE);
        if (defense == null){
            return 0.0;
        }
        return defense;
    }

    public double getGearScore(){
        Double gearscore = this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerGearScore(),PersistentDataType.DOUBLE);
        if (gearscore == null){
            return 0.0;
        }
        return gearscore;
    }

    public void setLevel(int level) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerLevel(), PersistentDataType.INTEGER, level);
    }

    public void setExp(double exp) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerEXP(), PersistentDataType.DOUBLE, exp);
    }

    public void setMaxExp() {
        double maxExp = Double.valueOf(String.format("%.2f", Formula.eval(Placeholder.parsePlayerInfo(this.player, String.valueOf(SavageRPG.getInstance().getConfig().get("formulas.exp"))))));
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerMaxEXP(), PersistentDataType.DOUBLE, maxExp);
    }

    public void setAttributePoints(int attributePoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerAttributePoints(), PersistentDataType.INTEGER, attributePoints);
    }

    public void setStrengthPoint(int strengthPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerStrengthAttributes(), PersistentDataType.INTEGER, strengthPoints);
    }

    public void setIntelligencePoint(int intelligencePoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerIntelligenceAttributes(), PersistentDataType.INTEGER, intelligencePoints);
    }

    public void setConstitutionPoint(int constitutionPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerConstitutionAttributes(), PersistentDataType.INTEGER, constitutionPoints);
    }

    public void setDexterityPoint(int dexterityPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerDexterityAttributes(), PersistentDataType.INTEGER, dexterityPoints);
    }

    public void setCharismaPoint(int charismaPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerCharismaAttributes(), PersistentDataType.INTEGER, charismaPoints);
    }

    public void setWisdomPoint(int wisdomPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerWisdomAttributes(), PersistentDataType.INTEGER, wisdomPoints);
    }

    public void setLuckPoints(int luckPoints) {
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerLuckAttributes(), PersistentDataType.INTEGER, luckPoints);
    }

    public void setDefense(double defensePoints){
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerDefense(), PersistentDataType.DOUBLE, defensePoints);
    }

    public void setGearScore(double gearScore){
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerGearScore(), PersistentDataType.DOUBLE, gearScore);
    }


    public void setDefault(){
        PDCPlayer player = new PDCPlayer(this.player);
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
    }

    public void updateDefense(){
        PDCPlayer player = new PDCPlayer(this.player);
        double helmetProtection = Armor.getProtection(this.player.getInventory().getHelmet());
        double chestplateProtection = Armor.getProtection(this.player.getInventory().getChestplate());
        double leggingsProtection = Armor.getProtection(this.player.getInventory().getLeggings());
        double bootsProtection = Armor.getProtection(this.player.getInventory().getBoots());
        double protection = helmetProtection + chestplateProtection + leggingsProtection + bootsProtection;
        player.setDefense(protection);
    }

}
