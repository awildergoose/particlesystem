package awildgoose.particlesystem.provider;

import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Scheduler {
    private static final Map<Integer, Consumer<World>> tasks = new HashMap<>();
    private static int currentTick = 0;

    public static void tick(World world) {
        currentTick++;
        if (tasks.containsKey(currentTick)) {
            tasks.get(currentTick).accept(world);
            tasks.remove(currentTick);
        }
    }

    public static void schedule(int delayTicks, Consumer<World> task) {
        tasks.put(currentTick + delayTicks, task);
    }
}
