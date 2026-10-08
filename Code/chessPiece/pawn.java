package chessPiece;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class pawn {

    private int[] lastPoints = new int[]{0,0};
    private boolean[] highlights = new boolean[4];
    private boolean lastSide;
    
    private void restart(){
        highlights = new boolean[4];
    }
    
    public void highlight(int x, int y, boolean side, JLabel[][] labels, String[][] list) {
        lastSide = side;
        int nextRow = side ? x + 1 : x - 1;
        if (nextRow >= 0 && nextRow < labels.length && checkinfront(list, nextRow, y)) {
            Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
            labels[nextRow][y].setBorder(border);
            this.highlights[0] = true;
        }
        int nextNextRow = side ? nextRow + 1 : nextRow - 1;
        if ((x == 1 || x == 6)
                && nextRow >= 0 && nextRow < labels.length
                && nextNextRow >= 0 && nextNextRow < labels.length
                && checkinfront(list, nextRow, y)
                && checkinfront(list, nextNextRow, y)) {
            Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
            labels[nextNextRow][y].setBorder(border);
            this.highlights[3] = true;
        }
        if (nextRow >= 0 && nextRow < labels.length) {
            checkAttack(labels, list, nextRow, y);
        }
        lastPoints = new int[]{x,y};
        
    }

    private boolean checkinfront(String[][] list, int x, int y){
        String temp = list[x][y];
        if (temp.substring(3,temp.length()).equals("##")) return true;
        return false;
    }

    public void removeHighlights(JLabel[][] labels){
        int nextRow = lastSide ? this.lastPoints[0] + 1 : this.lastPoints[0] - 1;
        if (this.highlights[0] && isOnBoard(labels, nextRow, lastPoints[1])) {
            labels[nextRow][lastPoints[1]].setBorder(null);
        }
        if (this.highlights[1] && isOnBoard(labels, nextRow, lastPoints[1] + 1)) {
            labels[nextRow][lastPoints[1] + 1].setBorder(null);
        }
        if (this.highlights[2] && isOnBoard(labels, nextRow, lastPoints[1] - 1)) {
            labels[nextRow][lastPoints[1] - 1].setBorder(null);
        }

        int nextNextRow = lastSide ? nextRow + 1 : nextRow - 1;
        if (this.highlights[3] && isOnBoard(labels, nextNextRow, lastPoints[1])) {
            labels[nextNextRow][lastPoints[1]].setBorder(null);
        }
        restart();
    }

    private boolean isOnBoard(JLabel[][] labels, int x, int y) {
        return x >= 0 && x < labels.length && y >= 0 && y < labels[x].length;
    }

    private void checkAttack(JLabel[][] labels, String[][] lists, int x, int y){
        int left = y + 1;
        if (left < lists[x].length){
            boolean leftWing = !lists[x][left].substring(3,lists[x][left].length()).equals("##");
            if(leftWing){
                Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
                labels[x][left].setBorder(border);
                this.highlights[1] = true;
            }
        }
        int right = y - 1;
        if (right >= 0){
            boolean rightWing = !lists[x][right].substring(3,lists[x][right].length()).equals("##");
            if(rightWing){
                Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
                labels[x][right].setBorder(border);
                this.highlights[2] = true;
            }
        }
    }
}
