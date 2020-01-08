/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Cavalier
 */
public class Knight extends Piece {
   public Knight(PlayerColor color) {
      super(color);
   }

   @Override
   public String letter() {
      return "N";
   }
   @Override
   protected ListCase moveList(Board board, Move lastMove, Case c) {
      ListCase list = new ListCase();
      list.addIfValidCase(c.x()-2, c.y()-1);
      list.addIfValidCase(c.x()-1, c.y()-2);
      list.addIfValidCase(c.x()+1, c.y()-2);
      list.addIfValidCase(c.x()+2, c.y()-1);
      list.addIfValidCase(c.x()-2, c.y()+1);
      list.addIfValidCase(c.x()-1, c.y()+2);
      list.addIfValidCase(c.x()+1, c.y()+2);
      list.addIfValidCase(c.x()+2, c.y()+1);
      return list;
   }
   @Override
   public chess.PieceType type() {
      return PieceType.KNIGHT; 
   }
}
