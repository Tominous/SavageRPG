package net.prosavage.illyriarpg.utils.handlers.abilities;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.function.Consumer;

public class LeftAndRightAction implements Listener {

    @EventHandler
    public void playerLeftAndRightAction(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack playerItem = player.getInventory().getItemInMainHand();
        if (!(playerItem.getType() == Material.AIR)){
            if (player.getInventory().getItemInMainHand().hasItemMeta()){
                ItemMeta meta = playerItem.getItemMeta();
                if (meta != null){
                    if (!meta.getPersistentDataContainer().isEmpty()) {
                        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
                        if (persistentDataContainer.has(INamespacedKeys.WEAPON_ABIILTY_NAME, PersistentDataType.STRING)) {
                            String testSkill = persistentDataContainer.get(INamespacedKeys.WEAPON_ABIILTY_NAME, PersistentDataType.STRING);
                            if (persistentDataContainer.has(INamespacedKeys.WEAPON_ABIILTY_IS_LEFT_ACTION, PersistentDataType.BYTE)) {
                                byte isLeft = persistentDataContainer.get(INamespacedKeys.WEAPON_ABIILTY_IS_LEFT_ACTION, PersistentDataType.BYTE);
                                if (isLeft == (byte) 1) {
                                    if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                                        Consumer<PlayerInteractEvent> playerInteractEventConsumer = IllyriaRPG.getInstance().getLeftClickInteractions().get(testSkill);
                                        if (playerInteractEventConsumer != null) {
                                            playerInteractEventConsumer.accept(event);
                                        }
                                    }
                                }
                            }
                            if (persistentDataContainer.has(INamespacedKeys.WEAPON_ABIILTY_IS_RIGHT_ACTION, PersistentDataType.BYTE)) {
                                byte isRight = persistentDataContainer.get(INamespacedKeys.WEAPON_ABIILTY_IS_RIGHT_ACTION, PersistentDataType.BYTE);
                                if (isRight == (byte) 1) {
                                    if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
                                        Consumer<PlayerInteractEvent> playerInteractEventConsumer = IllyriaRPG.getInstance().getRightClickInteractions().get(testSkill);
                                        if (playerInteractEventConsumer != null) {
                                           playerInteractEventConsumer.accept(event);
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

}
