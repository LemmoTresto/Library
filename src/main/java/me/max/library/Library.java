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

package me.max.library;

import me.max.library.bookshelves.BookShelfManager;
import me.max.library.listeners.BlockBreakListener;
import me.max.library.listeners.BlockPlaceListener;
import me.max.library.listeners.InventoryInteractListener;
import me.max.library.listeners.PlayerInteractListener;
import me.max.library.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Library extends JavaPlugin {

    private BookShelfManager bookShelfManager = null;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        ConfigUtil.saveDefaultConfig(this);
        reloadConfig();

        info("Initialising listeners..");
        try {
            new PlayerInteractListener(this);
            new InventoryInteractListener(this);
            new BlockBreakListener(this);
            new BlockPlaceListener(this);
            info("Successfully initialised listeners!");
        } catch (Exception e){
            error("Error initialising listeners");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }

        info("Started successfully in " + (start - System.currentTimeMillis()) + "ms");
    }

    @Override
    public void onDisable() {
    }

    public void info(String s){
        getLogger().info(s);
    }

    public void error(String s){
        getLogger().severe(s);
    }

    public BookShelfManager getBookShelfManager() {
        return bookShelfManager;
    }
}
