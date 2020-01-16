package engine;

import java.util.Objects;

/**
 * Classe permettant de référencer une case de l'échiquier
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
class Case {
    /**
     * Coordonnée horizontale dans l'échiquier (0 à gauche)
     */
    private int x;
    
    /**
     * Coordonnée verticale dans l'échiquier (0 en bas)
     */
    private int y;

    public int getX() { return x; }
    public int getY() { return y; }

    /**
     * @param x Coordonnée horizontale
     * @param y Coordonnée verticale
     */
    public Case(int x, int y) {
        Objects.checkIndex(x, 8);
        Objects.checkIndex(y, 8);
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne une nouvelle Case avec le décalage x, y demandé
     * @param x Décalage horizontal
     * @param y Décalage vertical
     * @return La nouvelle case
     */
    public Case add(int x, int y) {
        return new Case(this.x + x, this.y + y);
    }
    
    public Case add(Integer orientation[]) {
        if (orientation.length != 2) 
           throw new RuntimeException("Orientation need 2 elems");
        
        return add(orientation[0], orientation[1]);
    }

    /**
     * Test si un décalage donne une case valide
     * @param x Décalage horizontal
     * @param y Décalage vertical
     * @return true si le décalage tombe sur une case valide
     */
    public boolean validAdd(int x, int y) {
        return Board.validCoord(this.x + x, this.y + y);
    }
    
    public boolean validAdd(Integer orientation[]) {
        if (orientation.length != 2) 
           throw new RuntimeException("Orientation need 2 elems");
        
        return validAdd(orientation[0], orientation[1]);
    }

    /**
     * Compare les coordonnées de deux "Case"
     * @param o Case à comparer
     * @return true si les "Case" sont les mêmes
     */
    public boolean equals(Object o) {
        return o == this
                || o != null
                && o instanceof Case
                && ((Case) o).x == x && ((Case) o).y == y;
    }
}