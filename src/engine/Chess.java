/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chess.ChessView;

/**
 *
 * @author cassa
 */
public class Chess implements chess.ChessController {

   @Override
   public void start(ChessView view) {
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   view.startView();
   }

   @Override
   public boolean move(int fromX, int fromY, int toX, int toY) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void newGame() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   
}
