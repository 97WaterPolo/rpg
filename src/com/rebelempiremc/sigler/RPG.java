package com.rebelempiremc.sigler;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.rebelempiremc.sigler.*;

@SuppressWarnings("unused")
public class RPG extends JavaPlugin //implements Listener
{
	public void onEnable()
	{
		getServer().getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		getServer().getLogger().info(getDescription().getName() + " Version: " + getDescription().getVersion() + " By: 97WaterPolo " + " has been enabled!");
		getServer().getLogger().info("Please suggest ideas by sending a PM at: " + getDescription().getWebsite());
		getServer().getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		//getCommand("sendto").setExecutor(new SendDrop(this));
		//getCommand("st").setExecutor(new SendDrop(this));
		//getCommand("send").setExecutor(new SendDrop(this));
		getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		getServer().getPluginManager().registerEvents(new DamageListener(this), this);
		//getServer().getPluginManager().registerEvents(new SendDrop(this), this);
	}

	public void onDisable()
	{
		getServer().getLogger().info(getDescription().getName() + " Version: " + getDescription().getVersion() + " Has been disabled!");
	}

}