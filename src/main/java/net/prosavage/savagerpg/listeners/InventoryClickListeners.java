package net.prosavage.savagerpg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListeners implements Listener {


    @EventHandler
    public void AttributeGUI(InventoryClickEvent e){
        String inventoryName = e.getView().getTitle();
    }

}
