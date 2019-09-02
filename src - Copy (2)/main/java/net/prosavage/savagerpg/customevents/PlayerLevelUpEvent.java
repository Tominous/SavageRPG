package net.prosavage.savagerpg.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerLevelUpEvent extends PlayerEvent {

    int oldLevel;
    int newLevel;
    private static final HandlerList handlers = new HandlerList();

    public PlayerLevelUpEvent(Player player, int oldLevel) {
        super(player);
        this.oldLevel = oldLevel;
        this.newLevel = oldLevel + 1;
    }

    public int getOldLevel() {
        return this.oldLevel;
    }

    public int getNewLevel() {
        return this.newLevel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
