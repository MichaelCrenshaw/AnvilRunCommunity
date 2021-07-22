package stupid.anvilruncommunity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.util.concurrent.TimeUnit;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class AnvilManager implements Runnable {

    public static int dropInterval = 20;
//    public boolean anvilsOn = false;

    @Override
    public void run () {
        try {
            for (Player player : Bukkit.getOnlinePlayers()) {
                dropAnvil(player);
            }
        } catch (NullPointerException e) {
//            Bukkit.broadcastMessage("Null pointer in AnvilManager Run: online players.");
        }
}

    public void dropAnvil(Player player) {
        Material standingBlock = player.getLocation().getBlock().getRelative(0 , 20 , 0).getType();
        if ((standingBlock != Material.END_PORTAL) && (standingBlock != Material.END_PORTAL_FRAME) && (standingBlock != Material.OBSIDIAN) && (standingBlock != Material.NETHER_PORTAL) && (standingBlock != Material.BEDROCK)) {
            player.getLocation().getBlock().getRelative(0 , 20 ,0).setBlockData(Bukkit.createBlockData(Material.ANVIL));
        } else {
            return;
        }
    }

    public void anvilNuke (Player player) {
        Location locA = player.getLocation().add(20, 20, 20);
        Location locB = player.getLocation().add(-20, 20, -20);
        String blockA = Integer.toString(locA.getBlockX()) + " " + Integer.toString(locA.getBlockY()) + " " + Integer.toString(locA.getBlockZ()) + " ";
        String blockB = Integer.toString(locB.getBlockX()) + " " + Integer.toString(locB.getBlockY()) + " " + Integer.toString(locB.getBlockZ()) + " ";
        String command = "fill " + blockA + blockB + "minecraft:anvil";
        Bukkit.broadcastMessage(command);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }

    public int getDropInterval() {
        return dropInterval;
    }

    public void setDropInterval(int dropInterval) {
        this.dropInterval = dropInterval;
    }

//    public boolean isAnvilsOn() {
//        return anvilsOn;
//    }
//
//    public void setAnvilsOn(boolean anvilsOn) {
//        this.anvilsOn = anvilsOn;
//    }
}
