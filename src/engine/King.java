/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Roi
 */
public class King extends Piece {
   
   private boolean moved;
   
   public King(PlayerColor color) {
      super(color);
      moved = false;
   }
   
   private int baseLine() {
      if(color() == PlayerColor.WHITE)
         return 0;
      else
         return 7;
   }

   private boolean canCastling(Board board, int rookX) {
      int neighbourX = (rookX == 0 ? 3 : 5);
      Case rookCase = new Case(rookX, baseLine());
      Case neighbourCase = new Case(neighbourX, baseLine());
      Case kingCase = new Case(4, baseLine());
      return !moved
              && board.havePiece(rookCase)
              && board.getPiece(rookCase) instanceof Rook
              && !((Rook) board.getPiece(rookCase)).haveMoved()
              && !board.caseInCheck(neighbourCase, color())
              && !board.caseInCheck(kingCase, color())
              ;
   }
   private boolean canCastlingShort(Board board) {
      return canCastling(board, 7);
   }
   private boolean canCastlingLong(Board board) {
      return canCastling(board, 0);
   }
   private void castling(Board board, int rookX) {
      int direction = (rookX == 0 ? -1 : 1);
      Case rookCase = new Case(rookX, baseLine());
      Case neighbourCase = new Case(4+direction, baseLine());
      Case secondNeighbourCase = new Case(4+2*direction, baseLine());
      Case kingCase = new Case(4, baseLine());
      board.setPiece(neighbourCase, board.getPiece(rookCase));
      board.setPiece(secondNeighbourCase, board.getPiece(kingCase));
      board.setPiece(rookCase, null);
      board.setPiece(kingCase, null);
   }

   @Override
   public String letter() {
      return "K";
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

      return list;
   }

   @Override
   public ListCase possibleMove(Board board, Move lastMove, Case c) {
      ListCase list = moveList(board, lastMove, c);
      if(canCastlingShort(board)) list.add(6, c.y());
      if(canCastlingLong(board))  list.add(2, c.y());
      ListCase possibleMove = new ListCase();
      for(Case destinationCase : list) {
         Board tmpBoard = new Board(board);
         tmpBoard.getPiece(c).move(tmpBoard, new Move(c, destinationCase));
         if(!tmpBoard.kingInCheck(color())) {
            possibleMove.add(destinationCase);
         }
      }
      return possibleMove;
      //return super.possibleMove(board, lastMove, c); //To change body of generated methods, choose Tools | Templates.
   }
   
   
   
   @Override
   public chess.PieceType type() {
      return PieceType.KING; 
   }

   @Override
   public void move(Board board, Move move) {
      if(move.from().x() == 4 && move.to().x() == 6) {
         castling(board, 7);
      } else if(move.from().x() == 4 && move.to().x() == 2) {
         castling(board, 0);
      } else {
         super.move(board, move);
      }
      moved = true;
   }


   
   
   
   
}

