package me.agokhale1.pvptag.listener;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;

import me.agokhale1.pvptag.manager.*;

public class EntityDeath implements Listener {
	
	DataManager dm;
	public EntityDeath(DataManager dm)
	{
		this.dm = dm;
	}
	
	@EventHandler
	public void onPvPZombieDeath(EntityDeathEvent e)
	{
		
		if(!(e.getEntity().getKiller() instanceof Player)) return;
		if(e.getEntity() instanceof Zombie)
		{
			
			Zombie z = (Zombie) e.getEntity();
			
			if(dm.pvpZContains(z))
			{
				
				ItemStack[] armor = FileManager.getPvPZombiesFile().getInventory("ZombieInventories." + z.getUniqueId().toString() + ".armor");
				ItemStack[] contents = FileManager.getPvPZombiesFile().getInventory("ZombieInventories." + z.getUniqueId().toString() + ".inventory");
				
				FileManager.getPvPZombiesFile().removeInventory(z.getUniqueId().toString(), null);
				dm.removePvPZombie(z);
				
				e.setDroppedExp(0);
				e.getDrops().clear();
				
				for(ItemStack i : armor)
					if(i != null)
						e.getDrops().add(i);
				for(ItemStack i : contents)
					if(i != null)
						e.getDrops().add(i);
				
			}
			
		}
		
	}
	
}