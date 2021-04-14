package com.xypheria.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;

public class ModifyChat implements Listener {

	public ModifyChat(XypheriaChat plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}      

	public static boolean isPlayerInGroup(Player player, String group) {
		return player.hasPermission("group." + group);
	}

	private String getPlayerPrefix(Player player) {
		User user = XypheriaChat.getLuckPermsUser(player);
		return user.getCachedData().getMetaData().getPrefix();  
	}

	private String getPlayerNickname(Player player) {
		CachedMetaData metaData = XypheriaChat.getLuckPermsMetaData(player);
		String nickName = metaData.getMetaValue("nickname");
		if (nickName == null) {
			nickName = player.getName();
		}
		return nickName;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		event.setFormat(ChatColor.translateAlternateColorCodes('&', XypheriaChat.ServerName + "&f " + getPlayerPrefix(player) + "&f " + getPlayerNickname(player) + "&f: " + message)); 
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', XypheriaChat.ServerName + "&f " + getPlayerPrefix(player) + "&f " + getPlayerNickname(player) + "&6 logged in!"));
	}

	@EventHandler
	public void onJoin(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', XypheriaChat.ServerName + "&f " + getPlayerPrefix(player) + "&f " + getPlayerNickname(player) + "&6 logged out."));
	}
}