package me.agokhale1.pvptag;

import org.bukkit.plugin.java.*;

import me.agokhale1.pvptag.listener.*;
import me.agokhale1.pvptag.util.*;

public class PvPTag extends JavaPlugin {
	
	@Override
	public void onEnable()
	{
		
		new TagDataUtil();
		
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
		
	}
	
	@Override
	public void onDisable()
	{
		
		TagDataUtil.saveFile();		
		TagDataUtil.reset();
		
	}
	
}