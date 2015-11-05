package net.huntersharpe.Reincarnation.mobs;

import net.huntersharpe.Reincarnation.Reincarnation;
import org.spongepowered.api.Game;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackBuilder;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by intronate67 on 11/1/15.
 */
public class EvolutionHandler {

    Game game = Reincarnation.getInstance().getGame();

    public static EvolutionHandler instance = new EvolutionHandler();

    public static EvolutionHandler getInstance(){
        return instance;
    }

    public Map<String, Integer> evolutionLocation = new HashMap<>();

    public List<String> inReincarnation = new ArrayList<>();

    @Listener
    public void onPlayerDeath(DestructEntityEvent e){
        if(!e.getTargetEntity().getType().equals(EntityTypes.PLAYER)) return;
        Player p = (Player) e.getTargetEntity();
        //TODO: Handle /slay or /kill deaths
        if(!inReincarnation.contains(p.getName())) return;
        inReincarnation.add(p.getName());

        giveReincarnationBook(p);
    }


    public void giveReincarnationBook(Player p){
        ItemStack item = Reincarnation.getItemBuilder().itemType(ItemTypes.WRITTEN_BOOK).quantity(1).build();
        item.offer(Keys.DISPLAY_NAME, Texts.of("Reincarnation"));
        item.offer(Keys.BOOK_AUTHOR, Texts.of("Intronate67"));
        List<Text> pages = new ArrayList<>();
        pages.add(0, Texts.of("You have§ldied!§l\n" +
                "\n" +
                "But don't completely worry. You can complete a set of \"tasks\" to get you life back. \n" +
                "\n" +
                "It might take awhile but it's what you get for dying."));
        pages.add(1, Texts.of("Rabbit\n" +
                "    \n" +
                "    §4Eat five carrots\n" +
                "\n" +
                "    §40§0/§25"));
        item.offer(Keys.BOOK_PAGES, pages);
        p.setItemInHand(item);
    }

}
