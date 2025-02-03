package me.eventually.toolOfIdeas;

import me.eventually.toolOfIdeas.commands.MainCommand;
import me.eventually.toolOfIdeas.listeners.ClickListener;
import me.eventually.toolOfIdeas.listeners.DamageListener;
import me.eventually.toolOfIdeas.listeners.PlayerListener;
import me.eventually.toolOfIdeas.utils.PluginUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ToolOfIdeas extends JavaPlugin {
    public static ToolOfIdeas instance;

    @Override
    public void onEnable() {
        if (PluginUtil.isFolia()){
            getLogger().info("This plugin does not support Folia. Disabling plugin...");
            manualDisable();
            return;
        }
        instance = this;

        // Plugin startup logic
        if (getServer().getPluginManager().getPlugin("NBTAPI") == null){
            getLogger().severe("NBTAPI not found! Please install NBTAPI to use this plugin!");
            manualDisable();
            return;
        }
        getLogger().info("Registering Events...");
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info("Registering Commands...");
        Bukkit.getPluginCommand("toi").setExecutor(new MainCommand());
        Bukkit.getPluginCommand("toi").setTabCompleter(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void manualDisable(){
        instance = null;
        getLogger().severe("Disabling plugin...");
        getServer().getPluginManager().disablePlugin(this);
    }
}
