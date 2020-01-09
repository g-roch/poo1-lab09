/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;


public abstract class PieceLongRange extends Piece { // Piece long-distance
   protected PieceLongRange(PlayerColor color) {
      super(color);
   }
   /**
    * Dans quel sens la pièce a le droit de se déplacer
    */
   public abstract List<Integer[]> getOrientations();

   @Override
   protected ListCase moveList(Board board, Move lastMove, Case c) {
      ListCase list = new ListCase();
      List<Integer[]> orientations = getOrientations();
      for(Integer[] orientation : orientations) {
         int i = c.x() + orientation[0];
         int j = c.y() + orientation[1];
         while(ListCase.validCoord(i, j)) {
            list.addIfValidCase(i, j);
            if(board.havePiece(i, j))
               break;
            i += orientation[0];
            j += orientation[1];
         }
      }
      return list;
   }
}
