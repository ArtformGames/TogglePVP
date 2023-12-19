package com.artformgames.plugin.togglepvp.handler;

import com.artformgames.core.ArtCore;
import com.artformgames.core.function.settings.SettingsType;
import com.artformgames.core.function.settings.UserSettingsData;
import com.artformgames.core.function.settings.values.BooleanSettingsType;
import com.artformgames.plugin.togglepvp.conf.PluginConfig;
import com.artformgames.plugin.togglepvp.conf.PluginMessages;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPHandler implements Listener {

    public static final SettingsType<Boolean> PVP = new BooleanSettingsType(10, PluginConfig.DEFAULTS.getNotNull());


    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        Player attacker = null;

        if (e.getDamager() instanceof Player player) {
            attacker = player;
        } else if (e.getDamager() instanceof Projectile proj && proj.getShooter() instanceof Player shooter) {
            attacker = shooter;
        }

        if (attacker == null) return;
        if (!(e.getEntity() instanceof Player player)) return;

        if (!isPVPEnabled(attacker)) {
            // 当玩家想要攻击别人时，先判断自己是否允许攻击
            PluginMessages.SELF_DISABLED.send(attacker);
            e.setCancelled(true);
        } else if (!isPVPEnabled(player)) {
            //再判断被攻击者是否允许被攻击
            PluginMessages.TARGET_DISABLED.send(attacker, player.getName());
            e.setCancelled(true);
        }
    }

    public static boolean isPVPEnabled(Player player) {
        return ArtCore.getUserManager().get(player).getHandler(UserSettingsData.class).get(PVP);
    }

    public static void setPVPStatus(Player player, boolean status) {
        ArtCore.getUserManager().get(player).getHandler(UserSettingsData.class).set(PVP, status);
    }


}
