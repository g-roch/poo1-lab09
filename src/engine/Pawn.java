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
   public Pawn(PlayerColor color, int x, int y) {
      super(color, x, y);
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
   protected List<Case> moveList(Board board, Move lastMove) {
      List<Case> list = new LinkedList<>();
      int baseLine;
      if(direction() == 1)
         baseLine = 6;
      else
         baseLine = 1;

      // TODO
      // if(chessboard[x][y+direction()] == null)
      //    list.add(new Case(x, y+direction()));
      // // Avance de 2 au début
      // if(y == baseLine && chessboard[x][y+direction()] == null
      //       && chessboard[x][y+direction()+direction()] == null)
      //    list.add(new Case(x, y+direction()+direction()));

      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   @Override
   public chess.PieceType type() {
      return PieceType.PAWN; 
   }
}
