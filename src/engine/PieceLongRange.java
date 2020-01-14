package engine;

import chess.PlayerColor;
import java.util.List;


public abstract class PieceLongRange extends Piece { // Piece long-distance
   protected PieceLongRange(PlayerColor color) {
      super(color);
   }
   /**
    * Dans quel sens la pièce a le droit de se déplacer
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
