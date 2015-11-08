package net.huntersharpe.Reincarnation.mobs;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.UseItemStackEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;

import java.util.HashMap;
import java.util.List;
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
        ItemStack item = inv.query(ItemTypes.WRITTEN_BOOK).peek();
        List<Text> pages = item.get(Keys.BOOK_PAGES).get();
        Text pageTwo = pages.get(1);
        Text value = pageTwo.getChildren().get(4);
        int currentAmount = Integer.parseInt(value.toString());
        if(currentAmount == 5){
            EvolutionHandler.getInstance().evolutionLocation.put(p.getName(), 2);
        }else{
            value.builder().insert(0, Texts.of(String.valueOf(currentAmount + 1)));
        }
    }

}
