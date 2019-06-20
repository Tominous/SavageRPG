package net.prosavage.savagerpg.api;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.NamespacedKey;

public class SNamespacedKey {

    NamespacedKey weapon_type = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Type");
    NamespacedKey weapon_rarity = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Rarity");
    NamespacedKey weapon_class = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Class");
    NamespacedKey weapon_level = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Level");
    NamespacedKey weapon_min_damage = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Min");
    NamespacedKey weapon_max_damage = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey weapon_cooldown = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey weapon_damage_per_second = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Per-Second");
    NamespacedKey weapon_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Gem");
    NamespacedKey weapon_have_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-NoGem");

    NamespacedKey armor_type = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Type");
    NamespacedKey armor_rarity = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Rarity");
    NamespacedKey armor_class = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Class");
    NamespacedKey armor_level = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Level");
    NamespacedKey armor_protection = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Protection");
    NamespacedKey armor_health = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Health");
    NamespacedKey armor_regen = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Regen");
    NamespacedKey armor_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Gem");
    NamespacedKey armor_have_gem = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-NoGem");

    NamespacedKey player_level = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Level");
    NamespacedKey player_exp = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-EXP");
    NamespacedKey player_max_exp = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Max-EXP");
    NamespacedKey player_skill_points = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Skill-Points");
    NamespacedKey player_recent_damage = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Player-Recent-Damage");

    // WEAPON STUFF

    public NamespacedKey getWeaponType(){
        return this.weapon_type;
    }

    public NamespacedKey getWeaponRarity(){
        return this.weapon_rarity;
    }

    public NamespacedKey getWeaponClass(){
        return this.weapon_class;
    }

    public NamespacedKey getWeaponLevel(){
        return this.weapon_level;
    }

    public NamespacedKey getWeaponMinDamage(){
        return this.weapon_min_damage;
    }

    public NamespacedKey getWeaponMaxDamage(){
        return this.weapon_max_damage;
    }

    public NamespacedKey getWeaponCooldown(){
        return this.weapon_cooldown;
    }

    public NamespacedKey getWeaponDPS(){
        return this.weapon_damage_per_second;
    }

    public NamespacedKey getWeaponGems(){
        return this.weapon_gem;
    }

    public NamespacedKey getDoesWeaponHaveGems(){
        return this.weapon_have_gem;
    }

    // ARMOR STUFF

    public NamespacedKey getArmorType(){
        return this.armor_type;
    }

    public NamespacedKey getArmorRarity(){
        return this.armor_rarity;
    }

    public NamespacedKey getArmorClass(){
        return this.armor_class;
    }

    public NamespacedKey getArmorLevel(){
        return this.armor_level;
    }

    public NamespacedKey getArmorProtection(){
        return this.armor_protection;
    }

    public NamespacedKey getArmorHealth(){
        return this.armor_health;
    }

    public NamespacedKey getArmorRegen(){
        return this.armor_regen;
    }

    public NamespacedKey getArmorGem(){
        return this.armor_gem;
    }

    public NamespacedKey getDoesArmorHaveGem(){
        return this.armor_have_gem;
    }

    // PLAYER STUFF

    public NamespacedKey getPlayerLevel(){
        return this.player_level;
    }

    public NamespacedKey getPlayerExp(){
        return this.player_exp;
    }

    public NamespacedKey getPlayerMaxExp(){
        return this.player_max_exp;
    }

    public NamespacedKey getPlayerSkillpoints(){
        return this.player_skill_points;
    }

    public NamespacedKey getPlayerRecentDamage(){
        return this.player_recent_damage;
    }


}
