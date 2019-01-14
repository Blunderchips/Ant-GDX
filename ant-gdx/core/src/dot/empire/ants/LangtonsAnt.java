package dot.empire.ants;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.badlogic.gdx.graphics.Color.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

/**
 * Main class.
 *
 * @author Matthew 'siD' Van der Bijl
 */
// https://en.wikipedia.org/wiki/Langton%27s_ant
public final class LangtonsAnt extends ApplicationAdapter implements Disposable {

    /**
     * Tag for logging;
     */
    public static final String TAG = "LangtonsAnt-GDX";

    /**
     * The width of the screen in pixels.
     */
    public static final int WIDTH = 1600;
    /**
     * The height of the screen in pixels.
     */
    public static final int HEIGHT = 900;

    /**
     * Potential {@link Ant} colours.
     */
    public static final Color[] COLOURS = {
            CHARTREUSE, GOLD, TAN, SCARLET,
            PINK, VIOLET, SKY, VIOLET
    };

    /**
     * Used to render the world.
     */
    private ShapeRenderer renderer;
    /**
     * World grid.
     */
    private int[][] field;
    /**
     * The size of each cell in the world grid.
     */
    private Vec2i size;

    private int numBlocks = 200; // TODO: 14 Jan 2019 remove

    /**
     * Moving ants of the word.
     */
    private List<Ant> ants;
    /**
     * {@code LinkedList} of ants that need to be removed from the world as they have reached its limits.
     */
    private List<Ant> toBeRemoved;
    /**
     * Prints simulation frame per seconds (fps) to the console.
     */
    private FPSLogger fpsLogger;

    /**
     * Called on start up.
     */
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.gl.glClearColor(1, 1, 1, 1); // white

        this.renderer = new ShapeRenderer();
        this.renderer.setAutoShapeType(true);

        init();

        this.fpsLogger = new FPSLogger();
        this.toBeRemoved = new LinkedList<Ant>();
    }

    /**
     * Initialise the world.
     */
    private void init() {
        this.field = new int[numBlocks][numBlocks];

        this.size = new Vec2i((int) (WIDTH / (float) numBlocks), (int) (HEIGHT / (float) numBlocks));

        Gdx.app.debug(LangtonsAnt.TAG, String.format("Size = %s", size.toString()));

        int numAnts = 100; // number of ants in the world
        this.ants = new ArrayList<Ant>(numAnts);
        for (int i = 0; i < numAnts; i++) {
            addAnt();
        }
    }

    /**
     * Add a new ant to the world at a random location with a random colour.
     */
    private void addAnt() {
        this.ants.add(new Ant(MathUtils.random(COLOURS.length), new Vec2i(MathUtils.random(numBlocks), MathUtils.random(numBlocks))));
    }

    /**
     * Tick. Called once per frame. Updates and renders the simulation.
     */
    @Override
    @SuppressWarnings("LibGDXProfilingCode")
    public void render() {
        // update
        this.fpsLogger.log(); // LibGDXProfilingCode
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { // restart
            init();
            return;
        }
        for (Ant ant : ants) {
            ant.tick(field, this);
        }
        for (Ant ant : toBeRemoved) {
            this.ants.remove(ant);
            addAnt();
        }
        this.toBeRemoved.clear();

        // render
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT); // clear screen

        // render world grid
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        {
            for (int x = 0; x < field.length; x++) {
                for (int y = 0; y < field[0].length; y++) {
                    if (field[x][y] == 0) {
                        continue;
                    }

                    this.renderer.setColor(COLOURS[field[x][y] - 1]);
                    this.renderer.rect(x * size.x, y * size.y, size.x, size.y);
                }
            }
        }
        this.renderer.end();
    }

    /**
     * Called on shutdown.
     */
    @Override
    public void dispose() {
        this.renderer.dispose();
    }

    public List<Ant> getToBeRemoved() {
        return this.toBeRemoved;
    }
}
