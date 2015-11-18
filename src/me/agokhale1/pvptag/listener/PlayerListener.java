package me.agokhale1.pvptag.listener;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

import me.agokhale1.pvptag.util.*;

public class PlayerListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		
		TagDataUtil.resetPlayerOnJoin(e.getPlayer());
		
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e)
	{
		
		Player p = e.getPlayer();
		
		if(TagDataUtil.contains(p))
		{
			
			ItemStack[] items = p.getInventory().getContents();
			ItemStack[] armor = p.getInventory().getArmorContents();
			int invSize = 0;
			
			for(ItemStack i : items)
				if(i != null)
					invSize++;
			
			for(ItemStack a : armor)
				if(a != null)
					invSize++;
			
			Location loc = p.getLocation();
			Block block = loc.getBlock();
			Chest chest = null;
			
			//TODO: Use a custom sign and open an inv gui that has all items in it
			
			if(invSize < 27)
			{
				
				block.setType(Material.CHEST);
				chest = (Chest) block.getState();
				
				for(ItemStack i : items)
					if(i != null)
						chest.getBlockInventory().addItem(i);
				
				for(ItemStack a : armor)
					if(a != null)
						chest.getBlockInventory().addItem(a);
				
			}
			else
			{
				
				block.setType(Material.CHEST);
				block.getRelative(BlockFace.NORTH).setType(Material.CHEST);
				chest = (Chest) block.getState();
				
				for(ItemStack i : items)
					if(i != null)
						chest.getInventory().addItem(i);
				
				for(ItemStack a : armor)
					if(a != null)
						chest.getInventory().addItem(a);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		
		TagDataUtil.resetPlayerOnDeath(e.getEntity());
		
	}
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent e)
	{
		
		if(TagDataUtil.contains(e.getPlayer()))
		{
			
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.BLUE + "[PvPTag] ");
			
		}
		
	}
	
}