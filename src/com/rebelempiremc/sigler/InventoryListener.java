package com.rebelempiremc.sigler;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.rebelempiremc.sigler.resources.RemcAPI;

@SuppressWarnings("unused")
public class InventoryListener implements Listener
{
	public RPG plugin;//The variable to refer to the Main
	public InventoryListener(RPG plugin){//The constructor
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(final InventoryClickEvent event)
	{
		HumanEntity p = event.getWhoClicked();
		if (p instanceof Player)
		{
			final Player player = (Player) p;
			ItemStack cursor = event.getCursor();
			if(event.getCurrentItem().getType() == Material.STAINED_GLASS_PANE)
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
				{
					@Override
					public void run()
					{player.updateInventory();player.closeInventory();player.getInventory().addItem(event.getCursor());}
				}, 1L);
				event.setCancelled(true);
			}
			if (event.getCurrentItem().getType() == Material.LAVA) 
			{
				if (event.getCursor() !=null){
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
				{
					public void run()
					{player.updateInventory();}
				}, 1L);
				event.setCursor(null);
				event.setCancelled(true);
				}else{
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
					{
						@Override
						public void run()
						{player.updateInventory();player.closeInventory();player.getInventory().addItem(event.getCursor());}
					}, 1L);
					event.setCancelled(true);
				}
			}
				
			
			/*if (event.getSlot() == 17 && event.getAction().equals(InventoryAction.SWAP_WITH_CURSOR))
			{
				ItemStack lava = new ItemStack(Material.LAVA, 1);
				ItemMeta lavameta = lava.getItemMeta();
				lavameta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + ChatColor.ITALIC + "Item Remover!");
				lava.setItemMeta(lavameta);
				event.setCursor(null);
				player.getInventory().setItem(17, lava);
				player.updateInventory();
			}*/
			if (event.isShiftClick())
			{
				event.setCancelled(true);
				player.sendMessage("You are not allowed to shift click items!");

			}
			//player.updateInventory(); Possible cause to glitch
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		RemcAPI.invUpdate(event.getPlayer());
	}
/*	@EventHandler
	public void onOpen(InventoryOpenEvent event){
		if (event.getPlayer() instanceof Player){
			RemcAPI.invUpdate((Player) event.getPlayer());
		}
	}
	@EventHandler
	public void onClose(InventoryCloseEvent event){
		if (event.getPlayer() instanceof Player){
			RemcAPI.invUpdate((Player) event.getPlayer());
		}
	}*/
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlace(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		Block block = event.getBlockPlaced();
		if (block.getType() == Material.STAINED_GLASS_PANE && block.getData() == (short)15)
		{
			event.setCancelled(true);
			player.updateInventory();
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void invLimiter(PlayerPickupItemEvent e) 
	{
		Player player = e.getPlayer();
		PlayerInventory inv = e.getPlayer().getInventory();
		ItemStack[] contents = inv.getContents();
		e.setCancelled(true);
		Item item = e.getItem();
		ItemStack newItem = item.getItemStack();
		invLoop:
			for (int i = 0; i < contents.length; i++) {
				switch (i) {
				case 14:
				case 15:
				case 16:
				case 23:
				case 24:
				case 25:
				case 32:
				case 33:
				case 34:
					if (contents[i] == null) 
					{
						contents[i] = newItem;
						item.remove();
						break invLoop;
					} else if (contents[i].isSimilar(newItem)) {
						int maxStack = newItem.getType().getMaxStackSize();
						int combinedStackSize = contents[i].getAmount() + newItem.getAmount();
						int remainingStackSize = combinedStackSize - maxStack;
						if (remainingStackSize > 0) {
							contents[i].setAmount(maxStack);
							newItem.setAmount(remainingStackSize);
							item.setItemStack(new ItemStack(newItem));
						} else {
							item.remove();
							break invLoop;
						}
					}
					continue;
				default:
					continue;
				}
			}
		inv.setContents(contents);
		player.updateInventory();
	}
}
