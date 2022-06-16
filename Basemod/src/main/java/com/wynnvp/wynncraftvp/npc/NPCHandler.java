package com.wynnvp.wynncraftvp.npc;

import net.minecraft.util.math.Vec3d;

import java.util.*;

public class NPCHandler {

    private static final Map<String, List<Vec3d>> namesHandlers = new HashMap<>();

    public static void add(String name, Vec3d vector) {
        if (namesHandlers.containsKey(name)) {
            List<Vec3d> list = namesHandlers.get(name);
            if (list.contains(vector)) return;
            list.add(new Vec3d((int)vector.x, (int)vector.y, (int)vector.z));
            namesHandlers.put(name, list);
        } else {
            namesHandlers.put(name, new ArrayList<>(Collections.singletonList(new Vec3d((int) vector.x, (int) vector.y, (int) vector.z))));
        }
    }

    //Get the closest armorstand
    //WARNING: System in test
    public static Optional<Vec3d> find(String rawNames) {
        Vec3d result = new Vec3d(0, 0, 0);
        List<Vec3d> list = namesHandlers.get(rawNames);
        try {
            result = list.stream().sorted(Comparator.comparingDouble(Vec3d::lengthSquared)).findAny().orElse(result);
        } catch (IllegalArgumentException exception) {
            result = list.stream().findAny().orElse(result);
        }
        return Optional.of(result);
    }

    /*public static Vec3d find(String rawNames) {
        return namesHandlers.get(rawNames).stream().findAny().orElse(new Vec3d(0, 0, 0));
    }*/

    public static void remove(String name) {
        namesHandlers.remove(name);
    }

    public static Map<String, List<Vec3d>> getNamesHandlers() {
        return namesHandlers;
    }
}
