package dot.empire.ants;

public class Vec2i {

    public int x;
    public int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public void left() {
        Vec2i tmp = cpy();
        this.x = tmp.y;
        this.y = -tmp.x;
    }

    public void right() {
        Vec2i tmp = cpy();
        this.x = -tmp.y;
        this.y = tmp.x;
    }

    public Vec2i cpy() {
        return new Vec2i(this);
    }
}
