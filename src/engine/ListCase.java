package engine;

import java.util.LinkedList;

/**
 * Gère une liste de "Case"
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class ListCase extends LinkedList<Case> {

   /**
    * Ajoute une case
    * @param x Coordonnée horizontale
    * @param y Coordonnée verticale
    */
   public void add(int x, int y) {
      add(new Case(x, y));
   }

   /**
    * Ajoute une case si les coordonnéee fournies sont valides
    * @param x Coordonnée horizontale
    * @param y Coordonnée verticale
    */
   public void addIfValidCase(int x, int y) {
      if(Board.validCoord(x, y))
         add(x, y);
   }
   
}
