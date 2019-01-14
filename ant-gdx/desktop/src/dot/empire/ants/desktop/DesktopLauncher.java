package dot.empire.ants.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ezware.dialog.task.TaskDialogs;
import dot.empire.ants.LangtonsAnt;

import java.awt.*;

/**
 * Main launcher. Runs the simulation in a desktop.
 */
public final class DesktopLauncher {

    /**
     * @deprecated You can not instantiate this class.
     */
    @Deprecated
    private DesktopLauncher() {
    }

    /**
     * @param args Arguments from the command line
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        final LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();

        cfg.title = LangtonsAnt.TAG;

        cfg.width = LangtonsAnt.WIDTH;
        cfg.height = LangtonsAnt.HEIGHT;

        cfg.resizable = false;
        cfg.samples = 0; // for clean edges

        int tickRate = 60;
        cfg.foregroundFPS = tickRate;
        cfg.backgroundFPS = tickRate;

        try {
            new LwjglApplication(new LangtonsAnt(), cfg); // ResultOfObjectAllocationIgnored
        } catch (Throwable t) {
            Toolkit.getDefaultToolkit().beep();
            t.printStackTrace(System.err);
            TaskDialogs.showException(t);
        }
    }
}
