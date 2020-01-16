package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Dame
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Queen extends PieceLongRange {
   
   public Queen(PlayerColor color) {
      super(color);
   }

  /**
    * Indique dans quels sens la dame peut se déplacer
    */
   @Override
   public List<Integer[]> getOrientations() {
      List<Integer[]> orientations = new LinkedList<>();
      orientations.add(new Integer[] {1,0});
      orientations.add(new Integer[] {-1,0});
      orientations.add(new Integer[] {0,1});
      orientations.add(new Integer[] {0,-1});
      orientations.add(new Integer[] {1,1});
      orientations.add(new Integer[] {1,-1});
      orientations.add(new Integer[] {-1,1});
      orientations.add(new Integer[] {-1,-1});
      return orientations;
   }

   @Override
   public chess.PieceType getType() {
      return PieceType.QUEEN; 
   }
}
