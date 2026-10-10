

//import java.util.HashSet;

import javax.swing.JLabel;


public class Engine {
    private board b;
    private String lastPiece = "";
    
    //private HashSet<Pair> allMoves;
    private int selectedX = -1;
    private int selectedY = -1;

    //
    private pieces pieces;

    public Engine(){
        this.b       = new board(this);
        this.pieces  = new pieces();
    }

   public void marked(int x, int y, JLabel[][] labels, String[][] list) {
        String temp = list[x][y].substring(3,list[x][y].length());
        this.pieces.removeHighlights(labels);
        boolean pos = false;
        if(temp.toCharArray()[0] == 'w') pos = true;

        if (!temp.equals("##")) {
            String piece = temp.substring(1,temp.length());
            this.pieces.highlight(piece, x, y, pos, labels, list);
            this.selectedX = x;
            this.selectedY = y;
        }else if (selectedX != -1 && !lastPiece.equals("##")) {
            this.pieces.move(selectedX, selectedY, x, y, labels, list);
            IO.println("Here3");
        } 

        IO.println("Here4");
    }

}
