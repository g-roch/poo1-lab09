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
   public Knight(PlayerColor color, int x, int y) {
      super(color, x, y);
   }

   @Override
   public String letter() {
      return "N";
   }
   @Override
   protected List<Case> moveList(Board board, Move lastMove) {
      List<Case> list = new LinkedList<>();
      if(x > 1 && y > 0)   list.add(new Case(x-2, y-1));
      if(x > 0 && y > 1)   list.add(new Case(x-1, y-2));
      if(x < 7 && y > 1)   list.add(new Case(x+1, y-2));
      if(x < 6 && y > 0)   list.add(new Case(x+2, y-1));
      if(x > 1 && y < 7)   list.add(new Case(x-2, y+1));
      if(x > 0 && y < 6)   list.add(new Case(x-1, y+2));
      if(x < 7 && y < 6)   list.add(new Case(x+1, y+2));
      if(x < 6 && y < 7)   list.add(new Case(x+2, y+1));
      return list;
   }
   @Override
   public chess.PieceType type() {
      return PieceType.KNIGHT; 
   }
}
