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
      
      // avance tout droit
      if(!board.havePiece(c.add(0, direction()))) {
         list.addIfValidCase(c.x(), c.y() + direction());
         if(baseLine == c.y() && board.getPiece(c.x(), c.y() + 2*direction()) == null) {
            list.addIfValidCase(c.x(), c.y() + 2*direction());
         }
      }
      
      // Prise
      for(int i = -1; i < 2; i += 2) {
         if(c.validAdd(i, direction())) {
            if(board.havePiece(c.add(i, direction()))) {
               // Prise normal
               list.add(c.x() + i, c.y() + direction());
            } else if(lastMove != null
                  && board.havePiece(c.add(i, 0)) 
                  && board.getPiece(c.add(i, 0)) instanceof Pawn 
                  && lastMove.to().x() == c.x() + i
                  && lastMove.to().y() == c.y()
                  && lastMove.from().x() == c.x() + i
                  && lastMove.from().y() == c.y() + 2*direction()
               ) { 
               // Prise en passant
               list.add(c.add(i, direction()));
            }

         }
      }
      return list;

   }
   @Override
   public chess.PieceType type() {
      return PieceType.PAWN; 
   }

   @Override
   public void move(Board board, Move move) {
      if(!board.havePiece(move.to())
            && move.to().x() != move.from().x()
         ) {
         // prise en passant
         board.setPiece(move.to().x(), move.from().y(), null);
         System.out.println("asdf");
      }
      super.move(board, move);
   }
}
