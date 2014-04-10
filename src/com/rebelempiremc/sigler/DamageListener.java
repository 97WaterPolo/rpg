package com.rebelempiremc.sigler;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener
{
	public RPG plugin;//The variable to refer to the Main
	public DamageListener(RPG plugin){//The constructor
		this.plugin = plugin;
	}   
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		Entity e = event.getEntity();
		e.getWorld().playEffect(e.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
		e.getWorld().playEffect(e.getLocation().add(0, 1, 0), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
	}

}
