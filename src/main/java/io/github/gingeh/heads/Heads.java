package io.github.gingeh.heads;

import org.bukkit.plugin.java.JavaPlugin;

public final class Heads extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OnDeath(), this);
    }
}
