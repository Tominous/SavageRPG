package me.kingalteriv.pragmata.Loop;

import com.SirBlobman.combatlogx.utility.CombatUtil;
import com.sun.media.jfxmedia.logging.Logger;
import me.kingalteriv.pragmata.Builder.Armor;
import me.kingalteriv.pragmata.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.junit.experimental.max.MaxHistory;

public class JoinRegenLoop implements Listener {

    Armor Armor = new Armor();

    @EventHandler
    @SuppressWarnings("Deprecated")
    public void joinRegenListener(PlayerJoinEvent e){

        new BukkitRunnable()
        {
            public void run()
            {
                if ((e.getPlayer().isOnline())) {

                    Player player = e.getPlayer();
                    ItemStack helmet = player.getInventory().getHelmet();
                    ItemStack chestplate = player.getInventory().getChestplate();
                    ItemStack leggings = player.getInventory().getLeggings();
                    ItemStack boots = player.getInventory().getBoots();

                    double helmetRegen = Armor.getRegen(helmet);
                    double chestplateRegen = Armor.getRegen(chestplate);
                    double leggingsRegen = Armor.getRegen(leggings);
                    double bootsRegen = Armor.getRegen(boots);

                    double health = player.getHealth();

                    double regenValue = Double.parseDouble(String.format("%.2f", helmetRegen + chestplateRegen + leggingsRegen + bootsRegen));

                    double maxHealth = player.getMaxHealth();

                    if (!CombatUtil.isInCombat(player)) {
                        if (health <= (maxHealth - 1.0)) {
                            if ((health >= 0.01)) {
                                player.setHealth(health + (maxHealth*(regenValue / 500)));
                            }
                        }
                    }
                }

                Bukkit.getLogger().info("TEST 1");
                if (!(e.getPlayer().isOnline())){
                    this.cancel();
                    return;
            }

            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);
    }
}
