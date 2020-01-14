package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Classe permettant de manipuler une piece de l'échiquiers
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public abstract class Piece implements Cloneable {
   private PlayerColor color;

   protected Piece(PlayerColor color/*, int x, int y*/) {
      this.color = color;
   }

   public PlayerColor color() {
      return color;
   }


   /**
    * Indique si cette pièce mets en échec la case kingCase
    */
   public boolean adversaryCheck(Board board, Case kingCase, Case selfCase) {
      ListCase moveList = moveList(board, selfCase);
      for(Case destinationCase : moveList) {
         if(destinationCase.equals(kingCase))
            return true;
      }
      return false;
   }

   /**
    * Retourne la liste de case de destination autorisé pour cette pièce,
    * Indépendement de la mise en échecs de son propre roi.
    */
   protected abstract ListCase moveList(Board board, Case c);

   /**
    * Retourne la liste de movement autorisé par les régles.
    */
   public ListCase possibleMove(Board board, Case c) {
      ListCase moveList = moveList(board, c);
      ListCase possibleMove = new ListCase();
      for(Case destinationCase : moveList) {
         Board tmpBoard = new Board(board);
         tmpBoard.getPiece(c).move(tmpBoard, new Move(c, destinationCase));
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
   public boolean move(Board board, Move move) {
      board.setPiece(move.to(), this);
      board.setPiece(move.from(), null);
      return true;
   }

   @Override
   public Piece clone() {
      Piece piece = null;
      try {
         piece = (Piece) super.clone();
      } catch (CloneNotSupportedException e) { }
      return piece;
   }
   
   
}
