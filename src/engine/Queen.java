/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Dame
 */
public class Queen extends PieceLongRange {
   public Queen(PlayerColor color) {
      super(color);
   }

   @Override
   public boolean canMoveDiagonal() {
      return true;
   }
   @Override
   public boolean canMoveStraight() {
      return true;
   }
   @Override
   public String letter() {
      return "Q";
   }
   @Override
   public chess.PieceType type() {
      return PieceType.QUEEN; 
   }
}
