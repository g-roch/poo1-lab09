/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import engine.Chess;

/**
 *
 * @author cassa
 */
public class ChessGame {

   public static void main(String[] args) {
      // 1. Création du contrôleur pour gérer le jeu d’échec
      ChessController controller = new Chess(); // Instancier un ChessController
      // 2. Création de la vue
      ChessView view = new GUIView(controller); // mode GUI
      // = new ConsoleView(controller); // mode Console
      // 3 . Lancement du programme.
      controller.start(view);
   }
}
