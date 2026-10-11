import javax.swing.SwingUtilities;

import sound.chessSound;

public class Start {
    public static void main(String[] game){
        SwingUtilities.invokeLater(() -> {
            new chessSound();
        });
        Engine engine = new Engine();
        board c = new board(engine);
        c.initBoard();
    }
}
