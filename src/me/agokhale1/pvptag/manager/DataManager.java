package me.agokhale1.pvptag.manager;

import java.util.*;

import org.bukkit.*;
import org.bukkit.entity.*;

public class DataManager {
	
	List<String> taggedPlayers, pvpZombies;
	
	public DataManager()
	{
		taggedPlayers = FileManager.getTaggedPlayersFile().loadList("UUIDs");
		pvpZombies = FileManager.getPvPZombiesFile().loadList("UUIDs");
	}
	
	public void addTagPlayer(Player p)
	{
		
		if(!tagContains(p))
			taggedPlayers.add(p.getUniqueId().toString());
		
	}
	
	public void removeTagPlayer(Player p)
	{
		
		if(tagContains(p))
			taggedPlayers.remove(p.getUniqueId().toString());
		
	}
	
	public void addPvPZombie(Zombie z)
	{
		
		if(!pvpZContains(z))
			pvpZombies.add(z.getUniqueId().toString());
		
	}
	
	public void removePvPZombie(Zombie z)
	{
		
		if(pvpZContains(z))
			pvpZombies.remove(z.getUniqueId().toString());
		
	}
	
	public boolean tagContains(Player p)
	{
		return taggedPlayers.contains(p.getUniqueId().toString());
	}
	
	public boolean pvpZContains(Zombie z)
	{
		return pvpZombies.contains(z.getUniqueId().toString());
	}
	
	public List<String> getTaggedPlayers()
	{
		return taggedPlayers;
	}
	
	public List<String> getPvPZombies()
	{
		return pvpZombies;
	}
	
	public void resetOnJoin(Player p)
	{
		
		if(!tagContains(p))
			return;
		removeTagPlayer(p);
		
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.updateInventory();
		
		p.sendMessage(ChatColor.GOLD + "[PvPTag] " + ChatColor.RED + "Your inventory was cleared because you logged out while in combat!");
		
	}
	
	public void resetOnDeath(Player p)
	{
		
		if(!tagContains(p))
			return;
		removeTagPlayer(p);
		
		p.sendMessage(ChatColor.GOLD + "[PvPTag] " + ChatColor.GREEN + "You are no longer in combat and can logout safely!");
		
	}
	
}