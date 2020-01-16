package engine;

import chess.PieceType;
import chess.PlayerColor;

/**
 * Roi
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
public class King extends Piece {

   /**
    * Indique si le roi a déjà effectué un mouvement
    */
   private boolean moved;
   
   public King(PlayerColor color) {
      super(color);
      moved = false;
   }

   /**
    * Determine si le roque avec la tour rookX est possible
    * @param board Plateau de jeux
    * @param rookX Tour avec laquel roquer
    * @return true si le roque est permis
    */
   private boolean canCastling(Board board, int direction) {
      Case rookCase = new Case(direction == -1 ? 0 : 7, baseLine());
      Case neighbourCase = new Case(4+direction, baseLine());
      Case secondNeighbourCase = new Case(4+2*direction, baseLine());
      Case knightCase = new Case(rookCase.getX() - direction, baseLine());
      Case kingCase = new Case(4, baseLine());
      return !moved
              && board.havePiece(rookCase)
              && board.getPiece(rookCase) instanceof Rook
              && !((Rook) board.getPiece(rookCase)).haveMoved()
              && !board.havePiece(knightCase)
              && !board.havePiece(secondNeighbourCase)
              && !board.havePiece(neighbourCase)
              && !board.caseTargeted(neighbourCase, getColor()) // case de transit pas en échecs
              && !board.caseTargeted(kingCase, getColor()) // roi pas en échecs
              // le test que la case de destionation n'est pas en échecs est fait lors du déplacement
              ;
   }
   
   /**
    * Determine si le petit roque est possible
    * @param board Plateau de jeu
    * @return true si le roque est permis
    */
   private boolean canCastlingShort(Board board) {
      return canCastling(board, 1);
   }
   
   /**
    * Determine si le grand roque est possible
    * @param board Plateau de jeu
    * @return true si le roque est permis
    */
   private boolean canCastlingLong(Board board) {
      return canCastling(board, -1);
   }

   /**
    * Détermine la ligne de base du roi
    * @return 0 ou 7
    */
   private int baseLine() {
      if(getColor() == PlayerColor.WHITE)
         return 0;
      else
         return 7;
   }

   /**
    * Effectue un Roque
    * @param board Plateau de jeu
    * @param rookX Tour avec laquel roquer
    */
   private void castling(Board board, int rookX) {
      int direction = (rookX == 0 ? -1 : 1);
      Case rookCase = new Case(rookX, baseLine());
      Case neighbourCase = new Case(4+direction, baseLine());
      Case secondNeighbourCase = new Case(4+2*direction, baseLine());
      Case kingCase = new Case(4, baseLine());
      board.setPiece(neighbourCase, board.getPiece(rookCase));
      board.setPiece(secondNeighbourCase, board.getPiece(kingCase));
      board.setPiece(rookCase, null);
      board.setPiece(kingCase, null);
   }

   @Override
   protected ListCase moveList(Board board, Case c) {
      ListCase list = new ListCase();

      list.addIfValidCase(c.getX()-1, c.getY()-1);
      list.addIfValidCase(c.getX()-1, c.getY()  );
      list.addIfValidCase(c.getX()-1, c.getY()+1);
      list.addIfValidCase(c.getX()  , c.getY()-1);
      list.addIfValidCase(c.getX()  , c.getY()+1);
      list.addIfValidCase(c.getX()+1, c.getY()-1);
      list.addIfValidCase(c.getX()+1, c.getY()  );
      list.addIfValidCase(c.getX()+1, c.getY()+1);

      // On ne peut pas ajouter les Roques ici, car celà fait rentré dans un appel de
      // methode recursif infini

      return list;
   }

   @Override
   public ListCase possibleMove(Board board, Case c) {
      ListCase list = moveList(board, c);
      for(int direction : new int[] {-1, +1}) {
         if (canCastling(board, direction)) {
            list.add(4+2*direction, c.getY());
         }
      }
      ListCase possibleMove = new ListCase();
      for(Case destinationCase : list) {
         Board tmpBoard = new Board(board);
         tmpBoard.getPiece(c).move(tmpBoard, new Move(c, destinationCase));
         if(!tmpBoard.kingInCheck(getColor())) {
            possibleMove.add(destinationCase);
         }
      }
      return possibleMove;
   }
      
   @Override
   public PieceType getType() {
      return PieceType.KING; 
   }

   @Override
   public boolean move(Board board, Move move) {
      if(move.getFrom().getX() == 4 && move.getTo().getX() == 6) {
         castling(board, 7);
      } else if(move.getFrom().getX() == 4 && move.getTo().getX() == 2) {
         castling(board, 0);
      } else {
         super.move(board, move);
      }
      moved = true;
      return true;
   }
   
}

