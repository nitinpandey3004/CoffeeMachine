import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Player {
    private PieceEnum symbol;
    private String name;
    private List<Location> moves;

    Player(PieceEnum symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        this.moves = new ArrayList<>();
    }

    public void addMove(Location p) {
        this.moves.add(p);
    }
}
