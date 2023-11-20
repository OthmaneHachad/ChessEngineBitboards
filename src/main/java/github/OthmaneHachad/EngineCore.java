package github.OthmaneHachad;

public class EngineCore {

    // private
    private static long[][] PAWN_MOVE_MASK ;
    private static long[] ROOK_MOVE_MASK ;
    private static long[] KNIGHT_MOVE_MASK ;
    private static long[] BISHOP_MOVE_MASK ;
    private static long[] QUEEN_MOVE_MASK ;
    private static long[] KING_MOVE_MASK ;



    public EngineCore()
    {
        this.PAWN_MOVE_MASK = new long[Color.values().length][64];
        this.ROOK_MOVE_MASK = new long[64];
        this.KNIGHT_MOVE_MASK = new long[64] ;
        this.BISHOP_MOVE_MASK = new long[64] ;
        this.QUEEN_MOVE_MASK = new long[64] ;
        this.KING_MOVE_MASK = new long[64] ;

        // generate all masks
        this.generateReachableSquares();
    }

    public void generateReachableSquares()
    {
        for (PieceType piece : PieceType.values())
        {
            for (int i = 0; i < 64; i++)
            {
                switch (piece)
                {
                    case BISHOP: this.BISHOP_MOVE_MASK[i] |= this.generateReachableSquaresBishop(i);
                    case QUEEN: this.QUEEN_MOVE_MASK[i] |= this.generateReachableSquaresQueen(i);
                    case ROOK: this.ROOK_MOVE_MASK[i] |= this.generateReachableSquaresRook(i);
                    case KNIGHT: this.KNIGHT_MOVE_MASK[i] |= this.generateReachableSquaresKnight(i);
                    case KING: this.KING_MOVE_MASK[i] |= this.generateReachableSquaresKing(i);
                    case PAWN:
                        this.PAWN_MOVE_MASK[0][i] |= this.generateReachableSquaresPawn(i, Color.BLACK);
                        this.PAWN_MOVE_MASK[1][i] |= this.generateReachableSquaresPawn(i, Color.WHITE);
                }
            }
        }
    }

    public long generateReachableSquaresKnight(int position) {
        long moves = 0L;

        int row = position / 8;
        int column = position % 8;

        // Forward Vertical
        // Up left
        if ((row + 2) < 8 && (column - 1) > 0) moves |= 1L << (position + 15);
        // Up right
        if ((row + 2) < 8 && (column + 1) < 8) moves |= 1L << (position + 17);

        // Backward Vertical
        // Down right
        if ((row - 2) > 0 && (column + 1) < 8) moves |= 1L << (position - 15);
        // Down left
        if ((row - 2) > 0 && (column - 1) > 0) moves |= 1L << (position - 17);

        // Forward Horizontal
        // Up left
        if ((row + 1) < 8 && (column - 2) > 0) moves |= 1L << (position + 6);
        // Up right
        if ((row + 1) < 8 && (column + 2) < 8) moves |= 1L << (position + 10);

        // Backward Horizontal
        // Down right
        if ((row - 1) > 0 && (column + 2) < 8) moves |= 1L << (position - 6);
        // Down left
        if ((row - 1) > 0 && (column - 2) > 0) moves |= 1L << (position - 10);

        return moves;
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
        if ((row + (1*direction)) < 8 && (row + (1*direction)) >=0)
        {
            moves |= 1L << (position + (8*direction)) ;
        }


        // diagonal right/left
        if (color == Color.WHITE)
        {
            if ((row + 1) < 8 && (column + 1) < 8)
            {
                moves |= 1L << (position + (9*direction)) ;
            }

            if ((row + 1) < 8 && (column - 1) >= 0)
            {
                moves |= 1L << (position + (7*direction)) ;
            }
        }

        // diagonal right/left
        if (color == Color.BLACK)
        {
            // diagonal left (black perspective)
            if ((row - 1) >= 0 && (column + 1) < 8)
            {
                moves |= 1L << (position + (7*direction)) ;
            }

            // diagonal right (black perspective)
            if ((row - 1) >= 0 && (column - 1) >= 0)
            {
                moves |= 1L << (position + (9*direction)) ;
            }
        }



        return moves ;


    }

    // GETTERS
    public static long[][] getPAWN_MOVE_MASK() {
        return PAWN_MOVE_MASK;
    }

    public static long[] getROOK_MOVE_MASK() {
        return ROOK_MOVE_MASK;
    }

    public static long[] getKNIGHT_MOVE_MASK() {
        return KNIGHT_MOVE_MASK;
    }

    public static long[] getBISHOP_MOVE_MASK() {
        return BISHOP_MOVE_MASK;
    }

    public static long[] getQUEEN_MOVE_MASK() {
        return QUEEN_MOVE_MASK;
    }

    public static long[] getKING_MOVE_MASK() {
        return KING_MOVE_MASK;
    }
}
