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

package me.max.library.bookshelves;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BookShelf {

    private Location location;
    private List<ItemStack> items;

    public BookShelf(Location location, List<ItemStack> items){
        this.location = location;
        this.items = items;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public Location getLocation() {
        return location;
    }

    public ItemStack getItem(int index){
        return items.get(index);
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }
}
