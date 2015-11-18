package me.agokhale1.pvptag.util;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import org.bukkit.*;
import org.bukkit.entity.*;

public class TagDataUtil {
	
	private static ArrayList<String> taggedPlayers;
	private static File dir, file;
	
	@SuppressWarnings("unchecked")
	public TagDataUtil()
	{
		
		taggedPlayers = new ArrayList<String>();
		
		dir = Bukkit.getPluginManager().getPlugin("PvPTag").getDataFolder();
		dir.mkdirs();
		
		file = new File(dir + File.separator + "taggedplayers.db");
		
		try
		{
			
			if(file.exists())
			{
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				taggedPlayers = (ArrayList<String>) ois.readObject();
				ois.close();
				
			}
			else
				file.createNewFile();
			
		}
		catch (IOException | ClassNotFoundException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, ChatColor.RED + "Unable to create the tagged players file!");
		}
		
	}
	
	/**
	 * Saves the tagged players ArrayList to a .db file
	 */
	public static void saveFile()
	{
		
		try
		{
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(taggedPlayers);
			oos.close();
			
		}
		catch(IOException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, ChatColor.RED + "Unable to save the tagged players list to the file!");
		}
		
	}
	
	/**
	 * Clears the tagged players ArrayList and deletes the .db file.
	 * <p> NOTE: Requires a re-instantiation of TagDataUtil in order to call any methods! </p>
	 */
	public static void reset()
	{
		
		taggedPlayers.clear();
		
		if(file.exists())
			file.delete();
		
	}
	
	/**
	 * Adds the player to the tagged players ArrayList
	 * @param p Player to be added
	 */
	public static void add(Player p)
	{
		taggedPlayers.add(p.getUniqueId().toString());
	}
	
	/**
	 * Removes the player from the tagged players ArrayList
	 * @param p Player to be removed
	 */
	public static void remove(Player p)
	{
		taggedPlayers.remove(p.getUniqueId().toString());
	}
	
	/**
	 * Checks if a player is tagged
	 * @param p Player to be checked
	 * @return True if the player is tagged, false if not tagged
	 */
	public static boolean contains(Player p)
	{
		return taggedPlayers.contains(p.getUniqueId().toString());
	}
	
	/**
	 * Returns the tagged players ArrayList
	 * @return tagged players ArrayList, null if not instantiated
	 */
	public static List<String> getTaggedPlayers()
	{
		return taggedPlayers;
	}
	
	/**
	 * Resets the player's inventory if they had been tagged prior to logging back in
	 * @param p Player who is joining
	 */
	public static void resetPlayerOnJoin(Player p)
	{
		
		if(!contains(p)) return;
		remove(p);
		
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.updateInventory();
		
		p.sendMessage(ChatColor.BLUE + "[PvPTag] " + ChatColor.RED + "Your inventory was cleared because you logged out while tagged!");
		
	}
	
	/**
	 * Removes the player from the tagged players ArrayList
	 * @param p Player who has died
	 */
	public static void resetPlayerOnDeath(Player p)
	{
		
		if(!contains(p)) return;
		remove(p);
		
		p.sendMessage(ChatColor.BLUE + "[PvPTag] " + ChatColor.GREEN + "You are no longer tagged and can log out safely!");
		
	}
	
}