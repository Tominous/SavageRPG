package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.entities.SPlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Placeholder {

    YamlConfiguration variable = SavageRPG.getInstance().getPlaceholderFile();

    public String parsePlayerInfo(Player user, String string){
        SPlayer player = new SPlayer(user);
        int playerLevel = player.getLevel();
        double playerEXP = player.getExp();
        int playerATR = player.getAttributePoints();
        int playerSTR = player.getStrengthPoints();
        int playerINT = player.getIntelligencePoints();
        int playerCON = player.getConstitutionPoints();
        int playerDEX = player.getDexterityPoints();
        int playerCHA = player.getCharismaPoints();
        int playerWIS = player.getWisdomPoints();
        int playerLUK = player.getLuckPoints();
        double playerRecentDamage = player.getRecentDamage();
        double playerDamage = player.getDamage();
        double playerDefense = player.getDefense();
        string = string.replace(String.valueOf(getYAMLPlaceholder("player-name")), (user.getName()))
                .replace(String.valueOf(getYAMLPlaceholder("player-level")), String.valueOf(playerLevel))
                .replace(String.valueOf(getYAMLPlaceholder("player-health")), String.valueOf(user.getHealth()))
                .replace(String.valueOf(getYAMLPlaceholder("player-health-formatted")), String.format("%.2f", user.getHealth()))
                .replace(String.valueOf(getYAMLPlaceholder("player-max-health")), String.valueOf(user.getMaxHealth()))
                .replace(String.valueOf(getYAMLPlaceholder("player-max-health-formatted")), String.format("%.2f", user.getMaxHealth()))
                .replace(String.valueOf(getYAMLPlaceholder("player-exp")), String.valueOf(playerEXP))
                .replace(String.valueOf(getYAMLPlaceholder("player-atr")), String.valueOf(playerATR))
                .replace(String.valueOf(getYAMLPlaceholder("player-str")), String.valueOf(playerSTR))
                .replace(String.valueOf(getYAMLPlaceholder("player-con")), String.valueOf(playerCON))
                .replace(String.valueOf(getYAMLPlaceholder("player-int")), String.valueOf(playerINT))
                .replace(String.valueOf(getYAMLPlaceholder("player-dex")), String.valueOf(playerDEX))
                .replace(String.valueOf(getYAMLPlaceholder("player-cha")), String.valueOf(playerCHA))
                .replace(String.valueOf(getYAMLPlaceholder("player-wis")), String.valueOf(playerWIS))
                .replace(String.valueOf(getYAMLPlaceholder("player-luk")), String.valueOf(playerLUK))
                .replace(String.valueOf(getYAMLPlaceholder("player-recent-damage")), String.valueOf(playerRecentDamage))
                .replace(String.valueOf(getYAMLPlaceholder("player-recent-damage-formatted")), String.format("%.2f", user.getMaxHealth()))
                .replace(String.valueOf(getYAMLPlaceholder("player-damage")), String.valueOf(playerDamage))
                .replace(String.valueOf(getYAMLPlaceholder("player-damage-formatted")), String.format("%.2f", playerDamage))
                .replace(String.valueOf(getYAMLPlaceholder("player-defense")), String.valueOf(playerDefense))
                .replace(String.valueOf(getYAMLPlaceholder("player-defense-formatted")), String.format("%.2f", playerDefense))
                .replace(String.valueOf(getYAMLPlaceholder("damage")), String.valueOf(playerDamage))
                .replace(String.valueOf(getYAMLPlaceholder("damage-formatted")), String.format("%.2f", playerDamage));
        return string;
    }

    public String parseEntityInfo(Entity entity,String string){

        Integer entityLevel = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_LEVEL, PersistentDataType.INTEGER);
        Double entityEXP = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_EXP, PersistentDataType.DOUBLE);
        Double entityRecentDamage = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_RECENT_DAMAGE, PersistentDataType.DOUBLE);
        Double entityHealth = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_HEALTH, PersistentDataType.DOUBLE);
        Double entityMaxHealth = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_MAX_HEALTH, PersistentDataType.DOUBLE);
        String entityName = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_NAME, PersistentDataType.STRING);
        Double entityDistance = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_DISTANCE, PersistentDataType.DOUBLE);
        Double entityDamage = entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_DAMAGE, PersistentDataType.DOUBLE);
        string = string.replace(String.valueOf(getYAMLPlaceholder("mob-level")), String.valueOf(entityLevel))
                .replace(String.valueOf(getYAMLPlaceholder("mob-exp")), String.valueOf(entityEXP))
                .replace(String.valueOf(getYAMLPlaceholder("mob-damage")), String.valueOf(entityDamage))
                .replace(String.valueOf(getYAMLPlaceholder("mob-recent-damage")), String.valueOf(entityRecentDamage))
                .replace(String.valueOf(getYAMLPlaceholder("damage")), String.valueOf(entityDamage))
                .replace(String.valueOf(getYAMLPlaceholder("mob-health")), String.valueOf(entityHealth))
                .replace(String.valueOf(getYAMLPlaceholder("mob-max-health")), String.valueOf(entityMaxHealth))
                .replace(String.valueOf(getYAMLPlaceholder("mob-name")), String.valueOf(entityName))
                .replace(String.valueOf(getYAMLPlaceholder("mob-distance")), String.valueOf(entityDistance));
        return string;
    }

/*
    public String parseArmorInfo(ItemStack itemStack, String string){
        Double protection = Armor.getProtection(itemStack);
        Double health = Armor.getProtection(itemStack);
        Double regen = Armor.getProtection(itemStack);
        string = string.replace(String.valueOf(getYAMLPlaceholder("armor-protection")), String.valueOf(protection))
                .replace(String.valueOf(getYAMLPlaceholder("armor-health")), String.valueOf(health))
                .replace(String.valueOf(getYAMLPlaceholder("armor-regen")), String.valueOf(regen));
        return string;
    }
*/
    public String parseGearscoreValues(Double value, String string){
        string = string.replace(String.valueOf(getYAMLPlaceholder("gear-score-value")), String.valueOf(value));
        return string;
    }

    public String parseConfigInfo(String string){
        string = string.replace(String.valueOf(getYAMLPlaceholder("block-difficulty-increase")), String.valueOf(SavageRPG.getInstance().getConfig().get("mob-spawn.block-difficulty-increase")));
        return string;
    }

    public String getYAMLPlaceholder(String name){
        return (String) variable.get("placeholders." + name);
    }

}