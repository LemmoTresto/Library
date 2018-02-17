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
import org.bukkit.event.Listener;

public class InventoryMoveItemListener implements Listener {

    private Library library;

    public InventoryMoveItemListener(Library library){
        this.library = library;

        this.library.getServer().getPluginManager().registerEvents(this, library);
    }

//    @EventHandler
//    public void onInventoryItemMove(InventoryClickEvent event){
//        if (!event.getInventory().getTitle().startsWith("Bookshelf - ")) return; //return if it is not an inventory from us.
//
//        for (ItemStack item : Arrays.asList(event.getInventory().getContents())){
//            Material mat = item.getType();
//            if (mat != Material.BOOK && mat != Material.BOOK_AND_QUILL && mat != Material.ENCHANTED_BOOK && mat != Material.KNOWLEDGE_BOOK && mat != Material.WRITTEN_BOOK) event.setCancelled(true); //we only want books.
//        }
//    }

}
