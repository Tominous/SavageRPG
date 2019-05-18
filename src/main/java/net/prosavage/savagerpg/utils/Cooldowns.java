package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class Cooldowns {

    public void setAttack(Player p, double seconds) {
        p.setMetadata("SavageEquipments-" + p.getUniqueId() + "-attack-cooldown", new FixedMetadataValue(SavageRPG.getInstance(), System.currentTimeMillis()));
    }

    public Double getAttack(Player p){
        double oldCooldown = Double.valueOf(String.valueOf(p.getMetadata("SavageEquipments-" + p.getUniqueId() + "-attack-cooldown").get(0).value()));
        double nowCooldown = System.currentTimeMillis();
        return (nowCooldown - oldCooldown) / 1000;
    }

    public boolean isOnCooldown(Player p, long seconds) {

        if (!(p.getMetadata("SavageEquipments-" + p.getUniqueId() + "-attack-cooldown").isEmpty())) {
            double oldCooldown = Long.valueOf(String.valueOf(p.getMetadata("SavageEquipments-" + p.getUniqueId() + "-attack-cooldown").get(0).value()));
            double nowCooldown = System.currentTimeMillis();
            double differenceCooldown = nowCooldown - oldCooldown;

            double convertedCoodlown = differenceCooldown/1000;

            if (convertedCoodlown > seconds) {
                setAttack(p, nowCooldown);
                return false;
            }
            return true;
        }else{
            setAttack(p, System.currentTimeMillis());
            return false;
        }
    }
}
