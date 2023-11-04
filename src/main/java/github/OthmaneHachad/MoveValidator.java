package github.OthmaneHachad;

public class MoveValidator {
    private ChessBoard chessboard ;


    public MoveValidator(ChessBoard chessboard)
    {
        this.chessboard = chessboard ;
    }

    public boolean moveLegal(Move move)
    {
        switch (move.getPiece()){
            case PAWN: return this.pawnMoveLegal(move);
            case KING: return this.kingMoveLegal(move);
            case ROOK: return this.rookMoveLegal(move);
            case QUEEN: return this.queenMoveLegal(move);
            case BISHOP: return this.bishopMoveLegal(move);
            case KNIGHT: return this.knightMoveLegal(move);
            default: throw new IllegalArgumentException("Invalid Move, Piece not existing");
        }
    }

    private boolean knightMoveLegal(Move move)
    {
        return true ;
    }

    private boolean bishopMoveLegal(Move move)
    {
        return true ;
    }

    private boolean queenMoveLegal(Move move)
    {
        return true ;
    }

    private boolean rookMoveLegal(Move move)
    {
        int startX = (move.getStartPosition() / 8) ;
        int endX = (move.getEndPosition() / 8);

        int startY = (move.getStartPosition() % 8) ;
        int endY = (move.getEndPosition() % 8) ;

        int dx = endX - startX ;
        int dy = endY - startY ;

        if ((dx != 0 && dy != 0) || (dx == 0 && dy == 0))
        {
            return false ;
        }

        int stepX =(dx != 0) ? dx / Math.abs(dx) : 0 ;
        int stepY = (dy != 0) ? dy / Math.abs(dy) : 0 ;

        // check for any obstructions
        for (int x = startX + stepX, y = startY + stepY;
             x != endX || y != endY;
             x += stepX, y += stepY)
        {
            if (this.getChessboard().isSquareOccupied(x, y))
            {
                // means there is a piece blocking the way
                return false;
            }
        }


        if (this.getChessboard().isSquareOccupied(endX, endY))
        {
            // for final square, we check if the piece can be captured (opposite color)
            if (this.getChessboard().isSameColor(endX, endY, move.getColor()))
            {
                return false ;
            }
        }


        return true ;
    }

    private boolean kingMoveLegal(Move move)
    {
        return true ;
    }

    public boolean pawnMoveLegal(Move move) {

        int positionDifference = move.getEndPosition() - move.getStartPosition() ;
        int direction = move.getColor() == Color.WHITE ? 1 : -1 ;

        System.out.println("positionDifference: " +positionDifference + " | direction: " + direction);

        // Check for basic forward movement
        if ((positionDifference == 8 * direction && move.getPieceCaptured() == null))
        {
            return true ;
        }

        // Check for capture movement
        if (((positionDifference == (7 * direction)) || (positionDifference == (9 * direction)))
                && (move.getPieceCaptured() != null))
        {
            return true  ;
        }

        // Check for double square forward movement from starting position
        if ((move.getColor() == Color.WHITE && move.getStartPosition() / 8 == 1
                || move.getColor() == Color.BLACK && move.getStartPosition() / 8 == 6)
                && positionDifference == 16 * direction && move.getPieceCaptured() == null) {
            return true;
        }

        // TODO: Add checks for en passant and promotion
        return false ;
    }
    /** OVERLOAD
     *
      */
    public boolean pawnMoveLegal(Move move, PieceType promotionPiece) {
        int positionDifference = move.getEndPosition() - move.getStartPosition() ;
        int direction = move.getColor() == Color.WHITE ? 1 : -1 ;

        // Check for basic forward movement
        if (!(positionDifference == 8 * direction && move.getPieceCaptured() == null))
        {
            return true ;
        }

        // Check for capture movement
        if (((positionDifference == (7 * direction)) || (positionDifference == (9 * direction)))
                && (move.getPieceCaptured() != null))
        {
            return true  ;
        }

        // Check for double square forward movement from starting position
        if ((move.getColor() == Color.WHITE && move.getStartPosition() / 8 == 1
                || move.getColor() == Color.BLACK && move.getStartPosition() / 8 == 6)
                && positionDifference == 16 * direction && move.getPieceCaptured() == null) {
            return true;
        }

        // TODO: Add checks for en passant and promotion
        // TODO: Add promotion
        return false ;
    }


    public ChessBoard getChessboard() {
        return this.chessboard;
    }
}
