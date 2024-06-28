package github.OthmaneHachad;

import java.util.Stack;

public class GameState {

    private long[][] layoutBitboards ;

    private long whiteBitboard ;
    private long blackBitboard ;

    private boolean isWhiteKingChecked ;



    private boolean isBlackKingChecked ;

    private Stack<GameState> boardHistory ;

    // En passant specific fields
    private int enPassantTargetSquare;  // Position where an en passant capture can occur
    private Color enPassantTargetColor; // Color of the pawn that moved two squares


    public GameState(long[][] layoutBitboards, long wBitboard, long bBitboard,
                     Stack<GameState> boardHistory, boolean whiteKChecked, boolean blackKChecked,
                     int enPassantTargetSquare, Color enPassantTargetColor)
    {
        this.layoutBitboards = EngineCore.deepCopyBitboards(layoutBitboards);
        this.whiteBitboard = wBitboard ;
        this.blackBitboard = bBitboard ;
        this.boardHistory = boardHistory ;
        this.isWhiteKingChecked = whiteKChecked ;
        this.isBlackKingChecked = blackKChecked ;
        this.enPassantTargetSquare = enPassantTargetSquare;
        this.enPassantTargetColor = enPassantTargetColor;
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

    public int getEnPassantTargetSquare() {
        return enPassantTargetSquare;
    }

    public Color getEnPassantTargetColor() {
        return enPassantTargetColor;
    }
}
