/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import java.util.List;

class Case {
   public int x;
   public int y;
}

/**
 *
 * @author cassa
 */
public class Chess implements chess.ChessController {
   public abstract class Piece {
      protected PlayerColor color;
      private int x; 
      private int y; 

      public Piece(PlayerColor color, int x, int y) {
         this.color = color;
         this.x = x;
         this.y = y;
      }
      public abstract PieceType pieceType();
      public List<Case> possibleMove() { throw new UnsupportedOperationException("Not supported yet."); }
      public abstract boolean adversaryCheck();
      public abstract List<Case> moveList();
      public String toString() { throw new UnsupportedOperationException("Not supported yet."); }
      public abstract String letter();
      public abstract String letterWithMove();
      public abstract chess.PieceType type();
      public void putPiece() {
         view.putPiece(this.pieceType(), color, 0, 0);         
      }
   }
   public class Rook extends Piece {
      @Override
      public PieceType pieceType() {
         return PieceType.ROOK; 
      }

      @Override
      public boolean adversaryCheck() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
   }
        
        

	/**
	 * Objet utiliser pour géré l'affichage à l'utilisateur.
	 */
	protected	ChessView view;

	protected GenericPiece[][] chessboard;

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
		chessboard = new GenericPiece[8][8];
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
