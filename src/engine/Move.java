package engine;

public class Move {
   /**
    * Case de départ du mouvement
    */
   private Case from;
   /**
    * Case de destination du mouvement
    */
   private Case to;

   public Case from() {
      return from;
   }
   public Case to() {
      return to;
   }

   public Move(Case from, Case to) {
      this.from = from;
      this.to = to;
   }
}
