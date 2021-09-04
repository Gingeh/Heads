package io.github.gingeh.heads;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class OnDeath implements Listener {
    @EventHandler
    public void onEntityDeath(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Block death_location = e.getEntity().getLocation().getBlock();
            death_location.setType(Material.PLAYER_HEAD);
            Skull skull = (Skull) death_location.getState();
            skull.setOwningPlayer(e.getEntity());
            skull.update();
            LocalDate next_week = LocalDate.now().plusWeeks(1);
            next_week = next_week.minusDays(next_week.getDayOfWeek().getValue()-1);
            System.out.println(next_week);
            Bukkit.getBanList(BanList.Type.NAME).addBan(
                    e.getEntity().getPlayer().getName(),
                    "You are dead!",
                    Date.from(next_week.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                    null
            );
            e.getEntity().getPlayer().kickPlayer("You died, don't tell anyone!\nYou will be unbanned next week.");
        }
    }

    @EventHandler
    public void onLeave(PlayerKickEvent e) {
        e.setLeaveMessage("");
    }
}
