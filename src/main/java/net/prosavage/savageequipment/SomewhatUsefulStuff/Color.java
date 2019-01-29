package me.kingalteriv.pragmata.SomewhatUsefulStuff;

import org.bukkit.ChatColor;

public class Color
{
    public String ify(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String strip(String s)
    {
        return ChatColor.stripColor(s);
    }
}
