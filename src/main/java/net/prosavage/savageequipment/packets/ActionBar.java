package net.prosavage.savageequipment.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import static com.comphenix.protocol.PacketType.Play.Client.CHAT;
import static org.bukkit.Bukkit.getName;

public class ActionBar {

    public void sendActionText(Player player, String message) {
        if (message == null || message.isEmpty()) {
            return;
        }

        PacketContainer chat = new PacketContainer(PacketType.Play.Server.CHAT);
        chat.getBytes().writeSafely(0, (byte) 2);
        chat.getChatComponents().write(0, WrappedChatComponent.fromText(message));

//        pluginPackets.add(chat.getHandle());

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, chat);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}