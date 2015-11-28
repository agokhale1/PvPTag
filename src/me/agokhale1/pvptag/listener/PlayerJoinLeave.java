package me.agokhale1.pvptag.listener;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

import me.agokhale1.pvptag.*;
import me.agokhale1.pvptag.manager.*;

public class PlayerJoinLeave implements Listener {
	
	DataManager dm;
	public PlayerJoinLeave(DataManager dm)
	{
		this.dm = dm;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		
		dm.resetOnJoin(e.getPlayer());
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		
		if(dm.tagContains(e.getPlayer()))
		{
			
			PvPZombie z = new PvPZombie();
			z.init(e.getPlayer());
			
			dm.addPvPZombie(e.getPlayer());
			
		}
		
	}
	
}