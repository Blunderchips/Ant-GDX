package dot.empire.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Ant that move across the world.
 */
public class Ant {

    /**
     * Index of {@link LangtonsAnt#COLOURS}.
     */
    private int colour;
    /**
     * The direction the ant is moving in.
     */
    private Vec2i direction;
    /**
     * The current position of the ant.
     */
    private Vec2i position;

    public Ant(int colour, Vec2i position) {
        this.colour = colour;
        this.position = position;

        this.direction = new Vec2i(0, 1);
        Gdx.app.debug(LangtonsAnt.TAG, String.format("New ant = %d", colour));
    }

    /**
     * Update.
     *
     * @param field  grid of cells
     * @param  parent parent engine
     */
    public void tick(int[][] field, LangtonsAnt parent) {
        try {
            if (field[position.x][position.y] == 0) {
                field[position.x][position.y] = colour;
                this.direction.right();
            } else {
                field[position.x][position.y] = 0;
                this.direction.left();
            }
            this.position.add(direction);
        } catch (IndexOutOfBoundsException ioobe) {
            Gdx.app.debug(LangtonsAnt.TAG, String.format("Removing %s", toString()));
            parent.getToBeRemoved().add(this);
        }
    }

    /**
     * @return the colour of the ant.
     * @see com.badlogic.gdx.graphics.Color
     * @see LangtonsAnt#COLOURS
     */
    public Color getColour() {
        return LangtonsAnt.COLOURS[colour - 1];
    }

    /**
     * @return the current position of the ant
     */
    public Vec2i getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return String.format("Ant[%s, %s]", getColour().toString(), getPosition().toString());
    }
}
