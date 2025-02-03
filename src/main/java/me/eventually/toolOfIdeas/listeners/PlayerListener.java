package me.eventually.toolOfIdeas.listeners;

import me.eventually.toolOfIdeas.items.VampireEffectGenerator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        VampireEffectGenerator.players.remove(event.getPlayer());
    }
}
