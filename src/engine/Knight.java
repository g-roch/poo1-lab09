package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Cavalier
 */
public class Knight extends Piece {
   public Knight(PlayerColor color) {
      super(color);
   }

   @Override
   protected ListCase moveList(Board board, Case c) {
      ListCase list = new ListCase();
      list.addIfValidCase(c.getX()-2, c.getY()-1);
      list.addIfValidCase(c.getX()-1, c.getY()-2);
      list.addIfValidCase(c.getX()+1, c.getY()-2);
      list.addIfValidCase(c.getX()+2, c.getY()-1);
      list.addIfValidCase(c.getX()-2, c.getY()+1);
      list.addIfValidCase(c.getX()-1, c.getY()+2);
      list.addIfValidCase(c.getX()+1, c.getY()+2);
      list.addIfValidCase(c.getX()+2, c.getY()+1);
      return list;
   }
   @Override
   public PieceType getType() {
      return PieceType.KNIGHT; 
   }
}
