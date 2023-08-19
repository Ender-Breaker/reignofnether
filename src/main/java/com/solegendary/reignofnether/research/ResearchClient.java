package com.solegendary.reignofnether.research;

import com.solegendary.reignofnether.fogofwar.FogOfWarClientEvents;
import com.solegendary.reignofnether.hud.HudClientEvents;
import com.solegendary.reignofnether.unit.UnitActionItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.solegendary.reignofnether.fogofwar.FogOfWarClientEvents.resetFogChunks;

// class to track status of research items for the client player - we generally don't care about other players' research
public class ResearchClient {

    private final static Minecraft MC = Minecraft.getInstance();

    final private static List<String> researchItems = Collections.synchronizedList(new ArrayList<>());

    public static void removeAllResearch() {
        synchronized (researchItems) {
            researchItems.clear();
        }
    }

    public static void addResearch(String researchItemName) {
        synchronized (researchItems) {
            researchItems.add(researchItemName);
            HudClientEvents.showTemporaryMessage("Upgrade completed: " + researchItemName);
        }
    }

    public static boolean hasResearch(String researchItemName) {
        synchronized (researchItems) {
            if (hasCheat("medievalman"))
                return true;
            for (String researchItem : researchItems)
                if (researchItem.equals(researchItemName))
                    return true;
            return false;
        }
    }

    final private static List<String> cheatItems = Collections.synchronizedList(new ArrayList<>());

    public static void removeAllCheats() {
        synchronized (cheatItems) {
            cheatItems.clear();
        }
    }

    public static void addCheat(String cheatItemName) {
        synchronized (cheatItems) {
            cheatItems.add(cheatItemName);
            if (cheatItemName.equals("iseedeadpeople"))
                resetFogChunks();
        }
    }

    public static void removeCheat(String cheatItemName) {
        synchronized (cheatItems) {
            cheatItems.removeIf(r -> r.equals(cheatItemName));
            if (cheatItemName.equals("iseedeadpeople"))
                resetFogChunks();
        }
    }

    public static boolean hasCheat(String cheatItemName) {
        synchronized (cheatItems) {
            for (String cheatItem : cheatItems)
                if (cheatItem.equals(cheatItemName))
                    return true;
            return false;
        }
    }
}
