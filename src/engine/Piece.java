/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import chess.PieceType;
import chess.PlayerColor;
import java.util.List;

/**
 * Classe permettant de manipuler une piece de l'échiquiers
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public abstract class Piece implements Cloneable {
   private PlayerColor color;
   //protected int x; 
   //protected int y; 

   //private final String colorLetter[] = {"W", "B"};

   protected Piece(PlayerColor color/*, int x, int y*/) {
      this.color = color;
      //this.x = x;
      //this.y = y;
   }

   public PlayerColor color() {
      return color;
   }


   /**
    * Indique si cette pièce mets en échec l'adversaire
    */
   public boolean adversaryCheck(Board board, Case c) {
      ListCase moveList = moveList(board, null, c);
      for(Case destinationCase : moveList) {
         if(destinationCase.equals(c))
            return true;
      }
      return false;
   }

   /**
    * Retourne une lettre représentant le type de la piece
    */
   public abstract String letter();

   /**
    * Retourne une chaine de caractére représentant les mouvement permit par la piece
    * (Type de piece et s'il peut prendre en passant / Roqué)
    * @see #letter()
    */
   protected String letterWithMove() {
      if(color == PlayerColor.WHITE)
         return "W" + letter();
      else
         return "B" + letter();
   }

   /**
    * Retourne la liste de case de destination autorisé pour cette pièce,
    * Indépendement de la mise en échecs de son propre roi.
    */
   protected abstract ListCase moveList(Board board, Move lastMove, Case c);

   /**
    * Retourne la liste de movement autorisé par les régles.
    */
   public ListCase possibleMove(Board board, Move lastMove, Case c) { 
      //throw new UnsupportedOperationException("Not supported yet."); 
      // TODO
      ListCase moveList = moveList(board, lastMove, c);
      ListCase possibleMove = new ListCase();
      for(Case destinationCase : moveList) {
         Board tmpBoard = board.clone();
         tmpBoard.getPiece(c).moveList(tmpBoard, lastMove, destinationCase);
         if(!tmpBoard.kingInCheck(color)) {
            possibleMove.add(destinationCase);
         }
      }
      return possibleMove;
   }

   ///**
   // * Affiche la pièce
   // */
   //public void putPiece() { view.putPiece(this.type(), color, 0, 0);         }
   /**
    * Retourne le type de la piece pour l'affichage
    */
   public abstract PieceType type();

   /**
    * Déplace la pièce (Ne vérifie pas que le mouvement est valide)
    */
   public void move(Board board, Move move) {
      board.setPiece(move.to(), this);
      board.setPiece(move.from(), null);
   }

   @Override
   protected Piece clone() {
      Piece piece = null;
      try {
         piece = (Piece) super.clone();
      } catch (CloneNotSupportedException e) { }
      return piece;
   }
   
   
}
