package net.prosavage.illyriarpg.api;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.files.IWeaponFiles;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Optional;

public class ICreator {

    IWeaponFiles IWeaponFiles = new IWeaponFiles();

    Player player;
    String fileName;
    String rarityName;
    String materialType;
    String levelInput;
    String damageInput;
    String weaponCooldown;
    Boolean grabValues;

    public ICreator(Player player, boolean grabValues){
        this.player = player;
        this.grabValues = grabValues;
    }

    public String getYAMLFileName() {
        if (grabValues) {
            return IWeaponFiles.getName(getPersistentRarityName(), getPersistentFileName());
        }
        return "";
    }

    public String getYAMLRarityName() {
        if (grabValues) {
            return IWeaponFiles.getRarity(getPersistentRarityName(), getPersistentFileName());
        }
        return "";
    }

//    public String getYAMLMaterialType() {
//        return IWeaponFiles.
//    }

    public String getYAMLMaterialLoreType() {
        if (grabValues) {
            return IWeaponFiles.getMaterial(getPersistentRarityName(), getPersistentFileName());
        }
        return "";
    }

    public String getYAMLLevelInput() {
        if (grabValues){
            return "" + IWeaponFiles.getMinimumLevel(getPersistentRarityName(), getPersistentFileName());
        }
        return "";
    }

    public String getYAMLDamageInput() {
        if (grabValues) {
            double minimumDamage = IWeaponFiles.getMinimumDamage(getPersistentRarityName(), getPersistentFileName());
            double maximumDamage = IWeaponFiles.getMaximumDamage(getPersistentRarityName(), getPersistentFileName());
            IllyriaRPG.getInstance().sendConsole(minimumDamage + " " + maximumDamage);
            return minimumDamage + "-" + maximumDamage;
        }
        return "";
    }

    public String getYAMLWeaponCooldown() {
        if (grabValues) {
            return "" + IWeaponFiles.getCooldown(getPersistentRarityName(), getPersistentFileName());
        }
        return "";
    }

    public String getPersistentFileName() {
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_WEAPON_FILE_NAME, PersistentDataType.STRING, "null");
    }

    public String getPersistentRarityName() {
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_RARITY, PersistentDataType.STRING, "Common");
    }

    public String getPersistentMaterialType(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_MATERIAL_TYPE, PersistentDataType.STRING, "WOODEN_SWORD");
    }

    public String getPersistentMaterialLoreType() {
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_MATERIAL_LORE_TYPE, PersistentDataType.STRING, "null");
    }

    public Integer getPersistentLevelInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_LEVEL, PersistentDataType.INTEGER, 1);
    }

    public Double getPersistentMinimumDamageInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_MINIMUM_DAMAGE, PersistentDataType.DOUBLE, -1.0);
    }

    public Double getPersistentMaximumDamageInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_MAXIMUM_DAMAGE, PersistentDataType.DOUBLE, -1.0);
    }

    public Double getPersistentCooldownInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_ATTACK_COOLDOWN, PersistentDataType.DOUBLE, -1.0);
    }

    public Integer getPersistentScrollAmountInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_SCROLL_AMOUNT, PersistentDataType.INTEGER, 0);
    }

    public Integer getPersistentGemAmountInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_GEM_AMOUNT, PersistentDataType.INTEGER, 0);
    }

    public String getPersistentAbilityNameInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_ABILITY_NAME, PersistentDataType.STRING, "null");
    }

    public String getPersistentAbilityCastTypeInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_ABILITY_CAST_TYPE, PersistentDataType.STRING, "null");
    }

    public String getPersistentAbilityActionTypeInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_ABILITY_ACTION_TYPE, PersistentDataType.STRING, "null");
    }

    public Double getPersistentAbilityCooldownInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_ABILITY_COOLDOWN, PersistentDataType.DOUBLE, -1.0);
    }

    public Double getPersistentAbilityManaCostInput(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_ABILITY_MANA_COST, PersistentDataType.DOUBLE, -1.0);
    }

    public Byte getPersistentGiveItem(){
        return this.player.getPersistentDataContainer().getOrDefault(INamespacedKeys.CREATOR_ITEM_GIVE_ITEM, PersistentDataType.BYTE, (byte)0);
    }

}
