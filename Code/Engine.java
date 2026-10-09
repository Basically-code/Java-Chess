import chessPiece.*;

//import java.util.HashSet;

import javax.swing.JLabel;

public class Engine {
    private board b;
    private String lastPiece = "";
    private int pressCount = 0;
    //private HashSet<Pair> allMoves;
    private int selectedX = -1;
    private int selectedY = -1;

    //
    private pawn pawns;
    private knight knights;
    private king kings;
    private bishop bishops;
    private rook rooks;
    private queen queens;

    public Engine(){
        this.b       = new board(this);
        this.pawns   = new pawn();
        this.knights = new knight();
        this.kings   = new king();
        this.bishops = new bishop();
        this.rooks   = new rook();
        this.queens  = new queen();
    }

   public void marked(int x, int y, JLabel[][] labels, String[][] list) {
        String temp = list[x][y];

        if (!temp.substring(3).equals("##")) {
            // select a piece
            highlight(x, y, labels, temp, list);
            this.selectedX = x;
            this.selectedY = y;
            return;
        }

        // only move if a piece was selected
        if (selectedX != -1 && !lastPiece.isEmpty()) {
            move(x, y, labels, list);
            IO.println("Here3");
        }              // only if a piece was selected

        removeHighlights(labels);
        selectedX = -1;
        selectedY = -1;
        lastPiece = "";
        IO.println("Here4");
    }


    public record Pair(int x, int y) {}

    private void highlight(int x, int y, JLabel[][] label, String piece, String[][] list){
        boolean pos = false;
        if(piece.substring(3,4).equals("w")) {pos = true;}
        
        if (piece.substring(4,(piece.length())).equals("k"))        {this.kings.highlight(x,y,pos,label,list); this.lastPiece = "k";}
        else if (piece.substring(4,(piece.length())).equals("kn"))  {this.knights.highlight(x,y,pos,label,list); this.lastPiece = "kn";}
        else if (piece.substring(4,(piece.length())).equals("q"))   {this.queens.highlight(x,y,pos,label,list); this.lastPiece = "q";}
        else if (piece.substring(4,(piece.length())).equals("b"))   {this.bishops.highlight(x,y,pos,label,list); this.lastPiece = "b";}
        else if (piece.substring(4,(piece.length())).equals("r"))   {this.rooks.highlight(x,y,pos,label,list); this.lastPiece = "r";}
        else if (piece.substring(4,(piece.length())).equals("p"))   {this.pawns.highlight(x,y,pos,label,list); this.lastPiece = "p";}

        pressCount ++;
    }

    private void move(int x, int y, JLabel[][] label, String[][] pos) {
        if (lastPiece.equals("p")) {pawns.move(selectedX, selectedY, x, y, label, pos);}
        else if (lastPiece.equals("kn")) {knights.move(selectedX, selectedY, x, y, label, pos);}
        else if (lastPiece.equals("k")) {kings.move(selectedX, selectedY, x, y, label, pos);}
        else if (lastPiece.equals("b")) {bishops.move(selectedX, selectedY, x, y, label, pos);}
        else if (lastPiece.equals("r")) {rooks.move(selectedX, selectedY, x, y, label, pos);}
        else if (lastPiece.equals("q")) {queens.move(selectedX, selectedY, x, y, label, pos);}
    }

    public void removeHighlights(JLabel[][] labels){
        if(pressCount > 0){
            if(lastPiece.equals("p"))       this.pawns.removeHighlights(labels);
            else if(lastPiece.equals("kn")) this.knights.removeHighlights(labels);
            else if(lastPiece.equals("k"))  this.kings.removeHighlights(labels);
            else if(lastPiece.equals("b"))  this.bishops.removeHighlights(labels);
            else if(lastPiece.equals("r"))  this.rooks.removeHighlights(labels);
            else if(lastPiece.equals("q"))  this.queens.removeHighlights(labels);
        }
        lastPiece = "";
    }
}
