package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Pion
 */
public class Pawn extends Piece {
   public Pawn(PlayerColor color) {
      super(color);
   }

   /**
    * Détermine la direction dans laquel le pion se déplace
    * @return +1 ou -1
    */
   private int direction() {
      if(color() == PlayerColor.WHITE) return +1;
      else return -1;
   }

   /**
    * Détermine la ligne de base du pion
    * @return 1 ou 6
    */
   private int baseLine() {
      if(direction() == 1)
         return 1;
      else
         return 6;
   }

   @Override
   protected ListCase moveList(Board board, Case c) {
      ListCase list = new ListCase();

      // avance tout droit si la case est libre
      if(!board.havePiece(c.add(0, direction()))) {
         list.addIfValidCase(c.getX(), c.getY() + direction());
         if(baseLine() == c.getY() && board.getPiece(new Case(c.getX(), c.getY() + 2*direction())) == null) {
            list.addIfValidCase(c.getX(), c.getY() + 2*direction());
         }
      }
      
      // Prise en diagonal s'il y a une piece en diagonal
      for(int i = -1; i < 2; i += 2) {
         if(c.validAdd(i, direction())) {
            if(board.havePiece(c.add(i, direction()))) {
               // Prise normal
               list.add(c.getX() + i, c.getY() + direction());
            } else if(board.getLastMove() != null
                  && board.havePiece(c.add(i, 0)) 
                  && board.getPiece(c.add(i, 0)) instanceof Pawn 
                  && board.getLastMove().to().getX() == c.getX() + i
                  && board.getLastMove().to().getY() == c.getY()
                  && board.getLastMove().from().getX() == c.getX() + i
                  && board.getLastMove().from().getY() == c.getY() + 2*direction()
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
   public boolean move(Board board, Move move) {
      if(!board.havePiece(move.to())
            && move.to().getX() != move.from().getX()
         ) {
         // prise en passant
         board.setPiece(new Case(move.to().getX(), move.from().getY()), null);
      }
      return super.move(board, move);
   }
}
