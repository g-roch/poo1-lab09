/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Roi
 */
public class King extends Piece {
   public King(PlayerColor color) {
      super(color);
   }

   private boolean canCastlingShort() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   private boolean canCastlingLong() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public String letter() {
      return "K";
   }
   @Override
   public String letterWithMove() {
      String ret = super.letterWithMove();
      if(canCastlingLong()) ret = "+"+ret;
      if(canCastlingShort()) ret += "+";
      return ret;
   }
   @Override
   protected ListCase moveList(Board board, Move lastMove, Case c) {
      ListCase list = new ListCase();
      // TODO
      list.addIfValidCase(c.x()-1, c.y()-1);
      list.addIfValidCase(c.x()-1, c.y()  );
      list.addIfValidCase(c.x()-1, c.y()+1);
      list.addIfValidCase(c.x()  , c.y()-1);
      list.addIfValidCase(c.x()  , c.y()+1);
      list.addIfValidCase(c.x()+1, c.y()-1);
      list.addIfValidCase(c.x()+1, c.y()  );
      list.addIfValidCase(c.x()+1, c.y()+1);

      if(canCastlingShort()) list.addIfValidCase(5, c.y());
      if(canCastlingLong())  list.addIfValidCase(2, c.y());
      return list;
   }
   @Override
   public chess.PieceType type() {
      return PieceType.KING; 
   }
}

