package me.eventually.toolOfIdeas.items;

import me.eventually.toolOfIdeas.ToolOfIdeas;
import me.eventually.toolOfIdeas.utils.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class VampireEffectGenerator {
    public static List<Player> players = new ArrayList<>();


    public static void trigger(Player p){
        MessageUtil.sendMessage(p, "&c&l吸血效果已发动，持续10秒！");
        players.add(p);
        ToolOfIdeas plugin = ToolOfIdeas.instance;
        delayRemoval(p, plugin, 200);
    }
    public static void delayRemoval(Player p, Plugin plugin, int delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                players.remove(p);
                MessageUtil.sendMessage(p, "&c&l吸血效果已结束！");
            }
        }.runTaskLater(plugin, delay);
    }
    public static boolean isVampireEffectActive(Player p){
        return players.contains(p);
    }

}
