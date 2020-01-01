package net.prosavage.illyriarpg.utils.handlers.abilities;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.function.Consumer;

public class DropAction implements Listener {

    @EventHandler
    public void playerDrop(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (!(itemStack.getType() == Material.AIR)) {
            if (itemStack.hasItemMeta()) {
                ItemMeta meta = itemStack.getItemMeta();
                if (meta != null) {
                    if (!meta.getPersistentDataContainer().isEmpty()) {
                        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
                        if (persistentDataContainer.has(INamespacedKeys.WEAPON_ABIILTY_NAME, PersistentDataType.STRING)) {
                            String testSkill = persistentDataContainer.get(INamespacedKeys.WEAPON_ABIILTY_NAME, PersistentDataType.STRING);
                            if (persistentDataContainer.has(INamespacedKeys.WEAPON_ABIILTY_IS_DROP_ACTION, PersistentDataType.BYTE)) {
                                byte isDroppable = persistentDataContainer.get(INamespacedKeys.WEAPON_ABIILTY_IS_DROP_ACTION, PersistentDataType.BYTE);
                                if (isDroppable == (byte) 1) {
                                    Consumer<PlayerDropItemEvent> playerDropItemEventConsumer = IllyriaRPG.getInstance().getItemDropInteractions().get(testSkill);
                                    if (playerDropItemEventConsumer != null) {
                                        event.setCancelled(true);
                                        playerDropItemEventConsumer.accept(event);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}