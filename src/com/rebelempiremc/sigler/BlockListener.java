package com.rebelempiremc.sigler;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import com.rebelempiremc.sigler.resources.ParticleEffect;

public class BlockListener implements Listener
{
	public RPG plugin;//The variable to refer to the Main
	public BlockListener(RPG plugin){//The constructor
		this.plugin = plugin;
	}   

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak (final BlockBreakEvent event){
		Player player = event.getPlayer();
		Material block = event.getBlock().getType();
		Collection<ItemStack> drops = event.getBlock().getDrops();
		if (event.getBlock().getType().equals(Material.LOG) && event.getBlock().getData() == (byte) 0){
			dropItem(player, drops, event, 45, Material.LOG);
		}else if (block.equals(Material.IRON_ORE)){
			dropItem(player, drops, event, 90, Material.IRON_ORE);
		}else if (block.equals(Material.COAL_ORE)){
			dropItem(player, drops, event, 60, Material.COAL_ORE);
		}else if (block.equals(Material.GOLD_ORE)){
			dropItem(player, drops, event, 120, Material.GOLD_ORE);
		}else if (block.equals(Material.LAPIS_ORE)){
			dropItem(player, drops, event, 150, Material.LAPIS_ORE);
		}else if (block.equals(Material.REDSTONE_ORE)){
			dropItem(player, drops, event, 180, Material.REDSTONE_ORE);
		}else if (block.equals(Material.DIAMOND_ORE)){
			dropItem(player, drops, event, 600, Material.DIAMOND_ORE);
		}else if (block.equals(Material.EMERALD_ORE)){
			dropItem(player, drops, event, 600, Material.EMERALD_ORE);
		}
		else if (!player.isOp()){
			event.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	public void dropItem(Player player, Collection<ItemStack> drops, final BlockBreakEvent event, int delay, final Material material){
		for (ItemStack finaldrop : drops)
		{
			if (!(player.getInventory().firstEmpty() == -1))
			{
				player.getInventory().addItem(finaldrop);
			}
			else
			{
				player.getWorld().dropItemNaturally(player.getLocation(), finaldrop);
				player.updateInventory();
			}
			player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 1.0F);
			event.getBlock().setType(Material.BEDROCK);
			player.updateInventory();
			ParticleEffect.ENCHANTMENT_TABLE.display(event.getBlock().getLocation(), 0, 0, 0, 2, 5);
			ParticleEffect.ENCHANTMENT_TABLE.display(event.getBlock().getLocation(), 0, .25F, 0, 2, 5);
			ParticleEffect.ENCHANTMENT_TABLE.display(event.getBlock().getLocation(), 0, -.25F, 0, 2, 5);
			ParticleEffect.ENCHANTMENT_TABLE.display(event.getBlock().getLocation(), 0, .5F, 0, 2, 5);
			ParticleEffect.ENCHANTMENT_TABLE.display(event.getBlock().getLocation(), 0, -.5F, 0, 2, 5);
			ParticleEffect.ENCHANTMENT_TABLE.display(event.getBlock().getLocation(), 0, .75F, 0, 2, 5);
			ParticleEffect.ENCHANTMENT_TABLE.display(event.getBlock().getLocation(), 0, -.75F, 0, 2, 5);
			event.setCancelled(true);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
			{
				@Override
				public void run()
				{
					event.getBlock().setType(material);
				}
			}, 20*delay);//60 seconds
		}
	}

}
