
public class Start {
    public static void main(String[] game){
        Engine engine = new Engine();
        board c = new board(engine);
        c.initBoard();
    }
}
