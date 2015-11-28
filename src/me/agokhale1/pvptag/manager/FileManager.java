package me.agokhale1.pvptag.manager;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import org.bukkit.*;
import org.bukkit.configuration.file.*;
import org.bukkit.inventory.*;

public class FileManager {
	
	private static FileManager taggedPlayersFile = new FileManager("taggedplayers");
	private static FileManager pvpZombiesFile = new FileManager("pvpzombies");
	
	public static FileManager getTaggedPlayersFile()
	{
		return taggedPlayersFile;
	}
	
	public static FileManager getPvPZombiesFile()
	{
		return pvpZombiesFile;		
	}
	
	private File file;
	private FileConfiguration fileCfg;
	
	public FileManager(String name)
	{
		
		Bukkit.getPluginManager().getPlugin("PvPTag").getDataFolder().mkdir();
		file = new File(Bukkit.getPluginManager().getPlugin("PvPTag").getDataFolder(), name + ".yml");
		
		if(!file.exists())
		{
			
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				Bukkit.getLogger().log(Level.SEVERE, "Could not create the file: " + name + ".yml");
			}
			
		}
		fileCfg = YamlConfiguration.loadConfiguration(file);
	}
	
	public void saveList(List<String> list)
	{
		
		fileCfg.set("UUIDs", list);
		try
		{
			fileCfg.save(file);
		}
		catch (IOException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Could not save the file!");
		}
		
	}
	
	public List<String> loadList(String path)
	{
		return fileCfg.getStringList(path);
	}
	
	public void saveInventory(String path, ItemStack[] items)
	{
		
		fileCfg.set("ZombieInventories." + path, items);
		try
		{
			fileCfg.save(file);
		}
		catch (IOException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Could not save the file!");
		}
		
	}
	
	public void removeInventory(String path, Object obj)
	{
		
		fileCfg.set("ZombieInventories." + path, obj);
		try
		{
			fileCfg.save(file);
		}
		catch (IOException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Could not save the file!");
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemStack[] getInventory(String path)
	{
		
		ItemStack[] contents = null;
		Object o = fileCfg.get(path);
		
		if(o instanceof ItemStack[])
			contents = (ItemStack[]) o;
		else if(o instanceof List)
		{
			List l = (List) o;
			contents = (ItemStack[]) l.toArray(new ItemStack[0]);
		}
		return contents;
	}
	
}