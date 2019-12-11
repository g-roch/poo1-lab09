/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import java.util.List;
import java.util.LinkedList;

/**
 * Classe permettant de référencer un case de l'échiquier
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
class Case {
   private int x;
   private int y;
   /**
    * @exception RuntimeException asdf
    */
   public Case(int x, int y) {
      if(x < 0 || x > 8) throw new RuntimeException("");
      if(y < 0 || y > 8) throw new RuntimeException("");
      this.x = x;
      this.y = y;
   }
   public int getX() { return x; }
   public int getY() { return y; }
}

/**
 *
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Chess implements chess.ChessController {
   /**
    * Classe permettant de manipuler une piece de l'échiquiers
    * @author Cassandre Wojciechowski
    * @author Gabriel Roch 
    */
   public abstract class Piece {
      protected PlayerColor color;
      protected int x; 
      protected int y; 

      private final String colorLetter[] = {"W", "B"};

      protected Piece(PlayerColor color, int x, int y) {
         this.color = color;
         this.x = x;
         this.y = y;
      }
      public boolean otherColorOrNull(int x, int y) {
         return x >= 0 && x < 8 && y >= 0 && y < 8 && 
            (chessboard[x][y] == null || chessboard.color != color);
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
      public String letterWithMove() {
         if(color == PlayerColor.WHITE)
            return "W" + letter();
         else
            return "B" + letter();
      }
      /**
       * Retourne la liste de case de destination autorisé pour cette pièce,
       * Indépendement de la mise en échecs de son propre roi.
       */
      public abstract List<Case> moveList();

      /**
       * Retourne la liste de movement autorisé par les régles.
       */
      public List<Case> possibleMove() { 
         throw new UnsupportedOperationException("Not supported yet."); 
      }
      /**
       * Affiche la pièce
       */
      public void putPiece() {
         view.putPiece(this.type(), color, 0, 0);         
      }
      public String toString() { 
         throw new UnsupportedOperationException("Not supported yet."); 
      }
      /**
       * Retourne le type de la piece pour l'affichage
       */
      public abstract chess.PieceType type();
   }

   /**
    * Roi
    */
   public class King extends Piece {
      public King(PlayerColor color, int x, int y) {
         super(color, x, y);
      }

      private boolean canCastlingShort() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
      private boolean canCastlingLong() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

      @Override
      public String letter() {
         return "K";
      }
      @Override
      public String letterWithMove() {
         String ret = super.letterWithMove();
         if(canCastlingLong()) ret = "+"+ret;
         if(canCastlingShort()) ret += "+";
         return ret;
      }
      @Override
      public List<Case> moveList() {
         List<Case> list = new LinkedList<>();
         if(otherColorOrNull(x-1, y-1)) list.add(new Case(x-1, y-1));
         if(x != 0)             list.add(new Case(x-1, y));
         if(x != 0 && y != 7)   list.add(new Case(x-1, y+1));
         if(y != 0)             list.add(new Case(x, y-1));
         if(y != 0)             list.add(new Case(x, y+1));
         if(x != 7 && y != 0)   list.add(new Case(x+1, y-1));
         if(x != 7)             list.add(new Case(x+1, y));
         if(x != 7 && y != 7)   list.add(new Case(x+1, y+1));
         if(canCastlingShort()) list.add(new Case(5, y));
         if(canCastlingLong())  list.add(new Case(2, y));
         return list;
      }
      @Override
      public chess.PieceType type() {
         return PieceType.KING; 
      }
   }

   public abstract class PieceLongRange extends Piece { // Piece long-distance
      protected PieceLongRange(PlayerColor color, int x, int y) {
         super(color, x, y);
      }
      @Override
      public List<Case> moveList() {
         List<Case> list = new LinkedList<>();
         if(canMoveTraight()) {
            for(int i = x; i >= 0; --i) {
               list.add(new Case(i, y));
               if(chessboard[i][y] != null) 
                  break;
            }
            for(int i = x; i < 8; ++i) {
               list.add(new Case(i, y));
               if(chessboard[i][y] != null) 
                  break;
            }
            for(int i = y; i >= 0; --i) {
               list.add(new Case(x, i));
               if(chessboard[y][i] != null) 
                  break;
            }
            for(int i = y; i < 8; ++i) {
               list.add(new Case(x, i));
               if(chessboard[y][i] != null) 
                  break;
            }
         }
         if(canMoveDiagonal()) {
            for(int i = x, j = y; i >= 0 && j >= 0; --i, --j) {
               list.add(new Case(i, j));
               if(chessboard[i][j] != null) 
                  break;
            }
            for(int i = x, j = y; i >= 0 && j < 8; --i, ++j) {
               list.add(new Case(i, j));
               if(chessboard[i][j] != null) 
                  break;
            }
            for(int i = x, j = y; i < 8 && j >= 0; ++i, --j) {
               list.add(new Case(i, j));
               if(chessboard[i][j] != null) 
                  break;
            }
            for(int i = x, j = y; i < 8 && j < 8; ++i, ++j) {
               list.add(new Case(i, j));
               if(chessboard[i][j] != null) 
                  break;
            }
         }
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
      /**
       * La piece a le droit de se déplecer en digonale
       */
      public abstract boolean canMoveDiagonal();
      /**
       * La piece a le droit de se déplecer en ligne droite
       */
      public abstract boolean canMoveTraight();
   }

   /**
    * Dame
    */
   public class Queen extends PieceLongRange {
      public Queen(PlayerColor color, int x, int y) {
         super(color, x, y);
      }

      @Override
      public boolean canMoveDiagonal() {
         return true;
      }
      @Override
      public boolean canMoveTraight() {
         return true;
      }
      @Override
      public String letter() {
         return "Q";
      }
      @Override
      public chess.PieceType type() {
         return PieceType.QUEEN; 
      }
   }

   /**
    * Tour
    */
   public class Rook extends PieceLongRange {
      public Rook(PlayerColor color, int x, int y) {
         super(color, x, y);
      }

      @Override
      public boolean canMoveDiagonal() {
         return false;
      }
      @Override
      public boolean canMoveTraight() {
         return true;
      }
      @Override
      public String letter() {
         return "R";
      }
      @Override
      public chess.PieceType type() {
         return PieceType.ROOK; 
      }
   }

   /**
    * Fou
    */
   public class Bishop extends PieceLongRange {
      public Bishop(PlayerColor color, int x, int y) {
         super(color, x, y);
      }

      @Override
      public boolean canMoveDiagonal() {
         return true;
      }
      @Override
      public boolean canMoveTraight() {
         return false;
      }
      @Override
      public String letter() {
         return "B";
      }
      @Override
      public chess.PieceType type() {
         return PieceType.BISHOP; 
      }
   }

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
      public List<Case> moveList() {
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

   /**
    * Pion
    */
   public class Pawn extends Piece {
      public Pawn(PlayerColor color, int x, int y) {
         super(color, x, y);
      }

      private int direction() {
         if(color == PlayerColor.WHITE) return +1;
         else return -1;
      }

      @Override
      public String letter() {
         return ""; // Il n'y a pas de lettre en notation algébrique
      }
      @Override
      public String letterWithMove() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
      @Override
      public List<Case> moveList() {
         List<Case> list = new LinkedList<>();
         int baseLine;
         if(direction() == 1)
            baseLine = 6;
         else
            baseLine = 1;

         if(chessboard[x][y+direction()] == null)
            list.add(new Case(x, y+direction()));
         // Avance de 2 au début
         if(y == baseLine && chessboard[x][y+direction()] == null
               && chessboard[x][y+direction()+direction()] == null)
            list.add(new Case(x, y+direction()+direction()));

         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
      @Override
      public chess.PieceType type() {
         return PieceType.PAWN; 
      }
   }



   /**
    * Objet utiliser pour géré l'affichage à l'utilisateur.
    */
   protected ChessView view;

   protected Piece[][] chessboard;

   /*
Besoin:
- Savoir ou est quel piece.
- Savoir quel mouvement est autorisé pour quel piece
- Vérifier les "Echec" "Echec et mat"
- (( Vérifier les PAT ))
- Savoir quel joueur doit jouer
*/

   @Override
   public void start(ChessView view) {
      this.view = view;
      view.startView();
      newGame();
   }

   @Override
   public boolean move(int fromX, int fromY, int toX, int toY) {
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      return false;
   }

   @Override
   public void newGame() {
      chessboard = new Piece[8][8];
      // Nettoye le plateau
      for(int i = 0; i < 8; ++i) {
         for(int j = 0; j < 8; ++j) {
            view.removePiece(i, j);
         }
      }

      view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 0, 0);
      //view.displayMessage("Bonjour joueur");
      //view.removePiece(7, 7);
      //System.err.println("Ask user");
      //view.askUser("Test ask", "Maquest", new ChessView.UserChoice() {
      //	@Override
      //	public String textValue() {
      //		return "A";
      //	}
      //	public String toString() {
      //		return "a";
      //	}
      //}, new ChessView.UserChoice() {
      //	@Override
      //	public String textValue() {
      //		return "B";
      //	}
      //	public String toString() {
      //		return "b";
      //	}
      //});
   }

}
