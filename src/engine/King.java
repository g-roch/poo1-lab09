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

   private boolean canCastling(Board board, int rookX, int bishopX) {
      return !moved
              && board.havePiece(rookX, baseLine())
              && board.getPiece(rookX, baseLine()) instanceof Rook
              && !((Rook) board.getPiece(rookX, baseLine())).haveMoved()
              && !board.caseInCheck(new Case(bishopX, baseLine()), color())
              && !board.caseInCheck(new Case(4, baseLine()), color())
              ;
   }
   private boolean canCastlingShort(Board board) {
      return canCastling(board, 7, 5);
   }
   private boolean canCastlingLong(Board board) {
      return canCastling(board, 0, 3);
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
      if(canCastlingShort(board)) list.add(5, c.y());
      if(canCastlingLong(board))  list.add(2, c.y());
      ListCase possibleMove = new ListCase();
      for(Case destinationCase : list) {
         Board tmpBoard = board.clone();
         tmpBoard.getPiece(c).moveList(tmpBoard, lastMove, destinationCase);
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
      moved = true;
      super.move(board, move); //To change body of generated methods, choose Tools | Templates.
   }
   
   
}

