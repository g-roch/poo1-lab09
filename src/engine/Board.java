package engine;

import chess.PlayerColor;

public class Board {

   /**
    * Plateu de jeux
    */
   private Piece[][] gameBoard;

   /**
    * Dernier mouvement effectué sur le plateau de jeu
    */
   private Move lastMove;

   public Move getLastMove() {
      return lastMove;
   }

   public Board() {
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
    * @param b Board à copier
    */
   public Board(Board b) {
      gameBoard = new Piece[8][8];
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            if(b.gameBoard[x][y] == null)
               gameBoard[x][y] = null;
            else
               gameBoard[x][y] = b.gameBoard[x][y].clone();
         }
      }
   }

   /**
    * @param x Coordonnée horizontal
    * @param y Coordonnée vertical
    * @return true si les coordonée sont valid
    */
   public static boolean validCoord(int x, int y) {
      return x >= 0 && x < 8 && y >= 0 && y < 8;
   }


   /**
    * Effectue un mouvement
    * @param color Couleur du joueur qui joue
    * @param move Mouvement à effectué
    * @return Le mouvement effectué ou null si le mouvement était impossible ou interdit
    */
   public boolean move(PlayerColor color, Move move) {
      
      // Vérifie que la pièce à déplacer appartient bien au joueur
      if(!havePiece(move.from()) || getPiece(move.from()).color() != color)
         return false;

      
      // Vérifie que la destination se trouve dans les destination possible de la piece
      ListCase listPossibleMove = getPiece(move.from()).possibleMove(this, move.from());
      boolean possibleMove = false;
      for(Case c : listPossibleMove) {
         if (c.equals(move.to()))
            possibleMove = true;
      }

      // Si le mouvement n'est pas possible ou interdit
      if(!possibleMove) 
         return false;

      // Si la destination est occupée, elle doit être de la couleur adverse
      if(havePiece(move.to()) && getPiece(move.to()).color() == color)
         return false;

      if(getPiece(move.from()).move(this, move)) {
         lastMove = move;
         return true;
      } else {
         return false;
      }
   }

   /**
    * Determine s'il y a une piece sur une case donnée
    * @param c Case à tester
    * @return true s'il y a une piece sur la case
    */
   boolean havePiece(Case c) {
      return getPiece(c) != null;
   }

   /**
    * Récupere la piece d'une case
    * @param c Case à tester
    * @return La piece de la case ou null si la case est vide.
    */
   Piece getPiece(Case c) {
      return gameBoard[c.getX()][c.getY()];
   }

   /**
    * Pose un piece sur l'échiquier
    * @param c Case sur laquel la piece sera poser
    * @param p Piece à placer
    */
   void setPiece(Case c, Piece p) {
      gameBoard[c.getX()][c.getY()] = p;
   }


   /**
    * Détermine si une roi est en échec
    * @param color La couleur du roi à tester
    * @return true si le roi de couleur color est en échecs
    */
   boolean kingInCheck(PlayerColor color) {
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            Case c = new Case(x, y);
            if(havePiece(c)
                    && getPiece(c).color() == color
                    && getPiece(c) instanceof King
                    ) {
               return caseInCheck(new Case(x, y), color);
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
   boolean caseInCheck(Case c, PlayerColor color) { // couleur du roi
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            Case selfCase = new Case(x, y);
            if(havePiece(selfCase)
                    && getPiece(selfCase).color() != color
                    && getPiece(selfCase).adversaryCheck(this, c, selfCase)
            ) {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Compte le nombre de mouvement possible pour un utilisateur
    * @param player Joueur à tester
    * @return Le nombre de mouvement possible pour le joueur player
    */
   int countPossibleMoves(PlayerColor player){
      int countMoves = 0;
      for(int x = 0; x < 8; ++x) {
         for(int y = 0; y < 8; ++y) {
            Case c = new Case(x, y);
            if(havePiece(c) && getPiece(c).color() == player) {
               countMoves += getPiece(c).possibleMove(this, c).size();
            }
         }
      }
      return countMoves;
   }

}
