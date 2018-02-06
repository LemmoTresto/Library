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

import me.max.library.Library;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookShelfManager {

    private Library library;

    private List<BookShelf> bookShelfList;

    public BookShelfManager(Library library){
        this.library = library;
        this.bookShelfList = new ArrayList<>();

        YamlConfiguration data = loadData();

        for (String book : data.getKeys(false)){
            //get the configuration section for the book
            ConfigurationSection newData = data.getConfigurationSection(book);

            if (newData.getString("location").equals("null")) continue; // check if the bookshelf exists.

            //get section of location
            ConfigurationSection loc = newData.getConfigurationSection("location");
            Location location = new Location(Bukkit.getWorld(loc.getString("world")), loc.getDouble("x"), loc.getDouble("y"), loc.getDouble("z"));

            //get items
            ConfigurationSection items = newData.getConfigurationSection("items");
            List<ItemStack> bookshelfItems = new ArrayList<>();


            //loop and add items to list
            for (String item : items.getKeys(false)){
                bookshelfItems.add(items.getItemStack(item));
            }

            //create and add a bookshelf to the list.
            addBookShelf(new BookShelf(location, bookshelfItems));
        }
    }

    public YamlConfiguration loadData(){
        return YamlConfiguration.loadConfiguration(new File(library.getDataFolder() + "/data/" + "data.yml"));
    }

    public void saveData(){
        //load data
        YamlConfiguration data = loadData();

        //iterate over bookshelves
        for (int i = 0; i < bookShelfList.size(); i++){
            BookShelf bookShelf = bookShelfList.get(i);

            //set location
            data.set(i + ".location.world", bookShelf.getLocation().getWorld().toString());
            data.set(i + ".location.x", bookShelf.getLocation().getX());
            data.set(i + ".location.y", bookShelf.getLocation().getY());
            data.set(i + ".location.z", bookShelf.getLocation().getZ());

            //set items
            for (int j = 0; j < bookShelf.getItems().size(); j++){
                data.set(i + ".items." + j, bookShelf.getItem(j));
            }
        }

        //save the data.
        try {
            data.save(new File(library.getDataFolder() + "/data/", "data.yml"));
        } catch (IOException e) {
            library.error("Could not save book shelf data! All data that was not saved will be lost.");
            e.printStackTrace();
        }

    }

    public BookShelf getBookShelf(Location location){
        for (BookShelf bookShelf : bookShelfList){
            if (bookShelf.getLocation().equals(location)) return bookShelf;
        }
        return null;
    }

    public void addBookShelf(BookShelf bookShelf){
        bookShelfList.add(bookShelf);
    }

    public void removeBookShelf(BookShelf bookShelf){
        if (bookShelfList.contains(bookShelf)) bookShelfList.remove(bookShelf);
    }
}
