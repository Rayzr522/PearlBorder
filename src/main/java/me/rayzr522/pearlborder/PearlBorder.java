package me.rayzr522.pearlborder;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Rayzr
 */
public class PearlBorder extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onTeleport(PlayerTeleportEvent e) {
        if (e.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            return;
        }

        World world = e.getTo().getWorld();

        if (world.getWorldBorder() == null) {
            return;
        }

        WorldBorder worldBorder = world.getWorldBorder();

        double offsetX = e.getTo().getX() - worldBorder.getCenter().getX();
        double offsetZ = e.getTo().getZ() - worldBorder.getCenter().getZ();
        double size = worldBorder.getSize() / 2.0;

        if (offsetX > size || offsetX < -size || offsetZ > size || offsetZ < -size) {
            e.setCancelled(true);
        }
    }
}
