package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Classe permettant de manipuler une piece de l'échiquier
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */

public abstract class Piece implements Cloneable {
   
   private PlayerColor color;

   protected Piece(PlayerColor color) {
      this.color = color;
   }

   public PlayerColor getColor() {
      return color;
   }

   /**
    * Indique si cette pièce met en échec la case kingCase
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
    * Retourne la liste de cases de destination autorisées pour cette pièce,
    * indépendamment de la mise en échec de son propre roi.
    */
   protected abstract ListCase moveList(Board board, Case c);

   /**
    * Retourne la liste de mouvements autorisés par les régles.
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

   /**
    * Déplace la pièce (ne vérifie pas que le mouvement est valide)
    */
   public boolean move(Board board, Move move) {
      board.setPiece(move.getTo(), this);
      board.setPiece(move.getFrom(), null);
      return true;
   }

   /**
    * Retourne le type de la pièce pour l'affichage
    */
   public abstract PieceType getType();

   @Override
   public Piece clone() {
      Piece piece = null;
      try {
         piece = (Piece) super.clone();
      } catch (CloneNotSupportedException e) { }
      return piece;
   }
   
}
