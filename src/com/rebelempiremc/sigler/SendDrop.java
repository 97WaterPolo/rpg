package com.rebelempiremc.sigler;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class SendDrop implements CommandExecutor, Listener
{
	public RPG plugin;//The variable to refer to the Main
	public SendDrop(RPG plugin){//The constructor
		this.plugin = plugin;
	}
	ArrayList<String> drop = new ArrayList<String>();
	ArrayList<String> cooldown = new ArrayList<String>();
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		final Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("sendcooldown") && sender.hasPermission("AntiDrop.bypass"))
		{
			if (cooldown.contains(player.getName()))
			{
				cooldown.remove(player.getName());
				player.sendMessage("Removed from cooldown list!");
			}else
			{
				player.sendMessage("You are not on the cooldown list!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("drop"))
		{
			if (sender.hasPermission("AntiDrop.bypass"))
			{
				if (!(drop.contains(player.getName())))
				{
					drop.add(player.getName());
					player.sendMessage(ChatColor.YELLOW + "Added to drop bypass.");
				}else
				{
					drop.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "Removed from drop bypass!");
				}
			}else
			{
				player.sendMessage(ChatColor.AQUA + "You do not have permission!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("st") || cmd.getName().equalsIgnoreCase("sendto") || cmd.getName().equalsIgnoreCase("send"))
		{
			if (args.length == 0)
			{
				player.sendMessage(ChatColor.GOLD + "Please use " + ChatColor.RED + "/sendto [player] " + ChatColor.GOLD + "to send the item in your hand to fellow players!");
			}else
			{
				if (!(cooldown.contains(player.getName())))
				{
					Player target = Bukkit.getPlayerExact(args[0]);
					ItemStack hand = player.getInventory().getItemInHand();
					if (target != null)
					{
						if(hand.getAmount() > 0)
						{
							cooldown.add(player.getName());
							player.getInventory().remove(hand);
							player.sendMessage(ChatColor.LIGHT_PURPLE + "You have sent " + target.getName() + " the item you were holding!");
							target.sendMessage(ChatColor.DARK_PURPLE + "" + player.getName() + " has sent you an item!");
							if (!(target.getInventory().firstEmpty() == -1))
							{
								target.getInventory().addItem(hand);
								target.updateInventory();
							}
							else
							{
								target.getWorld().dropItemNaturally(target.getLocation(), new ItemStack(hand));
								target.sendMessage(ChatColor.RED + "Your inventory was full so the item sent was dropped!");
								target.updateInventory();
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
							{

								@Override
								public void run()
								{
									cooldown.remove(player.getName());
								}
							}, 6000L);
						}else
						{
							player.sendMessage(ChatColor.GREEN + "Please have an item in your hand!");
						}
					}else
					{
						player.sendMessage(ChatColor.RED + "That player is currently offline!");
					}
				}else
				{
					player.sendMessage(ChatColor.RED + "There is a cooldown of 5 minutes for sending items!");
				}
			}
		}
		return false;

	}
/*	@EventHandler
	public void ItemDrop(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		if (!(drop.contains(player.getName())))
		{
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + "Please use " + ChatColor.YELLOW + "/send [player] " + ChatColor.RED + "to send an item to a friend, or use the lava in your inventory!" );
			//player.sendMessage(red + message);
		}
	}*/
}
