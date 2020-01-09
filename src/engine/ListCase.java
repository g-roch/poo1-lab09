/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListCase extends LinkedList<Case> {
   //private List<Case> list = new LinkedList<>();
   //private Board board;
   /*public ListCase(Board board) {
      s
      list = new LinkedList<>();
      this.board = board;
   }
*/


   public void add(int x, int y) {
      add(new Case(x, y));
   }

   public void addIfValidCase(int x, int y) {
      if(Case.validCoord(x, y)) add(new Case(x, y));
   }
}
