package engine;

import chess.PlayerColor;
import java.util.List;

/**
 * Pièces se déplaçant sur de grandes distances (dame, tour, fou)
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */

public abstract class PieceLongRange extends Piece { // Pièce longue distance
   
   protected PieceLongRange(PlayerColor color) {
      super(color);
   }
   
   /**
    * Dans quels sens la pièce a le droit de se déplacer
    */
   public abstract List<Integer[]> getOrientations();

   @Override
   protected ListCase moveList(Board board, Case c) {
      ListCase list = new ListCase();
      List<Integer[]> orientations = getOrientations();
      
      for(Integer[] orientation : orientations) {
         Case destinationCase = c;
         
         while(destinationCase.validAdd(orientation)) {
            destinationCase = destinationCase.add(orientation);
            list.add(destinationCase);
            
            if(board.havePiece(destinationCase))
               break;
         }
      }
      return list;
   }
}
