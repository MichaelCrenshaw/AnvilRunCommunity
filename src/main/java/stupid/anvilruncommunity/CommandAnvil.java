package stupid.anvilruncommunity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandAnvil implements CommandExecutor {

    private Plugin plugin;
    private AnvilManager anvilManager = new AnvilManager();

    protected CommandAnvil(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Bukkit.getScheduler().cancelTasks(plugin);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, anvilManager, 20, anvilManager.getDropInterval());
            return true;
        }
        if (sender.isOp()) {
                if (args[0].equals("interval")) {
                    if (args.length > 1) {
                        try {
                            int interval = Integer.parseInt(args[1]);
                            anvilManager.setDropInterval(interval);
                            Bukkit.getScheduler().cancelTasks(plugin);
                            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, anvilManager, 20, anvilManager.getDropInterval());
                            return true;
                        } catch (NumberFormatException e) {
                            sender.sendMessage("Interval arg not a number: " + args[1]);
                            return false;
                        }
                    }
                } else if (args[0].equals("start")) {
                    sender.sendMessage("start is called");
                    Bukkit.getScheduler().cancelTasks(plugin);
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, anvilManager, 20, anvilManager.getDropInterval());
                    return true;
                } else if (args[0].equals("stop")) {
                    Bukkit.getScheduler().cancelTasks(plugin);
                    return true;
                }else if (args[0].equals("nuke")) {
                    if (args.length > 1) {
                            String playerName = (args[1]);
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (playerName.equals(player.getName())) {
                                    anvilManager.anvilNuke(player);
                                    return true;
                                }
                                sender.sendMessage("Player \"" + playerName + "\" not found");
                                return false;
                            }
                            return true;
                    } else {
                        return false;
                    }
                } else {
                    sender.sendMessage("Unknown argument " + args[0]);
                    return false;
                }
        } else {
            sender.sendMessage("You do not have permission to enter this command");
            return false;
        }
        return false;
    }
}
