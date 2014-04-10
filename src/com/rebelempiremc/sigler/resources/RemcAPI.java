package com.rebelempiremc.sigler.resources;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.v1_7_R1.EntityPlayer;
import net.minecraft.server.v1_7_R1.EnumClientCommand;
import net.minecraft.server.v1_7_R1.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;

import com.rebelempiremc.sigler.RPG;


@SuppressWarnings("unused")
public class RemcAPI
{
	public static RPG plugin;//The variable to refer to the Main
	public RemcAPI(RPG plugin)
	{//The constructor
		RemcAPI.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	public static void invUpdate(Player player){
		ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemStack lava = new ItemStack(Material.LAVA, 1);
		ItemMeta lavameta = lava.getItemMeta();
		lavameta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + ChatColor.ITALIC + "Item Remover!");
		lava.setItemMeta(lavameta);
		ItemMeta blackmeta = black.getItemMeta();
		blackmeta.setDisplayName("" + ChatColor.YELLOW + ChatColor.ITALIC + "Slot not available!");
		black.setItemMeta(blackmeta);
		player.getInventory().remove(Material.STAINED_GLASS_PANE);
		player.getInventory().remove(Material.LAVA);
		player.getInventory().setItem(4, black);
		player.getInventory().setItem(5, black);
		player.getInventory().setItem(6, black);
		player.getInventory().setItem(7, black);
		player.getInventory().setItem(8, black);
		player.getInventory().setItem(12, black);
		player.getInventory().setItem(13, black);
		player.getInventory().setItem(21, black);
		player.getInventory().setItem(22, black);
		player.getInventory().setItem(30, black);
		player.getInventory().setItem(31, black);
		player.getInventory().setItem(26, black);
		player.getInventory().setItem(35, black);
		player.getInventory().setItem(17, lava);
		player.updateInventory();
	}
	
    public static boolean isSword(Material m){
            Material mat = m;
           
            switch(m){
            case WOOD_SWORD:
            case STONE_SWORD:
            case IRON_SWORD:
            case GOLD_SWORD:
            case DIAMOND_SWORD:
                    return true;
                   
            default:
                    return false;
            }
        }
    public static boolean isPick(Material m){
        Material mat = m;
       
        switch(m){
        case WOOD_PICKAXE:
        case STONE_PICKAXE:
        case IRON_PICKAXE:
        case GOLD_PICKAXE:
        case DIAMOND_PICKAXE:
                return true;
               
        default:
                return false;
        }
    }
	public static Boolean isPlayerOnline(Player player)
	{
		if (player.isOnline())
		{
			return true;
		}else
		{
			return false;
		}
	}
	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static ItemStack coloredArmor(Material material, Color color, String name)
	{
		ItemStack leatherArmor = new ItemStack(material);
		LeatherArmorMeta meta = (LeatherArmorMeta) leatherArmor.getItemMeta();
		meta.setColor(color);
		if ( name != null )
		{
			meta.setDisplayName(name);
		}
		leatherArmor.setItemMeta(meta);
		return leatherArmor;
	}

	public static String rainbow(String string)
	{
		int lastColor = 0;
		int currColor = 0;
		String newMessage = "";
		String colors = "123456789abcde";
		for ( int i = 0; i < string.length(); i++ )
		{
			do
			{
				currColor = new Random().nextInt(colors.length() - 1) + 1;
			}
			while (currColor == lastColor);

			newMessage += ChatColor.RESET.toString() + ChatColor.getByChar(colors.charAt(currColor)) + "" + string.charAt(i);

		}
		return newMessage;
	}
	public static void resetInv(Player player, Boolean normal, Double health)
	{
		if (normal != false)
		{
			player.getInventory().setHelmet(null);
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
			player.getInventory().clear();
		}else
		{
			player.getInventory().setHelmet(null);
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
			player.getInventory().clear();
			player.setExp(0);
			player.setLevel(0);
			player.setMaxHealth(health);
			player.setHealth(player.getMaxHealth());
		}

	}

	public static void autoRespawn(Player player)
	{
		final EntityPlayer ep = ((CraftPlayer) player).getHandle();

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				PacketPlayInClientCommand in = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN); // Gets the packet class
				ep.playerConnection.a(in);
			}
		}, 5L);
	}

	public static ItemStack createItem(Material material, String displayname, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);


		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, int amount, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);


		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, String displayname) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		item.setItemMeta(meta);
		return item;
	}
	
	public void chatClear()
	{
		int i;
		for (i = 0; i<= 150; i++)
		{
			Bukkit.broadcastMessage("");
		}
	}
	
	public static Location addDirectionone(Player player)
	{
		Location location = player.getEyeLocation();
        location.add(location.getDirection().normalize());
		return location;
		
	}
	
	public boolean didPlayerJump(Location last, Location cur){
		double diff = cur.getY() - last.getY();
		if(diff < .41){
			//either didn't go up or went up something small
			return false;
		}
		if(diff > .43){
			//went up something big (ie stairs, cake, some snow
			return false;
		}
		//If not, they probably jumped
		return true;
	}

}

