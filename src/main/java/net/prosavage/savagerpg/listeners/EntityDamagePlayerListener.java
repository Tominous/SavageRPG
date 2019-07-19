package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamagePlayerListener implements Listener {

    Placeholder Placeholder = new Placeholder();
    Color Color = new Color();
    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    FileConfiguration SConfig = SavageRPG.getInstance().getConfig();
    Formula Formula = new Formula();

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            Entity entity = e.getDamager();
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                SavageRPG.getInstance().sendConsole("" + e.getDamage());
                double damage = Formula.eval(Placeholder.parsePlayerInfo(player, String.valueOf(SConfig.get("formulas.player-defense"))));
                e.setDamage(damage);
                SavageRPG.getInstance().sendConsole("" + e.getDamage());
            }

        }
    }
}
