package net.prosavage.savageequipment.listener;

import net.prosavage.savageequipment.builder.Item;
import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import net.prosavage.savageequipment.somewhatusefulstuff.SpawnMob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener {

    Color Color = new Color();
    SpawnMob SpawnMob = new SpawnMob();
    Item Item = new Item();

    @EventHandler
    public void damageListen(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();

            Double randomDamage = Double.parseDouble(String.valueOf(Item.getDamage(item)));
            String randomDamageRounded = String.format("%.2f", randomDamage);
            if (randomDamage != null) {
                e.setDamage(Double.parseDouble(randomDamageRounded));
                String hologramName = Color.ify("&c" + String.valueOf(e.getDamage()));
                SpawnMob.damageIndicator(e.getEntity(), String.valueOf(hologramName));
            }
        }
    }
}
