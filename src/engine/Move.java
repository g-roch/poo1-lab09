/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import engine.Case;

public class Move {
   private Case from;
   private Case to;
   // TODO: ¿ Test from == to ?
   public Move(Case from, Case to) {
      this.from = from;
      this.to = to;
   }
   public Case from() {
      return from;
   }
   public Case to() {
      return to;
   }
}
