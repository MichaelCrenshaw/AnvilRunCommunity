package stupid.anvilruncommunity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnvilRunCommunity extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        CommandAnvil commandAnvil = new CommandAnvil(this);
        this.getCommand("anvil").setExecutor(commandAnvil);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
