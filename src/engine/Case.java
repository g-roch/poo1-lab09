/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import java.util.Objects;

/**
 * Classe permettant de référencer un case de l'échiquier
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
class Case {
    private int x;
    private int y;
    /**
    * @exception RuntimeException asdf
    */
    public Case(int x, int y) throws RuntimeException {
        Objects.checkIndex(x, 8);
        Objects.checkIndex(y, 8);
        this.x = x;
        this.y = y;
    }

    public static boolean validCoord(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Case add(int x, int y) throws RuntimeException {
        return new Case(this.x + x, this.y + y);
    }
    public Case add(Integer orientation[]) {
        if (orientation.length != 2) throw new RuntimeException("Orientation need 2 elems");
        return add(orientation[0], orientation[1]);
    }
    public boolean validAdd(int x, int y) {
        return validCoord(this.x + x, this.y + y);
    }
    public boolean validAdd(Integer orientation[]) {
        if (orientation.length != 2) throw new RuntimeException("Orientation need 2 elems");
        return validAdd(orientation[0], orientation[1]);
    }

    public int x() { return x; }
    public int y() { return y; }

    public boolean equals(Object o) {
        return o == this
                || o != null
                && o.getClass() == getClass()
                && ((Case) o).x == x && ((Case) o).y == y;
    }
}

