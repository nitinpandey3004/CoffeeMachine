
import lombok.ToString;


public enum PieceEnum {
    CIRCLE ("O"),
    CROSS ("X"),
    EMPTY ("-");

    private final String symbol;

    PieceEnum(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return this.symbol;
    }
}
