/// For the chess pieces logic 
import java.awt.Color;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class pieces {

    private HashSet<Pair> possibleMoves;
    private HashSet<Pair> possibleAttacks;

    public pieces(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    public record Pair(int x, int y) {}
    
    /// Refreshes the moves after a play
    private void restart(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }
    
    /// Possible moves for the pawn
    private void pawnsMoves(int x, int y, boolean side, String[][] list){        
        int nextRow = side ? x + 1 : x - 1;
        if (nextRow >= 0 && nextRow < list.length) {
            boolean lr = true;
            for (int i = 0; i < 2; i++){
                int move = lr ? y + 1: y - 1;
                if (move > 0 && move < list[nextRow].length){
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


    /// Possible Moves for the king
    private void kingsMoves(int x, int y,boolean side,String[][] list){
        boolean up = true;
        for(int i = 0; i < 2; i++){
            int nextRow = up ? x + 1: x - 1;
            if(valid(nextRow, list.length)){
                if (OpponentChecker(nextRow, y,list,side)){
                    this.possibleAttacks.add(new Pair(nextRow, y));
                }
                else if (pieceChecker(nextRow, y, list, side)){
                    this.possibleMoves.add(new Pair(nextRow, y));
                }
            }
            up = !up;
        }
        boolean right = true;
        for(int i = 0; i < 2; i++){
            int nextCol = right ? y + 1: y - 1;
            if(valid(y, list[0].length)){
                if (OpponentChecker(nextCol, nextCol,list,side)){
                    this.possibleAttacks.add(new Pair(x, nextCol));
                }
                else if (pieceChecker(x, nextCol, list, side)){
                    this.possibleMoves.add(new Pair(x, nextCol));
                }
            }
            right = !right;
        }
        return;
    }

    /// Possible Moves for the rook
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

    /// Possible moves for the queen
    private void queensMoves(int x, int y,boolean side,String[][] list){
        boolean map1 = true;
        boolean map2 = true;
        int nextRow =  x + 1;
        int nextCol =  y + 1;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                while (valid(nextCol, list[0].length) && valid(nextRow, list.length)){
                    if (OpponentChecker(nextRow, nextCol, list, side)){
                        possibleAttacks.add(new Pair(nextRow, nextCol));
                        break;
                    }else if (pieceChecker(nextRow, nextCol, list, side)){
                        possibleMoves.add(new Pair(nextRow, nextCol));
                    }else{
                        break;
                    }
                    nextRow = map1 ? nextRow + 1: nextRow - 1;
                    nextCol = map2 ? nextCol + 1: nextCol - 1;
                }
                map1 = !map1;
            nextRow = map1 ? x + 1 : x - 1;
            nextCol = map2 ? y + 1 : y - 1;
        }
        
        // Flip the Column direction
        map2 = !map2;
        nextRow = map1 ? x + 1 : x - 1;
        nextCol = map2 ? y + 1 : y - 1;
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] dir : directions) {
            int nextRow1 = x + dir[0];
            int nextCol1 = y + dir[1];

            while (valid(nextCol1, list[0].length) && valid(nextRow1, list.length)) {
                
                if (OpponentChecker(nextRow1, nextCol1, list, side)) {
                    possibleAttacks.add(new Pair(nextRow1, nextCol1));
                    break;
                } 
                else if (pieceChecker(nextRow1, nextCol1, list, side)) {
                    possibleMoves.add(new Pair(nextRow1, nextCol1));
                } 
                else {
                    break;
                }
                
                nextRow1 += dir[0];
                nextCol1 += dir[1];
            }
        }
         
    }

    /// Possible moves for the bishop
    private void bishopsMoves(int x, int y,boolean side,String[][] list){
        boolean map1 = true;
        boolean map2 = true;
        int nextRow =  x + 1;
        int nextCol =  y + 1;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                while (valid(nextCol, list[0].length) && valid(nextRow, list.length)){
                    if (OpponentChecker(nextRow, nextCol, list, side)){
                        possibleAttacks.add(new Pair(nextRow, nextCol));
                        break;
                    }else if (pieceChecker(nextRow, nextCol, list, side)){
                        possibleMoves.add(new Pair(nextRow, nextCol));
                    }else{
                        break;
                    }
                    nextRow = map1 ? nextRow + 1: nextRow - 1;
                    nextCol = map2 ? nextCol + 1: nextCol - 1;
                }
                map1 = !map1;
            nextRow = map1 ? x + 1 : x - 1;
            nextCol = map2 ? y + 1 : y - 1;
        }
        
        // Flip the Column direction
        map2 = !map2;
        nextRow = map1 ? x + 1 : x - 1;
        nextCol = map2 ? y + 1 : y - 1;
        }
         
    }

    /// Possible moves for the knight
    private void knightsMoves(int x, int y,boolean side,String[][] list){

        boolean up = true;
        for(int i = 0; i < 2; i ++){
            int nextRow = up ? x + 2: x - 2;
            boolean sides = true;
            for(int j = 0; j < 2; j++){
                int nextCol = sides ? y + 1: y - 1;
                if (valid(nextRow,list.length) && valid(nextCol, list[0].length)){
                    if(OpponentChecker(nextRow, nextCol, list, side)){
                        this.possibleAttacks.add(new Pair(nextRow, nextCol));
                    }//Move
                    if (pieceChecker(nextRow, nextCol, list, side)){
                        this.possibleMoves.add(new Pair(nextRow, nextCol));
                    }//Attack
                    sides = !sides;
                }
            } up = !up;
        }
        boolean right = true;
        for(int i = 0; i < 2; i ++){
            int nextRow = right ? x - 1: x + 1;
            boolean sides = true;
            for(int j = 0; j < 2; j++){
                int nextCol = sides ? y + 2: y - 2;
                if (valid(nextRow,list.length) && valid(nextCol, list[0].length)){
                    if(OpponentChecker(nextRow, nextCol, list, side)){
                        this.possibleAttacks.add(new Pair(nextRow, nextCol));
                    }//Move
                    if (pieceChecker(nextRow, nextCol, list, side)){
                        this.possibleMoves.add(new Pair(nextRow, nextCol));
                    }//Attack
                    sides = !sides;
                }
            } right = !right;
        }
    }

    /// Chooses the moves according to the piece (String s) choosen  
    public void moves(String s, int x, int y,boolean side,String[][] list){
        if(s.equals("p"))       pawnsMoves(x, y, side, list);
        else if(s.equals("k"))  kingsMoves(x, y, side, list);
        else if(s.equals("kn")) knightsMoves(x, y, side, list);
        else if(s.equals("q"))  queensMoves(x, y, side, list);
        else if(s.equals("r"))  rooksMoves(x, y, side, list);
        else if(s.equals("b"))  bishopsMoves(x, y, side, list);
    }

    ///For highlighting the moves (Green: Possible normal moves  & Red: Possible Attacks)
    public void highlight(String s, int x, int y, boolean side, JLabel[][] labels, String[][] list) {
        moves(s, x, y, side, list);
        for(Pair p: this.possibleAttacks){
            Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
            labels[p.x()][p.y()].setBorder(border);
        }

        for(Pair p: this.possibleMoves){
            Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
            labels[p.x()][p.y()].setBorder(border);
        }
    }

    /// To remove the highlights both the Moves and Attacks
    public void removeHighlights(JLabel[][] labels){
        HashSet<Pair> newSet = new HashSet<>(possibleMoves);
        newSet.addAll(possibleAttacks);
        IO.println(newSet);
        for (Pair p: newSet){
            labels[p.x()][p.y()].setBorder(null);
            IO.println("remove");
        }
        restart();
    }

    //To move one of the pieces
    public void move(int fromX, int fromY, int toX, int toY, JLabel[][] label, String[][] list) {
        HashSet<Pair> newSet = new HashSet<>(possibleMoves);
        newSet.addAll(possibleAttacks);

        if (newSet.contains(new Pair(toX, toY))) {
            Icon pieceIcon = label[fromX][fromY].getIcon();
            ImageIcon myImageIcon = null;

            if (pieceIcon instanceof ImageIcon) {
                myImageIcon = (ImageIcon) pieceIcon;
            }

            label[fromX][fromY].setIcon(null);
            label[toX][toY].setIcon(myImageIcon);

            // update board state if needed
            String replace = list[fromX][fromY].substring(3,list[fromX][fromY].length());
            list[toX][toY] = list[toX][toY].substring(0,3) + replace;
            list[fromX][fromY] = list[fromX][fromY].substring(0,3) + "##";
        }
    }

    // -----------------------------------------------------------------------------------------------------------------//
    private boolean checkinfront(String[][] list, int x, int y){
        String temp = list[x][y];
        if (temp.substring(3,temp.length()).equals("##")) return true;
        return false;
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

    public HashSet<Pair> getPossibleMoves(){
        return possibleMoves;
    }

    public HashSet<Pair> getPossibleAttacks(){
        return possibleAttacks;
    }

    public HashSet<Pair> getAllMoves(){
        HashSet<Pair> newSet = new HashSet<>(possibleMoves);
        newSet.addAll(possibleAttacks);
        return newSet;
    }

}
