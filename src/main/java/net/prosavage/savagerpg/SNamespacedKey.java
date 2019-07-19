package net.prosavage.savagerpg;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.NamespacedKey;

public class SNamespacedKey {

    NamespacedKey playerLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.level");
    NamespacedKey playerEXP = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.exp");
    NamespacedKey playerMaxEXP = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.max.exp");
    NamespacedKey playerAttributePoints = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.attribute.points");
    NamespacedKey playerStrengthAttributes = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.strength.attribute");
    NamespacedKey playerIntelligenceAttributes = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.intelligence.attribute");
    NamespacedKey playerConstitutionAttributes = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.constitution.attribute");
    NamespacedKey playerDexterityAttributes = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.dexterity.attribute");
    NamespacedKey playerCharismaAttributes = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.charisma.attribute");
    NamespacedKey playerWisdomAttributes = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.wisdom.attribute");
    NamespacedKey playerLuckAttributes = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.luck.attribute");
    NamespacedKey playerRecentDamage = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.recent.damage");
    NamespacedKey playerDamage = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.damage");
    NamespacedKey playerDefense = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.defense");
    NamespacedKey playerGearScore = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.player.gear.score");

    NamespacedKey itemName = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.name");
    NamespacedKey itemMaterial = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.material");
    NamespacedKey itemRarity = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.rarity");
    NamespacedKey itemChance = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.chance");
    NamespacedKey itemLore = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.lore");
    NamespacedKey itemMaxLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.max.level");
    NamespacedKey itemLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.level");
    NamespacedKey itemMinLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.max.level");
    NamespacedKey itemMaxDamage = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.max.damage");
    NamespacedKey itemMinDamage = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.min.damage");
    NamespacedKey itemMaxGem = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.max.gem");
    NamespacedKey itemMinGem = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.min.gem");
    NamespacedKey itemGem = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.gem");
    NamespacedKey itemHaveGems = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.have.gems");
    NamespacedKey itemMaxCooldown = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.gemcooldown");
    NamespacedKey itemMinCooldown = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.min.cooldown");
    NamespacedKey itemCooldown = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.item.cooldown");

    NamespacedKey armorName = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.name");
    NamespacedKey armorMaterial = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.material");
    NamespacedKey armorRarity = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.rarity");
    NamespacedKey armorChance = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.chance");
    NamespacedKey armorLore = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.lore");
    NamespacedKey armorMaxLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.max.level");
    NamespacedKey armorLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.level");
    NamespacedKey armorMinLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.min.level");
    NamespacedKey armorMaxProtection = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.max.protection");
    NamespacedKey armorProtection = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.protection");
    NamespacedKey armorMinProtection = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.min.protection");
    NamespacedKey armorMaxHealth = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.max.health");
    NamespacedKey armorHealth = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.health");
    NamespacedKey armorMinHealth = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.min.health");
    NamespacedKey armorMaxRegen = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.max.regen");
    NamespacedKey armorRegen = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.regen");
    NamespacedKey armorMinRegen = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.min.regen");
    NamespacedKey armorMaxGem = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.max.gem");
    NamespacedKey armorGem = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.gem");
    NamespacedKey armorHaveGems = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.have.gems");
    NamespacedKey armorMinGem = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.armor.min.gem");

    NamespacedKey entityLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.level");
    NamespacedKey entityMinLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.min.level");
    NamespacedKey entityMaxLevel = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.max.level");
    NamespacedKey entityExp = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.exp");
    NamespacedKey entityRecentDamage = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.recent.damage");
    NamespacedKey entityDamage = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.damage");
    NamespacedKey entityName = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.name");
    NamespacedKey entityHealth = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.health");
    NamespacedKey entityMaxHealth = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.max.health");
    NamespacedKey entityDrops = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.drops");
    NamespacedKey entityDropChance = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.drop.chance");
    NamespacedKey entityIsSavageRPGMob = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.is.savagerpg.mob");
    NamespacedKey entityDistance = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.distance");
    NamespacedKey entityOwner = new NamespacedKey(SavageRPG.getInstance(), "savagerpg.entity.owner");

    public NamespacedKey getPlayerLevel() {
        return playerLevel;
    }

    public NamespacedKey getPlayerEXP() {
        return playerEXP;
    }

    public NamespacedKey getPlayerMaxEXP() {
        return playerMaxEXP;
    }

    public NamespacedKey getPlayerAttributePoints() {
        return playerAttributePoints;
    }

    public NamespacedKey getPlayerStrengthAttributes() {
        return playerStrengthAttributes;
    }

    public NamespacedKey getPlayerIntelligenceAttributes() {
        return playerIntelligenceAttributes;
    }

    public NamespacedKey getPlayerConstitutionAttributes() {
        return playerConstitutionAttributes;
    }

