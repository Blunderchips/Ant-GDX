package dot.empire.ants;

/**
 * 2-point {@link Integer} Vector class. Used to represent the direction and position of a given {@link Ant}.
 *
 * @author Matthew 'siD' Van der Bijl
 */
// https://stackoverflow.com/questions/4780119/2d-euclidean-vector-rotations
public class Vec2i {

    /**
     * X-axis component.
     */
    public int x;
    /**
     * Y-axis component.
     */
    public int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param src copy source
     */
    public Vec2i(Vec2i src) {
        this.x = src.x;
        this.y = src.y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec2i)) return false;

        Vec2i vec2i = (Vec2i) o;

        if (x != vec2i.x) return false;
        return y == vec2i.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ',' + y + ')';
    }

    public Vec2i add(Vec2i other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vec2i left() {
        final Vec2i tmp = cpy();
        this.x = tmp.y;
        this.y = -tmp.x;
        return this;
    }

    public Vec2i right() {
        final Vec2i tmp = cpy();
        this.x = -tmp.y;
        this.y = tmp.x;
        return this;
    }

    public Vec2i cpy() {
        return new Vec2i(this);
    }
}
