package net.prosavage.savagerpg.api;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class SPlayer {

    SNamespacedKey SNamespacedKey = new SNamespacedKey();

    Player player;
    int level = 1;
    double exp = 0.00;
    double maxExp = 0.00;
    int attributePoints = 0;

    int strengthPoints = 1;
    // Strength point, controls the max weight and melee the user can have.
    int intelligencePoints = 1;
    // Intelligence point, controls the mana amount and have a random chance to gain more skill points upon leveling up.
    int constitutionPoints = 1;
    // Endurance, vitality, stamina, controls the amount of health and defense the user can obtain.
    int dexterityPoints = 1;
    // Agility, controls how quick they can be and can allow them to get out of situation sometimes.
    int charismaPoints = 1;
    // Controls how their appearance will affect other npcs.
    int wisdomPoints = 1;
    // Controls the cooldown of spells and how good they ares
    int luckPoints = 1;
    // Controls the amount of luck they have which affects item drops, fishing drops, etc.

    public SPlayer(Player p){
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }

    public int getLevel(){
        return this.level;
    }

    public double getExp() {
        return this.exp;
    }

    public double getMaxExp(){
        return this.maxExp;
    }

    public int getAttributePoints() {
        return this.attributePoints;
    }

    public int getStrengthPoints(){
        return this.strengthPoints;
    }

    public int getIntelligencePoints() {
        return this.intelligencePoints;
    }

    public int getConstitutionPoints() {
        return this.constitutionPoints;
    }

    public int getDexterityPoints() {
        return this.dexterityPoints;
    }

    public int getCharismaPoints() {
        return this.charismaPoints;
    }

    public int getWisdomPoints() {
        return this.wisdomPoints;
    }

    public int getLuckPoints() {
        return luckPoints;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public void setMaxExp(double maxExp) {
        this.maxExp = maxExp;
    }

    public void setAttributePoints(int attributePoints) {
        this.attributePoints = attributePoints;
    }

    public void setStrengthPoint(int strengthPoints) {
        this.strengthPoints = strengthPoints;
    }

    public void setIntelligencePoint(int intelligencePoints) {
        this.intelligencePoints = intelligencePoints;
    }

    public void setConstitutionPoint(int constitutionPoints) {
        this.constitutionPoints = constitutionPoints;
    }

    public void setDexterityPoint(int dexterityPoints) {
        this.dexterityPoints = dexterityPoints;
    }

    public void setCharismaPoint(int charismaPoints) {
        this.charismaPoints = charismaPoints;
    }

    public void setWisdomPoint(int wisdomPoints) {
        this.wisdomPoints = wisdomPoints;
    }

    public void setLuckPoints(int luckPoints) {
        this.luckPoints = luckPoints;
    }

    public void saveData(){
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerLevel(), PersistentDataType.INTEGER, this.level);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerEXP(), PersistentDataType.DOUBLE, this.exp);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerMaxEXP(), PersistentDataType.DOUBLE, this.maxExp);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerAttributePoints(), PersistentDataType.INTEGER, this.attributePoints);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerStrengthAttributes(), PersistentDataType.INTEGER, this.strengthPoints);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerIntelligenceAttributes(), PersistentDataType.INTEGER, this.intelligencePoints);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerConstitutionAttributes(), PersistentDataType.INTEGER, this.constitutionPoints);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerDexterityAttributes(), PersistentDataType.INTEGER, this.dexterityPoints);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerCharismaAttributes(), PersistentDataType.INTEGER, this.charismaPoints);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerWisdomAttributes(), PersistentDataType.INTEGER, this.wisdomPoints);
        this.player.getPersistentDataContainer().set(SNamespacedKey.getPlayerLuckAttributes(), PersistentDataType.INTEGER, this.luckPoints);
    }

    public void loadData(){
        PDCPlayer pdcPlayer = new PDCPlayer(this.player);
        this.setLevel(pdcPlayer.getLevel());
        this.setExp(pdcPlayer.getExp());
        this.setMaxExp(pdcPlayer.getMaxExp());
        this.setAttributePoints(pdcPlayer.getAttributePoints());
        this.setStrengthPoint(pdcPlayer.getStrengthPoints());
        this.setIntelligencePoint(pdcPlayer.getIntelligencePoints());
        this.setConstitutionPoint(pdcPlayer.getConstitutionPoints());
        this.setDexterityPoint(pdcPlayer.getDexterityPoints());
        this.setCharismaPoint(pdcPlayer.getCharismaPoints());
        this.setWisdomPoint(pdcPlayer.getWisdomPoints());
        this.setLuckPoints(pdcPlayer.getLuckPoints());
    }

}