    public NamespacedKey getPlayerDexterityAttributes() {
        return playerDexterityAttributes;
    }

    public NamespacedKey getPlayerCharismaAttributes() {
        return playerCharismaAttributes;
    }

    public NamespacedKey getPlayerWisdomAttributes() {
        return playerWisdomAttributes;
    }

    public NamespacedKey getPlayerLuckAttributes() {
        return playerLuckAttributes;
    }

    public NamespacedKey getPlayerRecentDamage() {
        return playerRecentDamage;
    }

    public NamespacedKey getPlayerDamage() {
        return playerDamage;
    }

    public NamespacedKey getPlayerDefense(){
        return playerDefense;
    }

    public NamespacedKey getPlayerGearScore(){
        return playerGearScore;
    }

    public NamespacedKey getItemName() {
        return itemName;
    }

    public NamespacedKey getItemMaterial() {
        return itemMaterial;
    }

    public NamespacedKey getItemRarity() {
        return itemRarity;
    }

    public NamespacedKey getItemChance() {
        return itemChance;
    }

    public NamespacedKey getItemLore() {
        return itemLore;
    }

    public NamespacedKey getItemMaxLevel() {
        return itemMaxLevel;
    }

    public NamespacedKey getItemMinLevel() {
        return itemMinLevel;
    }

    public NamespacedKey getItemLevel() {
        return itemLevel;
    }

    public NamespacedKey getItemMaxDamage() {
        return itemMaxDamage;
    }

    public NamespacedKey getItemMinDamage() {
        return itemMinDamage;
    }

    public NamespacedKey getItemMaxGem() {
        return itemMaxGem;
    }

    public NamespacedKey getItemMinGem() {
        return itemMinGem;
    }

    public NamespacedKey getItemGem() {
        return itemGem;
    }

    public NamespacedKey getItemMaxCooldown() {
        return itemMaxCooldown;
    }

    public NamespacedKey getItemMinCooldown() {
        return itemMinCooldown;
    }

    public NamespacedKey getItemCooldown() {
        return itemCooldown;
    }

    public NamespacedKey getDoesItemHaveGems() {
        return itemHaveGems;
    }

    public NamespacedKey getEntityLevel() {
        return entityLevel;
    }

    public NamespacedKey getEntityMaxLevel() {
        return entityMaxLevel;
    }

    public NamespacedKey getEntityMinLevel() {
        return entityMinLevel;
    }

    public NamespacedKey getEntityExp() {
        return entityExp;
    }

    public NamespacedKey getEntityRecentDamage() {
        return entityRecentDamage;
    }

    public NamespacedKey getEntityName() {
        return entityName;
    }

    public NamespacedKey getEntityHealth() {
        return entityHealth;
    }

    public NamespacedKey getEntityMaxHealth() {
        return entityMaxHealth;
    }

    public NamespacedKey getEntityDistance(){
        return entityDistance;
    }

    public NamespacedKey getEntityDamage() {
        return entityDamage;
    }

    public NamespacedKey getEntityOwner() {
        return entityOwner;
    }

    public NamespacedKey getArmorName() {
        return armorName;
    }

    public NamespacedKey getArmorMaterial() {
        return armorMaterial;
    }

    public NamespacedKey getArmorRarity() {
        return armorRarity;
    }

    public NamespacedKey getArmorChance() {
        return armorChance;
    }

    public NamespacedKey getArmorLore() {
        return armorLore;
    }

    public NamespacedKey getArmorMinLevel() {
        return armorMinLevel;
    }

    public NamespacedKey getArmorLevel() {
        return armorLevel;
    }

    public NamespacedKey getArmorMaxLevel() {
        return armorMaxLevel;
    }

    public NamespacedKey getArmorMaxProtection() {
        return armorMaxProtection;
    }

    public NamespacedKey getArmorProtection() {
        return armorProtection;
    }

    public NamespacedKey getArmorMinProtection() {
        return armorMinProtection;
    }

    public NamespacedKey getArmorMaxHealth() {
        return armorMaxHealth;
    }

    public NamespacedKey getArmorHealth() {
        return armorHealth;
    }

    public NamespacedKey getArmorMinHealth() {
        return armorMinHealth;
    }

    public NamespacedKey getArmorMaxRegen() {
        return armorMaxRegen;
    }

    public NamespacedKey getArmorRegen() {
        return armorRegen;
    }

    public NamespacedKey getArmorMinRegen() {
        return armorMinRegen;
    }

    public NamespacedKey getArmorMaxGem() {
        return armorMaxGem;
    }

    public NamespacedKey getArmorGem() {
        return armorGem;
    }

    public NamespacedKey getDoesArmorHaveGems() {
        return armorHaveGems;
    }

    public NamespacedKey getArmorMinGem() {
        return armorMinGem;
    }


}
