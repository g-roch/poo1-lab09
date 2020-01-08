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
public abstract class Piece {
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
   public boolean adversaryCheck() {
      throw new UnsupportedOperationException("Not supported yet."); 
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
      return moveList(board, lastMove, c);
   }

   ///**
   // * Affiche la pièce
   // */
   //public void putPiece() { view.putPiece(this.type(), color, 0, 0);         }
   /**
    * Retourne le type de la piece pour l'affichage
    */
   public abstract PieceType type();
}
