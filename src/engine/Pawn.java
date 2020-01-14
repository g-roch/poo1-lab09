package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Pion
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Pawn extends Piece {
   public Pawn(PlayerColor color) {
      super(color);
   }

   /**
    * Détermine la direction dans laquelle le pion se déplace
    * @return +1 ou -1
    */
   private int direction() {
      if(getColor() == PlayerColor.WHITE) return +1;
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
         
         if(baseLine() == c.getY() && board.getPiece(new Case(c.getX(), 
                 c.getY() + 2*direction())) == null) {
            list.addIfValidCase(c.getX(), c.getY() + 2*direction());
         }
      }
      
      // Prise en diagonal s'il y a une pièce en diagonal
      for(int i = -1; i < 2; i += 2) {
         if(c.validAdd(i, direction())) {
            if(board.havePiece(c.add(i, direction()))) {
               // Prise normale
               list.add(c.getX() + i, c.getY() + direction());
            } else if(board.getLastMove() != null
                  && board.havePiece(c.add(i, 0)) 
                  && board.getPiece(c.add(i, 0)) instanceof Pawn 
                  && board.getLastMove().getTo().getX() == c.getX() + i
                  && board.getLastMove().getTo().getY() == c.getY()
                  && board.getLastMove().getFrom().getX() == c.getX() + i
                  && board.getLastMove().getFrom().getY() == c.getY() + 2*direction()
               ) { 
               // Prise en passant
               list.add(c.add(i, direction()));
            }

         }
      }
      return list;

   }
   @Override
   public PieceType getType() {
      return PieceType.PAWN; 
   }

   @Override
   public boolean move(Board board, Move move) {
      if(!board.havePiece(move.getTo())
            && move.getTo().getX() != move.getFrom().getX()
         ) {
         // Prise en passant
         board.setPiece(new Case(move.getTo().getX(), move.getFrom().getY()), null);
      }
      return super.move(board, move);
   }
}
