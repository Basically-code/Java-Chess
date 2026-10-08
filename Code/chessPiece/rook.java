package chessPiece;

import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;

public class rook {
    private HashSet<Pair> possibleMoves;
    private HashSet<Pair> possibleAttacks;

    public rook(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    public record Pair(int x, int y) {}

    private void restart(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    private void rooksMoves(int x, int y, boolean side, String[][] list) {
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    for (int[] dir : directions) {
        int nextRow = x + dir[0];
        int nextCol = y + dir[1];

        while (valid(nextCol, list[0].length) && valid(nextRow, list.length)) {
            
            if (OpponentChecker(nextRow, nextCol, list, side)) {
                possibleAttacks.add(new Pair(nextRow, nextCol));
                break;
            } 
            else if (pieceChecker(nextRow, nextCol, list, side)) {
                possibleMoves.add(new Pair(nextRow, nextCol));
            } 
            else {
                break;
            }

            nextRow += dir[0];
            nextCol += dir[1];
        }
    }
}
    public void highlight(int x, int y,boolean side,JLabel[][] labels, String[][] list){
        rooksMoves(x, y, side, list);
        for (Pair p: possibleAttacks){
            Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
            labels[p.x()][p.y()].setBorder(border);
        }
        for (Pair p: possibleMoves){
            Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
            labels[p.x()][p.y()].setBorder(border);
        }
    }

    public void removeHighlights(JLabel[][] labels){
        this.possibleAttacks.addAll(possibleMoves);
        for (Pair p: possibleAttacks){
            labels[p.x()][p.y()].setBorder(null);
        }
        restart();
    }

    private boolean OpponentChecker(int x, int y, String[][] lists, boolean side){
        String temp = lists[x][y];
        if(side == false && temp.substring(3,4).equals("w")) return true;
        else if(side && temp.substring(3,4).equals("b")) return true;
        return false;
    }

    private boolean pieceChecker(int x, int y, String[][] lists, boolean side){
        String temp = lists[x][y];
        if (temp.substring(3,temp.length()).equals("##")) return true;
        return false;
    }

    private boolean valid(int n, int max){
        return n >= 0 && n < max;
    }
}
