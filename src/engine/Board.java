/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PlayerColor;
import engine.Piece;
import engine.Move;
import java.util.List;

public class Board implements Cloneable {
   private Piece[][] board;

   public Board() {
      board = new Piece[8][8];
      board[0][7] = new Rook(PlayerColor.BLACK);
      board[1][7] = new Knight(PlayerColor.BLACK);
      board[2][7] = new Bishop(PlayerColor.BLACK);
      board[3][7] = new Queen(PlayerColor.BLACK);
      board[4][7] = new King(PlayerColor.BLACK);
      board[5][7] = new Bishop(PlayerColor.BLACK);
      board[6][7] = new Knight(PlayerColor.BLACK);
      board[7][7] = new Rook(PlayerColor.BLACK);
      for (int i = 0; i < 8; ++i)
         board[i][6] = new Pawn(PlayerColor.BLACK);
      
      board[0][0] = new Rook(PlayerColor.WHITE);
      board[1][0] = new Knight(PlayerColor.WHITE);
      board[2][0] = new Bishop(PlayerColor.WHITE);
      board[3][0] = new Queen(PlayerColor.WHITE);
      board[4][0] = new King(PlayerColor.WHITE);
      board[5][0] = new Bishop(PlayerColor.WHITE);
      board[6][0] = new Knight(PlayerColor.WHITE);
      board[7][0] = new Rook(PlayerColor.WHITE);
      for (int i = 0; i < 8; ++i)
         board[i][1] = new Pawn(PlayerColor.WHITE);
   }
   
   public Move move(Move lastMove, PlayerColor color, Move move) {
      
      // Vérifie que la pièce à déplacer appartient bien au joueur
      if(!havePiece(move.from()) || getPiece(move.from()).color() != color)
         return null;

      // Vérifie que la destination se trouve dans les destination possible de la piece
      ListCase listPossibleMove = getPiece(move.from()).possibleMove(this, lastMove, move.from());
      boolean possibleMove = false;
      for(Case c : listPossibleMove) 
         if(c.equals(move.to()))
            possibleMove = true;

      if(!possibleMove) 
         return null;

      // Si la destination est occupée, elle doit être de la couleur adverse
      if(havePiece(move.to()) && getPiece(move.to()).color() == color)
         return null;

      getPiece(move.from()).move(this, move);
      return move;

      //throw new UnsupportedOperationException("Not supported yet.");
   }

   boolean havePiece(int x, int y) {
      return ListCase.validCoord(x, y) && board[x][y] != null;
   }
   boolean havePiece(Case c) {
      return havePiece(c.x(), c.y());
   }
   
   Piece getPiece(int x, int y) {
      return board[x][y];
   }
   Piece getPiece(Case c) {
      return getPiece(c.x(), c.y());
   }
   void setPiece(int x, int y, Piece p) {
      board[x][y] = p;
   }
   void setPiece(Case c, Piece p) {
      setPiece(c.x(), c.y(), p);
   }
   
   
   
   boolean kingInCheck(PlayerColor color) {
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            if(havePiece(x, y) 
                    && getPiece(x, y).color() == color
                    && getPiece(x, y) instanceof King
                    ) {
               return caseInCheck(new Case(x, y), color);
            }
         }
      }
      return true;
   }
   boolean caseInCheck(Case c, PlayerColor color) { // couleur du roi
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            if(havePiece(x, y)
                    && getPiece(x, y).color() != color
                    && getPiece(x, y).adversaryCheck(this, c)
            ) {
               return true;
            }
         }
      }
      return false;
   }

   @Override
   public Board clone() {
      Board board = null;
      try {
         board = (Board) super.clone();
      } catch (CloneNotSupportedException e) { }
      return board;
   }
   
   
   

}
