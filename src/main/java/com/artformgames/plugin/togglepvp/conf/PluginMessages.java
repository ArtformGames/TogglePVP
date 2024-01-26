package com.artformgames.plugin.togglepvp.conf;

import cc.carm.lib.mineconfiguration.bukkit.value.ConfiguredMessageList;
import com.artformgames.core.conf.Messages;
import net.md_5.bungee.api.chat.BaseComponent;

public interface PluginMessages extends Messages {

    public static final ConfiguredMessageList<BaseComponent[]> SELF_DISABLED = Messages.list()
            .defaults("&7 You have disabled PVP so you can't attack other players.")
            .build();

    public static final ConfiguredMessageList<BaseComponent[]> TARGET_DISABLED = Messages.list()
            .defaults("&fPlayer &c%(player) &fPVP is disabled, so it can't be attacked.")
            .params("player")
            .build();

    public static final ConfiguredMessageList<BaseComponent[]> ENABLED = Messages.list()
            .defaults("&fYou have &a&lenabled &fPVP, now you can fight with other players.")
            .build();

    public static final ConfiguredMessageList<BaseComponent[]> DISABLED = Messages.list()
            .defaults("&fYou have &c&ldisabled &fPVP, now you can't attack other players, and you can't be attacked by other players.")
            .build();

}

