package me.agokhale1.pvptag.listener;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

import me.agokhale1.pvptag.manager.*;

public class EntityDamageByEntity implements Listener {
	
	DataManager dm;
	public EntityDamageByEntity(DataManager dm)
	{
		this.dm = dm;
	}
	
	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e)
	{
		
		if(e.getEntity() instanceof Player)
		{
			
			Player victim = (Player) e.getEntity(), attacker;
			
			if(e.getDamager() instanceof Player)
				attacker = (Player) e.getDamager();
			else if(e.getDamager() instanceof Arrow)
			{
				
				Arrow a = (Arrow) e.getDamager();
				
				if(!(a.getShooter() instanceof Player)) return;
				attacker = (Player) a.getShooter();
				
			}
			else
				return;
			
			if(!dm.tagContains(victim))
			{
				
				dm.addTagPlayer(victim);
				
				victim.sendMessage(ChatColor.GOLD + "[PvPTag] " + ChatColor.RED + "You have been tagged by " + ChatColor.DARK_RED.toString() + attacker.getName() + ChatColor.RED + "! Do not logout!");
				attacker.sendMessage(ChatColor.GOLD + "[PvPTag] " + ChatColor.RED + "You have tagged " + ChatColor.DARK_RED.toString() + victim.getName() + ChatColor.RED + "!");
				
			}
			
		}
		
	}
	
}