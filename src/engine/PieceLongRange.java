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
   @Override
   protected ListCase moveList(Board board, Move lastMove, Case c) {
      ListCase list = new ListCase();
      List<Integer[]> orientations = new LinkedList<>();
      if(canMoveStraight()) {
         orientations.add(new Integer[] {1,0});
         orientations.add(new Integer[] {-1,0});
         orientations.add(new Integer[] {0,1});
         orientations.add(new Integer[] {0,-1});
      }
      if(canMoveDiagonal()) {
         orientations.add(new Integer[] {1,1});
         orientations.add(new Integer[] {1,-1});
         orientations.add(new Integer[] {-1,1});
         orientations.add(new Integer[] {-1,-1});
      }
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
      // TODO uncomment
      //if(canMoveStraight()) {
      //   for(int i = x; i >= 0; --i) {
      //      list.add(new Case(i, y));
      //      if(chessboard[i][y] != null) 
      //         break;
      //   }
      //   for(int i = x; i < 8; ++i) {
      //      list.add(new Case(i, y));
      //      if(chessboard[i][y] != null) 
      //         break;
      //   }
      //   for(int i = y; i >= 0; --i) {
      //      list.add(new Case(x, i));
      //      if(chessboard[y][i] != null) 
      //         break;
      //   }
      //   for(int i = y; i < 8; ++i) {
      //      list.add(new Case(x, i));
      //      if(chessboard[y][i] != null) 
      //         break;
      //   }
      //}
      //if(canMoveDiagonal()) {
      //   for(int i = x, j = y; i >= 0 && j >= 0; --i, --j) {
      //      list.add(new Case(i, j));
      //      if(chessboard[i][j] != null) 
      //         break;
      //   }
      //   for(int i = x, j = y; i >= 0 && j < 8; --i, ++j) {
      //      list.add(new Case(i, j));
      //      if(chessboard[i][j] != null) 
      //         break;
      //   }
      //   for(int i = x, j = y; i < 8 && j >= 0; ++i, --j) {
      //      list.add(new Case(i, j));
      //      if(chessboard[i][j] != null) 
      //         break;
      //   }
      //   for(int i = x, j = y; i < 8 && j < 8; ++i, ++j) {
      //      list.add(new Case(i, j));
      //      if(chessboard[i][j] != null) 
      //         break;
      //   }
      //}
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   /**
    * La piece a le droit de se déplecer en digonale
    */
   public abstract boolean canMoveDiagonal();
   /**
    * La piece a le droit de se déplecer en ligne droite
    */
   public abstract boolean canMoveStraight();
}
