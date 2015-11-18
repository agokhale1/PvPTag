package me.agokhale1.pvptag.listener;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

import me.agokhale1.pvptag.util.*;

public class EntityDamageByEntity implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDamage(EntityDamageByEntityEvent e)
	{
		
		if(e.getEntity() instanceof Player)
		{
			
			Player hit = (Player) e.getEntity();
			Player attacker;
			
			if(e.getDamager() instanceof Arrow)
			{
				
				Arrow a = (Arrow) e.getDamager();
				if(!(a.getShooter() instanceof Player)) return;
				
				attacker = (Player) a.getShooter();
				
			}
			else if(e.getDamager() instanceof Player)
			{
				
				attacker = (Player) e.getDamager();
				
			}
			else
			{
				
				e.setCancelled(true);
				return;
				
			}
			
			if(!TagDataUtil.contains(hit))
			{
				
				TagDataUtil.add(hit);
				
				hit.sendMessage(ChatColor.BLUE + "[PvPTag] " + ChatColor.RED + "You have been tagged by " + ChatColor.DARK_RED.toString() + attacker.getName() + ChatColor.RED.toString() + "! Do not logout!");
				attacker.sendMessage(ChatColor.BLUE + "[PvPTag] " + ChatColor.RED + "You have tagged " + ChatColor.DARK_RED.toString() + hit.getName());
				
			}
			
		}
		
	}
	
}