package me.agokhale1.pvptag;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import me.agokhale1.pvptag.manager.*;

public class PvPZombie {
	
	Zombie z;
	
	public void init(Player p)
	{
		
		z = p.getLocation().getWorld().spawn(p.getLocation(), Zombie.class);
		
		z.setCustomName(ChatColor.DARK_RED.toString() + p.getName());
		z.setCustomNameVisible(true);
		
		z.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1, 1);
		z.setCanPickupItems(false);
		z.setRemoveWhenFarAway(false);
		
		setupInv(p);
		saveZombie(p);
		
	}
	
	public void setupInv(Player p)
	{
		
		EntityEquipment ee = z.getEquipment();
		
		ee.setArmorContents(p.getInventory().getArmorContents());
		ee.setItemInHand(p.getItemInHand());
		
		ee.setHelmetDropChance(1F);
		ee.setChestplateDropChance(1F);
		ee.setLeggingsDropChance(1F);
		ee.setBootsDropChance(1F);
		ee.setItemInHandDropChance(1F);
		
	}
	
	public void saveZombie(Player p)
	{
		
		FileManager.getPvPZombiesFile().saveInventory(p.getUniqueId().toString() + ".armor", p.getInventory().getArmorContents());
		FileManager.getPvPZombiesFile().saveInventory(p.getUniqueId().toString() + ".inventory", p.getInventory().getContents());
		
	}
	
	public Zombie getPvPZombie()
	{
		return z;
	}
	
}