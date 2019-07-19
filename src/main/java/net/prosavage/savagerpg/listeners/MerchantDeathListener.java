package net.prosavage.savagerpg.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MerchantDeathListener implements Listener {

    @EventHandler
    public void merchantDeath(EntityDeathEvent e){
        if (e.getEntity() instanceof WanderingTrader){
            WanderingTrader wanderingTrader = (WanderingTrader) e.getEntity();
            Entity wanderingTraderPassenger = wanderingTrader.getPassengers().get(0);
            Entity wanderingTraderPassengerPassenger = wanderingTraderPassenger.getPassengers().get(0);
            wanderingTraderPassengerPassenger.remove();
            wanderingTraderPassenger.remove();
        }
    }
}
