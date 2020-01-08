/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Tour
 */
public class Rook extends PieceLongRange {
   public Rook(PlayerColor color) {
      super(color);
   }

   @Override
   public boolean canMoveDiagonal() {
      return false;
   }
   @Override
   public boolean canMoveStraight() {
      return true;
   }
   @Override
   public String letter() {
      return "R";
   }
   @Override
   public chess.PieceType type() {
      return PieceType.ROOK; 
   }
}
