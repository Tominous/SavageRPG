package net.prosavage.savagerpg.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Color {

    public String ify(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String strip(String s)
    {
        return ChatColor.stripColor(s);
    }

    public List<String> ifying(List<String> list){
        List<String> returnString = new ArrayList<>();
        for (String string : list){
            string = ify(string);
            returnString.add(string);
        }
        return returnString;
    }

}
