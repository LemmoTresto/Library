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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Arrays;

public class InventoryCloseListener implements Listener {

    private Library library;

    public InventoryCloseListener(Library library){
        this.library = library;

        this.library.getServer().getPluginManager().registerEvents(this, library);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if (!event.getInventory().getTitle().equalsIgnoreCase("Bookshelf")) return; // we only want our own inv.

        //get bookshelf.
        BookShelf bookShelf = library.getBookShelfManager().getBookShelf(event.getInventory().getLocation());

        if (bookShelf == null) return;

        if (Arrays.asList(event.getInventory().getContents()).equals(bookShelf.getLocation())) return; //the inventory has not changed.

        //put new items in.
        bookShelf.setItems(Arrays.asList(event.getInventory().getContents()));
    }
}
