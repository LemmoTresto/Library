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

package me.max.library.utils;

import me.max.library.Library;
import org.bukkit.Bukkit;

import java.io.*;

public class ConfigUtil {

    public static void saveDefaultConfig(Library library) {
        if (!(new File(library.getDataFolder(), "config.yml").exists())) {
            library.info("Config file does not exist, creating it now..");
            saveResource(library, "config.yml", "config.yml");
            library.info("Written config file successfully.");
        }
    }

    private static void saveResource(Library library, String resourcePath, String destination) {
        InputStream in = library.getResource(resourcePath);

        File outFile = new File(library.getDataFolder(), destination);
        new File(library.getDataFolder().toString()).mkdirs();

        try {
            OutputStream out = new FileOutputStream(outFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (IOException ex) {
            library.error("Could not save " + outFile.getName() + " to " + outFile);
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(library);
        }
    }
}
