package net.huntersharpe.Reincarnation;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.item.inventory.ItemStackBuilder;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.ConfigDir;
import org.spongepowered.api.service.config.DefaultConfig;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by intronate67 on 10/26/15.
 */
@Plugin(id="reincarnation", name="net.huntersharpe.Reincarnation.Reincarnation", version="1.0")
public class Reincarnation {

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;

    @Inject
    @DefaultConfig(sharedRoot = false)
    public File defaultConf;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> configManager;

    @Inject
    private Game game;

    public ConfigurationNode config = null;

    private static Reincarnation instance = new Reincarnation();

    public static Reincarnation getInstance(){
        return instance;
    }

    public static ItemStackBuilder builder;

    public static ItemStackBuilder getItemBuilder(){
        return builder;
    }

    @Subscribe
    public void onPreInit(GamePreInitializationEvent e){
        game = e.getGame();
        builder = game.getRegistry().createItemBuilder();
        setupConfig();
    }

    @Subscribe
    public void onInit(GameInitializationEvent e){
        //TODO: Load commands
    }

    @Subscribe
    public void onServerStart(GameStartingServerEvent e){

    }

    public Game getGame(){
        return game;
    }

    private void setupConfig() {
        try {
            if (!defaultConf.getParentFile().exists()) {
                defaultConf.getParentFile().mkdir();
            }
            if (!defaultConf.exists()) {
                defaultConf.createNewFile();
                config = configManager.load();

                //TODO: Add config values
                configManager.save(config);
            }
            config = configManager.load();
        } catch (IOException e) {
            logger.warning("Default Config could not be loaded/created!");
        }
    }

    public ConfigurationNode getConfigNode(){
        return config;
    }

}
