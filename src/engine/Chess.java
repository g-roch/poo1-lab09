package engine;

import chess.ChessView;
import chess.PlayerColor;

/**
 * Controleur du jeu d'échecs
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class Chess implements chess.ChessController {

   /**
    * Objet utilisé pour gérer l'affichage à l'utilisateur.
    */
   private ChessView view;

   /**
    * Plateau de jeu
    */
   private Board board;

   /**
    * Joueur auquel c'est le tour de jouer
    */
   private PlayerColor player = PlayerColor.WHITE;

   @Override
   public void start(ChessView view) {
      this.view = view;
      view.startView();
      newGame();
   }

   @Override
   public boolean move(int fromX, int fromY, int toX, int toY) {

      Move move = new Move(new Case(fromX, fromY), new Case(toX, toY));
      
      if (!board.move(player, move)) {
         return false;
      }

      switchPlayer();
      pawnPromotion();

      boolean inCheck = board.kingInCheck(player);
      boolean cannotMove = board.countPossibleMoves(player) == 0;

      if (cannotMove && inCheck) {
         view.displayMessage("Check mate !");
      } else if(cannotMove) { // implicit !inCheck
         view.displayMessage("Pat !");
      } else if(inCheck) {
         view.displayMessage("Check !");
      } else if(player == PlayerColor.WHITE) {
         view.displayMessage("C'est aux blancs !");
      } else {
         view.displayMessage("C'est aux noirs !");
      }

      showBoard();
      return true;
   }

   @Override
   public void newGame() {
      board = new Board();
      // Nettoye le plateau
      showBoard();
   }

   /**
    * Change le joueur qui doit jouer
    */
   private void switchPlayer() {
      if(player == PlayerColor.WHITE)
         player = PlayerColor.BLACK;
      else
         player = PlayerColor.WHITE;
   }

   /**
    * Mise à jour du plateau de jeu pour l'affichage
    */
   private void showBoard() {
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            Case c = new Case(x, y);
            if(board.havePiece(c))
               view.putPiece(board.getPiece(c).getType(), 
                       board.getPiece(c).getColor(), x, y);
            else
               view.removePiece(x, y);
         }
      }
   }

   /**
    * Effectue la promotion des pions se trouvant sur la première et dernière ligne
    * @apiNote Cette fonction ne traite que le premier pion trouvé, dans une partie 
    * normale, il ne peut jamais avoir plus d'un pion sur la première ou dernière 
    * ligne
    */
   private void pawnPromotion() {
      for(int x = 0; x < 8; ++x) {
         Case c = new Case(x, 0);
         boolean promotion = false;
         promotion = board.getPiece(c) instanceof Pawn;
         if(!promotion) {
            c = new Case(x, 7);
            promotion = board.getPiece(c) instanceof Pawn;
         }
         if(promotion) {
            String type = askUser("Promotion", "Quelle pièce voulez-vous obtenir ?", 
                    new String[] {"Tour", "Cavalier", "Fou", "Dame"});
            switch (type) {
               case "Tour":
                  board.setPiece(c, new Rook(board.getPiece(c).getColor()));
                  break;
               case "Cavalier":
                  board.setPiece(c, new Knight(board.getPiece(c).getColor()));
                  break;
               case "Fou":
                  board.setPiece(c, new Bishop(board.getPiece(c).getColor()));
                  break;
               case "Dame":
                  board.setPiece(c, new Queen(board.getPiece(c).getColor()));
                  break;
            }
         }
      }
   }


   /**
    * Classe permettant la création d'un choix proposé à l'utilisateur
    */
   private class UserChoice implements ChessView.UserChoice {
      private String value;
      UserChoice(String value) {
         this.value = value;
      }

      /**
       * Methode requise par l'interface
       * @return La chaîne à afficher
       */
      @Override
      public String textValue() {
         return value;
      }

      /**
       * Méthode réelement utiliser pour exposer le texte à l'utilisateur
       * @return La chaîne à afficher
       */
      @Override
      public String toString() {
         return value;
      }
   }

   /**
    * Pose une question à choix à l'utilisateur
    * @param title Titre de la fenêtre
    * @param question Question à poser
    * @param answers Liste des réponses possibles
    * @return La réponse choisie par l'utilisateur
    */
   private String askUser(String title, String question, String[] answers) {
      ChessView.UserChoice userChoices[] = new ChessView.UserChoice[answers.length];
      
      for(int i = 0; i < answers.length; ++i) {
         userChoices[i] = new UserChoice(answers[i]);
      }
      return view.askUser("Promotion", "Quelle pièce voulez-vous obtenir ?", 
              userChoices).toString();
   }

}
