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
 *
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Chess implements chess.ChessController {

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
