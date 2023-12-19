package com.artformgames.plugin.togglepvp;

import cc.carm.lib.easyplugin.EasyPlugin;
import cc.carm.lib.mineconfiguration.bukkit.MineConfiguration;
import com.artformgames.core.utils.GHUpdateChecker;
import com.artformgames.plugin.togglepvp.command.TogglePVPCommand;
import com.artformgames.plugin.togglepvp.conf.PluginConfig;
import com.artformgames.plugin.togglepvp.conf.PluginMessages;
import com.artformgames.plugin.togglepvp.handler.PVPHandler;
import dev.rollczi.litecommands.bukkit.LiteCommandsBukkit;

public class Main extends EasyPlugin {

    private static Main instance;

    public Main() {
        Main.instance = this;
    }

    protected MineConfiguration configuration;
    protected LiteCommandsBukkit commands;

    @Override
    protected boolean initialize() {

        log("Loading plugin configurations...");
        configuration = new MineConfiguration(this);
        configuration.initializeConfig(PluginConfig.class);
        configuration.initializeMessage(PluginMessages.class);

        log("Register listeners...");
        registerListener(new PVPHandler());

        log("Register commands...");
        registerCommand(getName(), new TogglePVPCommand());


        if (PluginConfig.CHECK_UPDATE.getNotNull()) {
            log("Start to check the plugin versions...");
            getScheduler().runAsync(GHUpdateChecker.runner(this));
        } else {
            log("Version checker is disabled, skipped.");
        }

        return true;
    }

    @Override
    public boolean isDebugging() {
        return PluginConfig.DEBUG.getNotNull();
    }

    public static void info(String... messages) {
        getInstance().log(messages);
    }

    public static void severe(String... messages) {
        getInstance().error(messages);
    }

    public static void debugging(String... messages) {
        getInstance().debug(messages);
    }

    public static Main getInstance() {
        return instance;
    }

}
