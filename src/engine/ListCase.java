package engine;

import java.util.LinkedList;

/**
 * Gére une liste de Case
 */
public class ListCase extends LinkedList<Case> {

   /**
    * Ajoute une case
    * @param x Coordonnée horizontal
    * @param y Coordonnée vertical
    */
   public void add(int x, int y) {
      add(new Case(x, y));
   }

   /**
    * Ajoute une case si les coordonée fonrnie sont valide
    * @param x Coordonnée horizontal
    * @param y Coordonnée vertical
    */
   public void addIfValidCase(int x, int y) {
      if(Board.validCoord(x, y))
         add(x, y);
   }
}
