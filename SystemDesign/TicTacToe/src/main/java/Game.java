import java.util.List;
import java.util.Scanner;

public class Game {

    private int turnCount;
    private Player player1;
    private Player player2;
    private Board board;

    Game() {
        this.turnCount = 0;
        this.board = new Board();
    }

    private Player getPlayer() {
        return (this.turnCount%2 == 0)? this.player1: this.player2;
    }

    private void showAllMoves() {
        List<Location> player1Moves = this.player1.getMoves();

        System.out.println(player1.getName()+" "+player2.getSymbol());
        for(int i = 0; i < player1Moves.size(); i++) {
            System.out.println("Move" + (2*i+1) + " : " + player1Moves.get(i));
        }

        List<Location> player2Moves = this.player2.getMoves();

        System.out.println(player2.getName()+" "+player2.getSymbol());
        for(int i = 0; i < player2Moves.size(); i++) {
            System.out.println("Move" + (2*i+2) + " : " + player2Moves.get(i));
        }
    }

    public static void main(String[] args) {
        Game g = new Game();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter player 1's name:");
        g.player1 = new Player(PieceEnum.CIRCLE, sc.nextLine());
        System.out.println("Enter player 2's name:");
        g.player2 = new Player(PieceEnum.CROSS, sc.nextLine());


        while (true) {
            System.out.println("Current Board");
            g.board.getBoard();

            // Getting current player
            Player player = g.getPlayer();

            // Asking player for move
            System.out.println(player.getName() + "'s move");
            System.out.println("Enter row:");
            int x = sc.nextInt();
            System.out.println("Enter col:");
            int y = sc.nextInt();
            Location p = new Location(x, y);


            try {
                // Making the move
                g.board.makeMove(p, player.getSymbol());
                player.addMove(p);
                g.turnCount++;
                if(g.board.checkWinStatus(p)) {
                    System.out.println("Current Board");
                    g.board.getBoard();
                    g.showAllMoves();
                    System.out.println("Game won by " + player.getName() + " !!!!");
                    System.out.println(player.getName() + " has been awarded a planet in Andromeda Galaxy");
                    sc.close();
                    break;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
