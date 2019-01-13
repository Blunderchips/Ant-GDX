package dot.empire.ants.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dot.empire.ants.Ants;

/**
 * Main launcher.s
 */
public final class DesktopLauncher {

    /**
     * @param args Arguments from the command line
     */
    public static void main(String[] args) {
        final LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();

        cfg.title = Ants.TAG;

        cfg.width = Ants.WIDTH;
        cfg.height = Ants.HEIGHT;

        cfg.resizable = false;
        cfg.samples = 0; // for clean edges

        int tickRate = 60;
        cfg.foregroundFPS = tickRate;
        cfg.backgroundFPS = tickRate;

        new LwjglApplication(new Ants(), cfg);
    }
}
