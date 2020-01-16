package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Fou
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Bishop extends PieceLongRange {
   public Bishop(PlayerColor color) {
      super(color);
   }
   
   /**
    * Indique dans quels sens le fou peut se déplacer
    */
   @Override
   public List<Integer[]> getOrientations() {
      List<Integer[]> orientations = new LinkedList<>();
      orientations.add(new Integer[] {1,1});
      orientations.add(new Integer[] {1,-1});
      orientations.add(new Integer[] {-1,1});
      orientations.add(new Integer[] {-1,-1});
      return orientations;
   }

   @Override
   public PieceType getType() {
      return PieceType.BISHOP; 
   }
}
