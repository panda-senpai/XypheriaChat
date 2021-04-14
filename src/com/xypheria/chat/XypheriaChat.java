package com.xypheria.chat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;



public class XypheriaChat extends JavaPlugin {
	private static LuckPerms luckPerms;
	public static String ServerName;

	public static User getLuckPermsUser(Player player) {
		return luckPerms.getPlayerAdapter(Player.class).getUser(player);
	}

	public static CachedMetaData getLuckPermsMetaData (Player player) {
		return luckPerms.getPlayerAdapter(Player.class).getMetaData(player);
	}

	@Override
	public void onEnable() {
		new ModifyChat(this);
		luckPerms = getServer().getServicesManager().load(LuckPerms.class);

		FileConfiguration config = this.getConfig();
		config.addDefault("ServerDisplayName","[Default]");
		ServerName = config.getString("ServerDisplayName");
		config.options().copyDefaults(true);
		saveConfig();
	}

	@Override
	public void onDisable() {

	}
}



