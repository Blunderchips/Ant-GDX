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
 */
// https://en.wikipedia.org/wiki/Langton%27s_ant
public final class LangtonsAnt extends ApplicationAdapter implements Disposable {

    /**
     * Tag for logging;
     */
    public static final String TAG = "LangtonsAnt-GDX";

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    public static final Color[] COLOURS = {CHARTREUSE, GOLD, TAN, SCARLET,
            PINK, VIOLET, SKY, VIOLET};

    private ShapeRenderer renderer;
    private int[][] field;
    private Vec2i size;
    private int numBlocks = 200;

    /**
     * Moving ants of the word.
     */
    private List<Ant> ants;
    private List<Ant> toBeRemoved;

    private FPSLogger fpsLogger;

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

    private void init() {
        this.field = new int[numBlocks][numBlocks];

        this.size = new Vec2i((int) (WIDTH / (float) numBlocks), (int) (HEIGHT / (float) numBlocks));

        Gdx.app.debug(LangtonsAnt.TAG, String.format("Size = %s", size.toString()));

        int numAnts = 100;
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
    @SuppressWarnings("LibGDXProfilingCode")
    @Override
    public void render() {
        // update
        this.fpsLogger.log();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

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

    @Override
    public void dispose() {
        this.renderer.dispose();
    }

    public List<Ant> getToBeRemoved() {
        return this.toBeRemoved;
    }
}
