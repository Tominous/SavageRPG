package net.prosavage.savagerpg.api;

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
}
