// The chess Engine 
import javax.swing.JLabel;

public class Engine {
    @SuppressWarnings("unused")
	private board b;
    
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
        boolean pos = false;
        if(temp.toCharArray()[0] == 'w') pos = true;

        if (selectedX != -1 && selectedY != -1) {
            pieces.removeHighlights(labels);
        }

        if (selectedX != -1 && selectedY != -1 && !list[selectedX][selectedY].substring(3).equals("##")) {
            this.pieces.move(selectedX, selectedY, x, y, labels, list);
            selectedX = -1;
            selectedY = -1;
        } 

        if (!temp.equals("##")) {
            String piece = temp.substring(1,temp.length());
            this.pieces.highlight(piece, x, y, pos, labels, list);
            this.selectedX = x;
            this.selectedY = y;
            
        }
        
    }

}
