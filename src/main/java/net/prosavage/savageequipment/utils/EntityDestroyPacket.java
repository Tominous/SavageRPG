package net.prosavage.savageequipment.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class EntityDestroyPacket {

    PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_DESTROY);

    public boolean destroyEntity(Player player, Entity entity){
        int[] EntityIDs = {entity.getEntityId()};
        packet.getIntegers().write(0, 1);
        packet.getIntegerArrays().write(0, EntityIDs);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId() != player.getUniqueId()) {
                try {
                    ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Cannot send packet " + packet, e);
                }
            }
        }
        return true;
    }

}
