package me.max.library.listeners;

import me.max.library.Library;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private Library library;

    public PlayerInteractListener(Library library){
        this.library = library;

        this.library.getServer().getPluginManager().registerEvents(this, library);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return; //return if it is not right clicking
        if (!(event.getClickedBlock().getType() == Material.BOOKSHELF)) return; //return if it is not a book shelf.

        Player p = event.getPlayer();

        if (!p.hasPermission("library.open")) return; //return if no permission.


    }
}
