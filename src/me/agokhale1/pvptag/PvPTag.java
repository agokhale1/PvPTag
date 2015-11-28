package me.agokhale1.pvptag;

import org.bukkit.plugin.java.*;

import me.agokhale1.pvptag.listener.*;
import me.agokhale1.pvptag.manager.*;

public class PvPTag extends JavaPlugin {
	
	DataManager dm;
	
	@Override
	public void onEnable()
	{
		
		dm = new DataManager();
		
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new PlayerJoinLeave(dm), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(dm), this);
		
	}
	
	@Override
	public void onDisable()
	{
		
		FileManager.getTaggedPlayersFile().saveList(dm.getTaggedPlayers());
		FileManager.getPvPZombiesFile().saveList(dm.getPvPZombies());
		
	}
	
}