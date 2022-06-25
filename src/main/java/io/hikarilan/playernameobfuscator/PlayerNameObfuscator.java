package io.hikarilan.playernameobfuscator;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.GameProfileRequestEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Plugin(
        id = "playernameobfuscator",
        name = "PlayerNameObfuscator",
        version = BuildConstants.VERSION,
        description = "Obfuscator the player name",
        authors = {"HikariLan"}
)
public class PlayerNameObfuscator {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }

    @Subscribe
    public void onRequestGameProfile(GameProfileRequestEvent e) {
        String name = e.getUsername();
        String encode = Arrays.hashCode(name.getBytes(StandardCharsets.UTF_8)) + "";
        e.setGameProfile(e.getGameProfile().withName(encode.substring(0, Math.min(encode.length() - 1, 15))));
        logger.info("Player name obfuscated: " + e.getGameProfile().getName() + "(" + name + ")");
    }
}
