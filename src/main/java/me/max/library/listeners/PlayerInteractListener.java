/*
 *
 *  * Library - Simple plugin which allows you to place books in book shelves!
 *  * Copyright (C) 2018 Max Berkelmans AKA LemmoTresto
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
