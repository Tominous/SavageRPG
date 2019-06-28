package net.prosavage.savagerpg.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class PDCPlayer {

    SNamespacedKey SNamespacedKey = new SNamespacedKey();

    Player player;
    int level;
    double exp;
    double maxExp;
    int attributePoints;
    int strengthPoints;
    int intelligencePoints;
    int constitutionPoints;
    int dexterityPoints;
    int charismaPoints;
    int wisdomPoints;
    int luckPoints;

    public PDCPlayer(OfflinePlayer p){
        this.player = p.getPlayer();
    }

    public int getLevel(){
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerLevel(),PersistentDataType.INTEGER));
    }

    public double getExp() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerEXP(),PersistentDataType.DOUBLE));
    }

    public double getMaxExp(){
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerMaxEXP(),PersistentDataType.DOUBLE));
    }

    public int getAttributePoints() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerAttributePoints(),PersistentDataType.INTEGER));
    }

    public int getStrengthPoints(){
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerStrengthAttributes(),PersistentDataType.INTEGER));
    }

    public int getIntelligencePoints() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerIntelligenceAttributes(),PersistentDataType.INTEGER));
    }

    public int getConstitutionPoints() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerConstitutionAttributes(),PersistentDataType.INTEGER));
    }

    public int getDexterityPoints() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerDexterityAttributes(),PersistentDataType.INTEGER));
    }

    public int getCharismaPoints() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerCharismaAttributes(),PersistentDataType.INTEGER));
    }

    public int getWisdomPoints() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerWisdomAttributes(),PersistentDataType.INTEGER));
    }

    public int getLuckPoints() {
        return Objects.requireNonNull(this.player.getPersistentDataContainer().get(SNamespacedKey.getPlayerLuckAttributes(),PersistentDataType.INTEGER));
    }
}
