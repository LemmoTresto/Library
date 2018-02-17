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
import me.max.library.bookshelves.BookShelf;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener{

    private Library library;

    public BlockBreakListener(Library library) {
        this.library = library;

        this.library.getServer().getPluginManager().registerEvents(this, library);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event){
        if (!(event.getBlock().getType() == Material.BOOKSHELF)) return; // not a bookshelf.

        //get bookshelf
        BookShelf bookShelf = library.getBookShelfManager().getBookShelf(event.getBlock().getLocation());

        //check if it is null
        if (bookShelf == null) return;

        //drop items
        for (ItemStack item : bookShelf.getItems()){
            if (item == null || item.getType() == Material.AIR) continue;
            try {
                bookShelf.getLocation().getWorld().dropItemNaturally(bookShelf.getLocation(), item);
            } catch (Exception e){
                library.error("Something went wrong with dropping an item");
                e.printStackTrace();
            }
        }

        //remove bookshelf.
        library.getBookShelfManager().removeBookShelf(bookShelf);
    }
}
