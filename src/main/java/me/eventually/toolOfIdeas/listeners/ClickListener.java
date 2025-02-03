package me.eventually.toolOfIdeas.listeners;

import me.eventually.toolOfIdeas.items.VampireEffectGenerator;
import me.eventually.toolOfIdeas.utils.ItemUtil;
import me.eventually.toolOfIdeas.utils.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        if (item != null && ItemUtil.isPluginItem(item)){
            switch (ItemUtil.getPluginItemType(item)){
                case "VAMPIRE_EFFECT_GENERATOR":
                    event.setCancelled(true);
                    if (VampireEffectGenerator.isVampireEffectActive(event.getPlayer())) {
                        MessageUtil.sendMessage(event.getPlayer(), "&c&l不可以堆叠使用！");
                        return;
                    }
                    item.setAmount(item.getAmount() - 1);
                    VampireEffectGenerator.trigger(event.getPlayer());
            }
        }
    }
}
