package me.max.library;

import me.max.library.listeners.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Library extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        info("Initialising listeners..");
        try {
            new PlayerInteractListener(this);
        } catch (Exception e){
            error("Error initialising listeners");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void info(String s){
        getLogger().info(s);
    }

    public void error(String s){
        getLogger().severe(s);
    }
}
