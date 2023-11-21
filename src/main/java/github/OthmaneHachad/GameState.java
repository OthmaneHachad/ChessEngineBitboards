package github.OthmaneHachad;

import java.util.Stack;

public class GameState {

    private long[][] layoutBitboards ;

    private long whiteBitboard ;
    private long blackBitboard ;

    private boolean isWhiteKingChecked ;



    private boolean isBlackKingChecked ;

    private Stack<GameState> boardHistory ;

    public GameState(long[][] layoutBitboards, long wBitboard, long bBitboard,
                     Stack<GameState> boardHistory, boolean whiteKChecked, boolean blackKChecked)
    {
        this.layoutBitboards = EngineCore.deepCopyBitboards(layoutBitboards);
        this.whiteBitboard = wBitboard ;
        this.blackBitboard = bBitboard ;
        this.boardHistory = boardHistory ;
        this.isWhiteKingChecked = whiteKChecked ;
        this.isBlackKingChecked = blackKChecked ;
    }

    public long[][] getLayoutBitboards()
    {
        return this.layoutBitboards;
    }

    public long getWhiteBitboard()
    {
        return this.whiteBitboard;
    }

    public long getBlackBitboard()
    {
        return this.blackBitboard;
    }

    public Stack<GameState> getBoardHistory()
    {
        return this.boardHistory;
    }

    public boolean isWhiteKingChecked()
    {
        return isWhiteKingChecked;
    }

    public boolean isBlackKingChecked()
    {
        return isBlackKingChecked;
    }
}
