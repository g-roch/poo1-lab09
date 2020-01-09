/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Tour
 */
public class Rook extends PieceLongRange {
   public Rook(PlayerColor color) {
      super(color);
   }

   @Override
   public List<Integer[]> getOrientations() {
      List<Integer[]> orientations = new LinkedList<>();
      orientations.add(new Integer[] {1,0});
      orientations.add(new Integer[] {-1,0});
      orientations.add(new Integer[] {0,1});
      orientations.add(new Integer[] {0,-1});
      return orientations;
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
