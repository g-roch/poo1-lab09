/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chess.ChessView;
import chess.PlayerColor;

/**
 *
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Chess implements chess.ChessController {

   /**
    * Objet utiliser pour géré l'affichage à l'utilisateur.
    */
   protected ChessView view;

   private Board board;
   
   private Move lastMove;
   
   private PlayerColor player = PlayerColor.WHITE;

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
      Move m = new Move(new Case(fromX, fromY), new Case(toX, toY));
      if(board.havePiece(m.from())) player = board.getPiece(m.from()).color();
      Move l = board.move(lastMove, player, m);
      if(l == null) 
         return false;

      lastMove = l;
      
      checkPromotion();
      
      showBoard();
      
      return true;
   }

   private void showBoard() {
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            if(board.havePiece(x, y))
               view.putPiece(board.getPiece(x, y).type(), board.getPiece(x, y).color(), x, y);
            else
               view.removePiece(x, y);
         }
      }
   }
   @Override
   public void newGame() {
      board = new Board();
      // Nettoye le plateau
      showBoard();

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

   private void checkPromotion() {
      for(int x = 0; x < 8; ++x) {
         int y = 0;
         boolean promotion = false;
         promotion = board.getPiece(x, y) instanceof Pawn;
         if(!promotion) {
            y = 7;
            promotion = board.getPiece(x, y) instanceof Pawn;
         }
         if(promotion) {
            askUser(view, "Promotion", "Quelle pièce voulez-vous obtenir ?", new String[] {"Tour", "Cavalier", "Fou", "Dame"});
            
         }
            
      }
//      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   
   private class UserChoice implements ChessView.UserChoice {
      private String value;
      UserChoice(String value) {
         this.value = value;
      }
      @Override
      public String textValue() {
         return value;
      }
      @Override
      public String toString() {
         return value;
      }
      
   }

   private void askUser(ChessView view, String titre, String question, String[] answers) {
      ChessView.UserChoice t[] = new ChessView.UserChoice[answers.length];
      
      for(int i = 0; i < answers.length; ++i) {
         t[i] = new UserChoice(answers[i]);
      }
      view.askUser("Promotion", "Quelle pièce voulez-vous obtenir ?", t);
   }

}
