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
   public King(PlayerColor color, int x, int y) {
      super(color, x, y);
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
   protected List<Case> moveList(Board board, Move lastMove) {
      List<Case> list = new LinkedList<>();
      // TODO
      Board.addIfValidCase(x-1, y-1);
      Board.addIfValidCase(x-1, y  );
      Board.addIfValidCase(x-1, y+1);
      Board.addIfValidCase(x  , y-1);
      Board.addIfValidCase(x  , y+1);
      Board.addIfValidCase(x+1, y-1);
      Board.addIfValidCase(x+1, y  );
      Board.addIfValidCase(x+1, y+1);

      if(canCastlingShort()) list.add(new Case(5, y));
      if(canCastlingLong())  list.add(new Case(2, y));
      return list;
   }
   @Override
   public chess.PieceType type() {
      return PieceType.KING; 
   }
}

