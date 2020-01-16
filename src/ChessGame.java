import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import chess.views.console.ConsoleView;
import engine.Chess;

/**
 * Classe main
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class ChessGame {

   public static void main(String[] args) {
      
      String mode = "gui";
      if (args.length > 0) mode = args[0];
      System.out.println(mode);

      // 1. Création du contrôleur pour gérer le jeu d’échec
      ChessController controller = new Chess(); // Instancier un ChessController
      
      // 2. Création de la vue
      ChessView view;
      if (mode.equals("text"))
         view = new ConsoleView(controller);
      else
         view = new GUIView(controller);
      
      // ChessView view = new GUIView(controller); // mode GUI
      // ChessView view = new ConsoleView(controller); // mode Console
      
      // 3 . Lancement du programme.
      controller.start(view);
   }
}
