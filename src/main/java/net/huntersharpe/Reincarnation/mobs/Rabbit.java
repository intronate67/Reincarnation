package net.huntersharpe.Reincarnation.mobs;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.UseItemStackEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.Slot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by intronate67 on 11/1/15.
 */
public class Rabbit {

    public static Rabbit instance = new Rabbit();

    public static Rabbit getInstance(){
        return instance;
    }

    public Map<String, Integer> eatenCarrots = new HashMap<>();

    @Listener
    public void onPlayerEat(UseItemStackEvent e){
        if(!e.getCause().first(Entity.class).get().getType().equals(EntityTypes.PLAYER)) return;
        if(!EvolutionHandler.getInstance().inReincarnation.contains(e.getCause().first(Player.class).get().getName())) return;
        if(!e.getItemStackInUse().getFinal().getType().equals(ItemTypes.CARROT)) return;
        if(EvolutionHandler.getInstance().evolutionLocation.get(e.getCause().first(Player.class).get().getName()) != 1) return;
        Player p = e.getCause().first(Player.class).get();
        Inventory inv = p.getInventory();
        if(!(inv instanceof Slot)) return;

    }

}
