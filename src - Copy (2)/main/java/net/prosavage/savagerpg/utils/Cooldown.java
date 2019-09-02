package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.builder.Weapon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Cooldown {

    Number Number = new Number();

    public void setSkillCooldown(Player player){
        SPlayer sPlayer = new SPlayer(player);
        sPlayer.setAbilityCooldown(System.currentTimeMillis());
    }

    public double getSkillCooldown(Player player){
        SPlayer sPlayer = new SPlayer(player);
        double cooldown = sPlayer.getAbilityCooldown();
        double nowCooldown = System.currentTimeMillis();
        return (nowCooldown - cooldown) / 1000;
    }

    public boolean hasSkillCooldown(Player player, ItemStack itemStack){
        SPlayer sPlayer = new SPlayer(player);
        if (sPlayer.getAbilityCooldown() != 0){
            double oldCooldown = sPlayer.getAbilityCooldown();
            double newCooldown = System.currentTimeMillis();
            double difference = newCooldown - oldCooldown;
            double converted = difference/1000;
            double itemStackCooldown = new Weapon(itemStack).getAbilityCooldown();
            if (Number.isGreaterOrEqualTo(converted, itemStackCooldown)){
                setSkillCooldown(player);
                return false;
            }
            return true;
        }
        setSkillCooldown(player);
        return false;
    }

}
