/*
 * vim: ts=3 softtabstop=3 shiftwidth=3 expandtab
 */

package engine;

/**
 * Classe permettant de référencer un case de l'échiquier
 * @author Cassandre Wojciechowski
 * @author Gabriel Roch 
 */
class Case {
   private int x;
   private int y;
   /**
    * @exception RuntimeException asdf
    */
   public Case(int x, int y) {
      if(x < 0 || x > 8) throw new RuntimeException("");
      if(y < 0 || y > 8) throw new RuntimeException("");
      this.x = x;
      this.y = y;
   }

   public int x() { return x; }
   public int y() { return y; }

	 public boolean equals(Object o) {
		 return o == this ||
			 o != null && o.getClass() == getClass() &&
			 ((Case) o).x == x && ((Case) o).y == y;
	 }
}

