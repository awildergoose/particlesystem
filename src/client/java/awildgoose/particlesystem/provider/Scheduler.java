package awildgoose.particlesystem.provider;

import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Scheduler {
    private static final Map<Integer, List<Consumer<World>>> tasks = new HashMap<>();
    private static int currentTick = 0;

    public static void schedule(int delayTicks, Consumer<World> task) {
        int runTick = currentTick + delayTicks;
        tasks.computeIfAbsent(runTick, k -> new ArrayList<>()).add(task);
    }

    public static void tick(World world) {
        currentTick++;
        List<Consumer<World>> tasksForTick = tasks.remove(currentTick);
        if (tasksForTick != null) {
            for (Consumer<World> task : tasksForTick) {
                task.accept(world);
            }
        }
    }

}
