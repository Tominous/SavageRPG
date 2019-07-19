package net.prosavage.savagerpg.hooks.mythicmobs;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.skills.Skill;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Optional;

public class SkillCast {

    public boolean forPlayer(Entity entity,String skillName,Entity trigger) {

        Optional<Skill> maybeSkill = MythicMobs.inst().getSkillManager().getSkill(skillName);
        if (!maybeSkill.isPresent()) {
            return false;
        }
        ActivePlayer ap = new ActivePlayer(entity);
        Skill skill = maybeSkill.get();
        SkillMetadata data = new SkillMetadata(SkillTrigger.API,ap,BukkitAdapter.adapt(trigger));
        if (skill.usable(data,SkillTrigger.API)) {
            skill.execute(data);
            return true;
        }
    return false;
    }
}