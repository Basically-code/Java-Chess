package chessPiece;
import java.awt.Color;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class pawn {

    private HashSet<Pair> possibleMoves;
    private HashSet<Pair> possibleAttacks;

    public pawn(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    public record Pair(int x, int y) {}
    
    private void restart(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    private void pawnsMoves(int x, int y, boolean side, String[][] list){
        
        int nextRow = side ? x + 1 : x - 1;
        if (nextRow >= 0 && nextRow < list.length) {
            boolean lr = true;
            for (int i = 0; i < 2; i++){
                int move = lr ? y + 1: y - 1;
                if (move < list[nextRow].length){
                    boolean leftWing = !list[nextRow][move].substring(3,list[nextRow][move].length()).equals("##");
                    if(leftWing){
                        this.possibleAttacks.add(new Pair(nextRow, move));
                    }
                }
                lr = !lr;
            }
            
        }
        if (nextRow >= 0 && nextRow < list.length && checkinfront(list, nextRow, y)) {
            this.possibleMoves.add(new Pair(nextRow, y));
        }
        int nextNextRow = side ? nextRow + 1 : nextRow - 1;
        if ((x == 1 || x == 6)
                && nextRow >= 0 && nextRow < list.length
                && nextNextRow >= 0 && nextNextRow < list.length
                && checkinfront(list, nextRow, y)
                && checkinfront(list, nextNextRow, y)) {
            this.possibleMoves.add(new Pair(nextNextRow, y));
        }
    }
    
    public void highlight(int x, int y, boolean side, JLabel[][] labels, String[][] list) {
        pawnsMoves(x, y, side, list);
        for(Pair p: possibleAttacks){
            Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
            labels[p.x()][p.y()].setBorder(border);
        }

        for(Pair p: possibleMoves){
            Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
            labels[p.x()][p.y()].setBorder(border);
        }
    }

    private boolean checkinfront(String[][] list, int x, int y){
        String temp = list[x][y];
        if (temp.substring(3,temp.length()).equals("##")) return true;
        return false;
    }

    public void removeHighlights(JLabel[][] labels){
        this.possibleAttacks.addAll(possibleMoves);
        for (Pair p: possibleAttacks){
            labels[p.x()][p.y()].setBorder(null);
        }
        restart();
    }

    // private boolean isOnBoard(JLabel[][] labels, int x, int y) {
    //     return x >= 0 && x < labels.length && y >= 0 && y < labels[x].length;
    // }
}
