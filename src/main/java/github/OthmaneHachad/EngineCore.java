package github.OthmaneHachad;

public class EngineCore {

    // private
    private long[][] PAWN_MOVE_MASK ;
    private long[] ROOK_MOVE_MASK ;
    private long[] KNIGHT_MOVE_MASK ;
    private long[] BISHOP_MOVE_MASK ;
    private long[] QUEEN_MOVE_MASK ;
    private long[] KING_MOVE_MASK ;



    public EngineCore()
    {
        this.PAWN_MOVE_MASK = new long[Color.values().length][64];
        this.ROOK_MOVE_MASK = new long[64];
        this.KNIGHT_MOVE_MASK = new long[64] ;
        this.BISHOP_MOVE_MASK = new long[64] ;
        this.QUEEN_MOVE_MASK = new long[64] ;
        this.KING_MOVE_MASK = new long[64] ;

        // generate all masks
        //this.generateReachableSquares();
    }

    // TODO: adjust to correctly generate all MASKS
    public void generateReachableSquares(Color color, int position)
    {
        // method stub
    }

    public long generateReachableSquaresKnight(int position)
    {
        long moves = 0L ;

        int row = position / 8;
        int column = position % 8;

        // forward Vertical
        // up left
        if (row < 6 && column > 0) moves |= 1L << position + 15 ;
        // up right
        if (row < 6 && column > 0) moves |= 1L << position + 17 ;

        // backwards Vertical
        // down right
        if (row > 1 && column < 7) moves |= 1L << position - 15 ;
        // down left
        if (row > 1 && column > 0) moves |= 1L << position - 17 ;

        // forward Horizontal
        // up left
        if (row < 7 && column > 1) moves |= 1L << position + 6 ;
        // up right
        if (row < 7 && column < 6) moves |= 1L << position + 10 ;

        // backward Horizontal
        // down right
        if (row < 7 && column < 6) moves |= 1L << position - 6 ;
        // down left
        if (row > 0 && column > 1) moves |= 1L << position - 10 ;

        return moves ;
    }

    public long generateReachableSquaresBishop(int position) {
        long moves = 0L;
        int row = position / 8;
        int column = position % 8;

        // Diagonal moves in all four directions
        // Upper Left Diagonal
        for (int r = row - 1, c = column - 1; r >= 0 && c >= 0; r--, c--) {
            moves |= 1L << (r * 8 + c);
        }

        // Upper Right Diagonal
        for (int r = row - 1, c = column + 1; r >= 0 && c < 8; r--, c++) {
            moves |= 1L << (r * 8 + c);
        }

        // Lower Left Diagonal
        for (int r = row + 1, c = column - 1; r < 8 && c >= 0; r++, c--) {
            moves |= 1L << (r * 8 + c);
        }

        // Lower Right Diagonal
        for (int r = row + 1, c = column + 1; r < 8 && c < 8; r++, c++) {
            moves |= 1L << (r * 8 + c);
        }

        return moves;
    }


    public long generateReachableSquaresQueen(int position)
    {
        return this.generateReachableSquaresBishop(position)
                | this.generateReachableSquaresRook(position) ;
    }

    public long generateReachableSquaresRook(int position)
    {
        long moves = 0L;
        int row = position / 8;
        int column = position % 8;

        // Horizontal slide
        // Left
        for (int c = column - 1; c >= 0; c--) {
            moves |= 1L << (row * 8 + c);
        }

        // Right
        for (int c = column + 1; c < 8; c++) {
            moves |= 1L << (row * 8 + c);
        }

        // Vertical
        // Up
        for (int r = row + 1; r < 8; r++) {
            moves |= 1L << (r * 8 + column);
        }

        // Down
        for (int r = row - 1; r >= 0; r--) {
            moves |= 1L << (r * 8 + column);
        }

        return moves;
    }

    public long generateReachableSquaresKing(int position) {
        long moves = 0L;
        int row = position / 8;
        int column = position % 8;

        // Forward and backward
        if (row < 7) moves |= 1L << (position + 8);
        if (row > 0) moves |= 1L << (position - 8);

        // Diagonal forward right/left
        if (row < 7 && column < 7) moves |= 1L << (position + 9);
        if (row < 7 && column > 0) moves |= 1L << (position + 7);

        // Diagonal backward right/left
        if (row > 0 && column < 7) moves |= 1L << (position - 7);
        if (row > 0 && column > 0) moves |= 1L << (position - 9);

        // Horizontal left and right
        if (column < 7) moves |= 1L << (position + 1);
        if (column > 0) moves |= 1L << (position - 1);

        return moves;
    }


    public long generateReachableSquaresPawn(int position, Color color)
    {
        // TODO: add en passant
        // TODO: fix pawn reachable squares
        // setPieceMoveBitboards
        long moves = 0L ;
        int direction = color == Color.WHITE ? 1 : -1 ;

        int row = position / 8 ;
        int column = position % 8 ;

        if (row == 1 && color == Color.WHITE)
        {
            moves |= 1L << (position + 16) ;
        }

        if (row == 6 && color == Color.BLACK)
        {
            moves |= 1L << (position - 16) ;
        }

        // forward
        moves |= 1L << (position + (8*direction)) ;

        // diagonal right/left
        moves |= 1L << (position + (9*direction)) ;
        moves |= 1L << (position + (7*direction)) ;

        return moves ;


    }
}