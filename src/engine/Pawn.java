/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Pion
 */
public class Pawn extends Piece {
   public Pawn(PlayerColor color) {
      super(color);
   }

   private int direction() {
      if(color() == PlayerColor.WHITE) return +1;
      else return -1;
   }

   @Override
   public String letter() {
      return ""; // Il n'y a pas de lettre en notation algébrique
   }
   @Override
   public String letterWithMove() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   @Override
   protected ListCase moveList(Board board, Move lastMove, Case c) {
      ListCase list = new ListCase();
      int baseLine;
      if(direction() == 1)
         baseLine = 1;
      else
         baseLine = 6;
      
      if(!board.havePiece(c.x(), c.y() + direction())) {
         list.addIfValidCase(c.x(), c.y() + direction());
         if(baseLine == c.y() && board.getPiece(c.x(), c.y() + 2*direction()) == null) {
            list.addIfValidCase(c.x(), c.y() + 2*direction());
         }
      }
      
      
      if(ListCase.validCoord(c.x() - 1, c.y() + direction()) 
              && board.havePiece(c.x() - 1, c.y() + direction())) {
         list.add(c.x() - 1, c.y() + direction());
      }
      if(ListCase.validCoord(c.x() + 1, c.y() + direction()) 
              && board.havePiece(c.x() + 1, c.y() + direction())) {
         list.add(c.x() + 1, c.y() + direction());
      }

      return list;

      // TODO
      // if(chessboard[x][y+direction()] == null)
      //    list.add(new Case(x, y+direction()));
      // // Avance de 2 au début
      // if(y == baseLine && chessboard[x][y+direction()] == null
      //       && chessboard[x][y+direction()+direction()] == null)
      //    list.add(new Case(x, y+direction()+direction()));

      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   @Override
   public chess.PieceType type() {
      return PieceType.PAWN; 
   }
}
