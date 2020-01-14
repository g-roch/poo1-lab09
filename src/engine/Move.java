package engine;

/**
 * Gère le mouvement des pièces
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Move {
   /**
    * Case de départ du mouvement
    */
   private Case from;
   
   /**
    * Case de destination du mouvement
    */
   private Case to;

   public Case getFrom() {
      return from;
   }
   public Case getTo() {
      return to;
   }

   public Move(Case from, Case to) {
      this.from = from;
      this.to = to;
   }
   
}
