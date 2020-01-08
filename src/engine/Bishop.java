/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Fou
 */
public class Bishop extends PieceLongRange {
   public Bishop(PlayerColor color, int x, int y) {
      super(color, x, y);
   }

   @Override
   public boolean canMoveDiagonal() {
      return true;
   }
   @Override
   public boolean canMoveStraight() {
      return false;
   }
   @Override
   public String letter() {
      return "B";
   }
   @Override
   public chess.PieceType type() {
      return PieceType.BISHOP; 
   }
}
