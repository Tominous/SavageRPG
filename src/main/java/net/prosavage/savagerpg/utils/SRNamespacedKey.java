package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.NamespacedKey;

public class SRNamespacedKey {

    NamespacedKey namespacedKey_weapon_type = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Type");
    NamespacedKey namespacedKey_weapon_rarity = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Rarity");
    NamespacedKey namespacedKey_weapon_class = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Class");
    NamespacedKey namespacedKey_weapon_level = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Level");
    NamespacedKey namespacedKey_weapon_min_damage = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Min");
    NamespacedKey namespacedKey_weapon_max_damage = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey namespacedKey_weapon_cooldown = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey namespacedKey_weapon_damage_per_second = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Per-Second");
    NamespacedKey namespacedKey_weapon_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Gem");
    NamespacedKey namespacedKey_weapon_have_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-NoGem");

    NamespacedKey namespacedKey_armor_type = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Type");
    NamespacedKey namespacedKey_armor_rarity = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Rarity");
    NamespacedKey namespacedKey_armor_class = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Class");
    NamespacedKey namespacedKey_armor_level = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Level");
    NamespacedKey namespacedKey_armor_protection = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Protection");
    NamespacedKey namespacedKey_armor_health = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Health");
    NamespacedKey namespacedKey_armor_regen = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Regen");
    NamespacedKey namespacedKey_armor_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Gem");
    NamespacedKey namespacedKey_armor_have_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-NoGem");

    NamespacedKey namespacedKey_player_level = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Level");
    NamespacedKey namespacedKey_player_exp = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-EXP");
    NamespacedKey namespacedKey_player_max_exp = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Max-EXP");
    NamespacedKey namespacedKey_player_skill_points = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Skill-Points");
    NamespacedKey namespacedKey_player_recent_damage = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Recent-Damage");

    // WEAPON STUFF

    public NamespacedKey getNamespacedKey_weapon_type(){
        return this.namespacedKey_weapon_type;
    }

    public NamespacedKey getNamespacedKey_weapon_rarity(){
        return this.namespacedKey_weapon_rarity;
    }

    public NamespacedKey getNamespacedKey_weapon_class(){
        return this.namespacedKey_weapon_class;
    }

    public NamespacedKey getNamespacedKey_weapon_level(){
        return this.namespacedKey_weapon_level;
    }

    public NamespacedKey getNamespacedKey_weapon_min_damage(){
        return this.namespacedKey_weapon_min_damage;
    }

    public NamespacedKey getNamespacedKey_weapon_max_damage(){
        return this.namespacedKey_weapon_max_damage;
    }

    public NamespacedKey getNamespacedKey_weapon_cooldown(){
        return this.namespacedKey_weapon_cooldown;
    }

    public NamespacedKey getNamespacedKey_weapon_damage_per_second(){
        return this.namespacedKey_weapon_damage_per_second;
    }

    public NamespacedKey getNamespacedKey_weapon_gem(){
        return this.namespacedKey_weapon_gem;
    }

    public NamespacedKey getNamespacedKey_weapon_have_gem(){
        return this.namespacedKey_weapon_have_gem;
    }

    // ARMOR STUFF

    public NamespacedKey getNamespacedKey_armor_type(){
        return this.namespacedKey_armor_type;
    }

    public NamespacedKey getNamespacedKey_armor_rarity(){
        return this.namespacedKey_armor_rarity;
    }

    public NamespacedKey getNamespacedKey_armor_class(){
        return this.namespacedKey_armor_class;
    }

    public NamespacedKey getNamespacedKey_armor_level(){
        return this.namespacedKey_armor_level;
    }

    public NamespacedKey getNamespacedKey_armor_protection(){
        return this.namespacedKey_armor_protection;
    }

    public NamespacedKey getNamespacedKey_armor_health(){
        return this.namespacedKey_armor_health;
    }

    public NamespacedKey getNamespacedKey_armor_regen(){
        return this.namespacedKey_armor_regen;
    }

    public NamespacedKey getNamespacedKey_armor_gem(){
        return this.namespacedKey_armor_gem;
    }

    public NamespacedKey getNamespacedKey_armor_have_gem(){
        return this.namespacedKey_armor_have_gem;
    }

    // PLAYER STUFF

    public NamespacedKey getNamespacedKey_player_level(){
        return this.namespacedKey_player_level;
    }

    public NamespacedKey getNamespacedKey_player_exp(){
        return this.namespacedKey_player_exp;
    }

    public NamespacedKey getNamespacedKey_player_max_exp(){
        return this.namespacedKey_player_max_exp;
    }

    public NamespacedKey getNamespacedKey_player_skill_points(){
        return this.namespacedKey_player_skill_points;
    }

    public NamespacedKey getNamespacedKey_player_recent_damage(){
        return this.namespacedKey_player_recent_damage;
    }


}


