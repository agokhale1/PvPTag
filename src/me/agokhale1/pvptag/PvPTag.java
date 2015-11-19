package me.agokhale1.pvptag;

import org.bukkit.plugin.java.*;

import me.agokhale1.pvptag.listener.*;
import me.agokhale1.pvptag.util.*;
import me.agokhale1.pvptag.util.UpdaterUtil.UpdateType;

public class PvPTag extends JavaPlugin {
	
	@Override
	public void onEnable()
	{
		
		UpdaterUtil updater = new UpdaterUtil(this, 52564, this.getFile(), UpdateType.DEFAULT, true);
		
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