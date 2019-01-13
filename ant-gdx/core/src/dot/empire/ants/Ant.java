package dot.empire.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Ants that move across the world.
 */
// TODO: 13 Jan 2019 toString
public class Ant {

    private int colour;
    private Vec2i direction;
    private Vec2i position;

    public Ant(int colour, Vec2i position) {
        this.colour = colour;
        this.position = position;

        this.direction = new Vec2i(0, 1);
    }

    /**
     * Update.
     */
    public void tick(int[][] field, Ants parent) {
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
            Gdx.app.debug(Ants.TAG, String.format("Removing %s", toString()));
            parent.getToBeRemoved().add(this);
        }
    }

    public Color getColour() {
        return Ants.COLOURS[colour - 1];
    }

    public Vec2i getPosition() {
        return this.position;
    }
}
