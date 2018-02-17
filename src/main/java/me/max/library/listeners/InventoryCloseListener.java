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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryCloseListener implements Listener {

    private Library library;

    public InventoryCloseListener(Library library){
        this.library = library;

        this.library.getServer().getPluginManager().registerEvents(this, library);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if (!event.getInventory().getTitle().startsWith("Bookshelf - ")) return; // we only want our own inv.

        //get bookshelf.
        String[] coords = event.getInventory().getTitle().replace("Bookshelf - ", "").split(", "); //retrieve the coordinates in strings.
        BookShelf bookShelf = library.getBookShelfManager().getBookShelf(new Location(event.getPlayer().getWorld(), Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2])));

        if (bookShelf == null) return;

        if (Arrays.asList(event.getInventory().getContents()).equals(bookShelf.getLocation())) return; //the inventory has not changed.

        //an inventory returns a null itemstack if it is air (nothing)
        //we convert this to an air itemstack so this can be saved correctly.
        List<ItemStack> contents = new ArrayList<>();
        //loop over contents
        for (ItemStack itemStack : event.getInventory().getContents()){
            if (itemStack == null){
                //itemstack is null so we convert it to air.
                contents.add(new ItemStack(Material.AIR));
            } else {
                contents.add(itemStack);
            }
        }
        //put in the correct contents.
        bookShelf.setItems(contents);
    }
}
