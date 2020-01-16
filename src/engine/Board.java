package engine;

import chess.PlayerColor;

import java.util.Iterator;

/**
 * Plateau de jeu
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */

public class Board {

   /**
    * Plateau de jeu
    */
   private Piece[][] gameBoard;

   /**
    * Dernier mouvement effectué sur le plateau de jeu
    */
   private Move lastMove;

   public Move getLastMove() {
      return lastMove;
   }
   
   /**
    * Initialisation et placement des pièces sur le plateau de jeu
    */
   public Board() {
      // Les pièces noires en haut du plateau
      gameBoard = new Piece[8][8];
      gameBoard[0][7] = new Rook(PlayerColor.BLACK);
      gameBoard[1][7] = new Knight(PlayerColor.BLACK);
      gameBoard[2][7] = new Bishop(PlayerColor.BLACK);
      gameBoard[3][7] = new Queen(PlayerColor.BLACK);
      gameBoard[4][7] = new King(PlayerColor.BLACK);
      gameBoard[5][7] = new Bishop(PlayerColor.BLACK);
      gameBoard[6][7] = new Knight(PlayerColor.BLACK);
      gameBoard[7][7] = new Rook(PlayerColor.BLACK);
      for (int i = 0; i < 8; ++i)
         gameBoard[i][6] = new Pawn(PlayerColor.BLACK);
      
      // Les pièces blanches en bas du plateau
      gameBoard[0][0] = new Rook(PlayerColor.WHITE);
      gameBoard[1][0] = new Knight(PlayerColor.WHITE);
      gameBoard[2][0] = new Bishop(PlayerColor.WHITE);
      gameBoard[3][0] = new Queen(PlayerColor.WHITE);
      gameBoard[4][0] = new King(PlayerColor.WHITE);
      gameBoard[5][0] = new Bishop(PlayerColor.WHITE);
      gameBoard[6][0] = new Knight(PlayerColor.WHITE);
      gameBoard[7][0] = new Rook(PlayerColor.WHITE);
      for (int i = 0; i < 8; ++i)
         gameBoard[i][1] = new Pawn(PlayerColor.WHITE);
   }

   /**
    * Contructeur par copie
    * @param board Board à copier
    */
   public Board(Board board) {
      gameBoard = new Piece[8][8];
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            if(board.gameBoard[x][y] == null)
               gameBoard[x][y] = null;
            else
               gameBoard[x][y] = board.gameBoard[x][y].clone();
         }
      }
   }

   /**
    * @param x Coordonnée horizontale
    * @param y Coordonnée verticale
    * @return true si les coordonnées sont valides
    */
   public static boolean validCoord(int x, int y) {
      return x >= 0 && x < 8 && y >= 0 && y < 8;
   }

   /**
    * Effectue un mouvement
    * @param color Couleur du joueur qui joue
    * @param move Mouvement à effectuer
    * @return Le mouvement effectué ou null si le mouvement était impossible ou 
    *          interdit
    */
   public boolean move(PlayerColor color, Move move) {
      
      // Vérifie que la pièce à déplacer appartient bien au joueur
      if(!havePiece(move.getFrom()) || getPiece(move.getFrom()).getColor() != color)
         return false;

      // Vérifie que la destination se trouve dans les destinations possibles de la 
      // pièce
      ListCase listPossibleMove = 
              getPiece(move.getFrom()).possibleMove(this, move.getFrom());
      boolean possibleMove = false;
      for(Case c : listPossibleMove) {
         if (c.equals(move.getTo()))
            possibleMove = true;
      }

      // Si le mouvement n'est pas possible ou interdit
      if(!possibleMove) 
         return false;

      // Si la destination est occupée, elle doit être de la couleur adverse
      if(havePiece(move.getTo()) && getPiece(move.getTo()).getColor() == color)
         return false;

      if(getPiece(move.getFrom()).move(this, move)) {
         lastMove = move;
         return true;
      } else {
         return false;
      }
   }

   /**
    * Détermine s'il y a une pièce sur une case donnée
    * @param c Case à tester
    * @return true s'il y a une pièce sur la case
    */
   public boolean havePiece(Case c) {
      return getPiece(c) != null;
   }

   /**
    * Récupere la pièce d'une case
    * @param c Case à tester
    * @return La piece de la case ou null si la case est vide.
    */
   public Piece getPiece(Case c) {
      return gameBoard[c.getX()][c.getY()];
   }

   /**
    * Pose une pièce sur l'échiquier
    * @param c Case sur laquelle la piece sera posée
    * @param p Piece à placer
    */
   public void setPiece(Case c, Piece p) {
      gameBoard[c.getX()][c.getY()] = p;
   }

   /**
    * Détermine si un roi est en échec
    * @param color La couleur du roi à tester
    * @return true si le roi de couleur color est en échec
    */
   public boolean kingInCheck(PlayerColor color) {
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            Case c = new Case(x, y);
            if(havePiece(c)
                    && getPiece(c).getColor() == color
                    && getPiece(c) instanceof King
                    ) {
               return caseTargeted(new Case(x, y), color);
            }
         }
      }
      return true;
   }


   /**
    * Teste si une case est attaquée par une autre pièce
    * @param c Case à tester
    * @param color La couleur du roi à tester
    * @return true si la case est attaquée
    */
   public boolean caseTargeted(Case c, PlayerColor color) { // couleur du roi
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            Case selfCase = new Case(x, y);
            if(havePiece(selfCase)
                    && getPiece(selfCase).getColor() != color
                    && getPiece(selfCase).adversaryCheck(this, c, selfCase)
            ) {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Compte le nombre de mouvements possibles pour un utilisateur
    * @param player Joueur à tester
    * @return Le nombre de mouvements possibles pour le joueur player
    */
   public int countPossibleMoves(PlayerColor player){
      int countMoves = 0;
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            Case c = new Case(x, y);
            if(havePiece(c) && getPiece(c).getColor() == player) {
               countMoves += getPiece(c).possibleMove(this, c).size();
            }
         }
      }
      return countMoves;
   }
}
