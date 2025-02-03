package me.eventually.toolOfIdeas.listeners;

import me.eventually.toolOfIdeas.items.VampireEffectGenerator;
import me.eventually.toolOfIdeas.utils.MessageUtil;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;
import java.util.Objects;


public class DamageListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){

        if (event.getDamager() instanceof Player damager){
            if (VampireEffectGenerator.isVampireEffectActive(damager)){
                Double health = damager.getHealth();
                damager.setHealth(Math.min(Objects.requireNonNull(damager.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue(), damager.getHealth() + event.getFinalDamage()));
                Double newHealth = damager.getHealth();
                DecimalFormat df = new DecimalFormat("#.#");
                if (health < newHealth){
                    MessageUtil.sendMessage(damager, "&c&l你吸取了" + df.format(newHealth - health) + "点血量！");
                }
            }
        }
    }
}
