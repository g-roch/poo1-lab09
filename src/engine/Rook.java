package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Tour
 */
public class Rook extends PieceLongRange {
   
   private boolean moved;
   
   public Rook(PlayerColor color) {
      super(color);
      moved = false;
   }
   
   public boolean haveMoved() {
      return moved;
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
   public PieceType getType() {
      return PieceType.ROOK; 
   }

   @Override
   public boolean move(Board board, Move move) {
      moved = true;
      return super.move(board, move);

   }

   
}
