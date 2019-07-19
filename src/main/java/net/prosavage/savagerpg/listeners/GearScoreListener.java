package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.PDCPlayer;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GearScoreListener implements Listener {

    net.prosavage.savagerpg.utils.Placeholder Placeholder = new Placeholder();
    net.prosavage.savagerpg.itembuilder.Armor Armor = new Armor();
    net.prosavage.savagerpg.utils.Formula Formula = new Formula();

    @EventHandler
    public void gearScore(PlayerJoinEvent e){
        Player player = e.getPlayer();

        PDCPlayer pdcPlayer = new PDCPlayer(player);

        new BukkitRunnable(){
            @Override
            public void run() {

                ItemStack helmet = player.getInventory().getHelmet();
                ItemStack chestplate = player.getInventory().getChestplate();
                ItemStack leggings = player.getInventory().getLeggings();
                ItemStack boots = player.getInventory().getBoots();

                assert helmet != null;
                assert chestplate != null;
                assert leggings != null;
                assert boots != null;

                double protection = Armor.getProtection(helmet) + Armor.getProtection(chestplate) + Armor.getProtection(leggings) + Armor.getProtection(boots);
                double health = Armor.getHealth(helmet) + Armor.getHealth(chestplate) + Armor.getHealth(leggings) + Armor.getHealth(boots);
                double regen = Armor.getRegen(helmet) + Armor.getRegen(chestplate) + Armor.getRegen(leggings) + Armor.getRegen(boots);

                double protectionScore = Formula.eval(Placeholder.parseGearscoreValues(protection, SavageRPG.getInstance().getConfig().getString("gear-score.multiplier.armor.protection")));
                double healthScore = Formula.eval(Placeholder.parseGearscoreValues(health, SavageRPG.getInstance().getConfig().getString("gear-score.multiplier.armor.health")));
                double regenScore = Formula.eval(Placeholder.parseGearscoreValues(regen, SavageRPG.getInstance().getConfig().getString("gear-score.multiplier.armor.regen")));

                pdcPlayer.setGearScore(protectionScore + healthScore + regenScore);

            }
        }.runTaskLaterAsynchronously(SavageRPG.getInstance(), 20L);

    }
}
