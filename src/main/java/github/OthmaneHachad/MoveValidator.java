package github.OthmaneHachad;

public class MoveValidator {
    private ChessBoard chessboard ;
    private EngineCore core ;


    public MoveValidator(ChessBoard chessboard)
    {
        this.chessboard = chessboard ;
        this.core = new EngineCore();
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

    public boolean knightMoveLegal(Move move)
    {
        // knight doesn't care about obstructing pieces
        return ( core.getKNIGHT_MOVE_MASK()[move.getStartPosition()]
                & 1L << (move.getEndPosition()) ) != 0 ;
    }

    public boolean bishopMoveLegal(Move move)
    {

        if (
                ( core.getBISHOP_MOVE_MASK()[move.getStartPosition()]
                        & 1L << (move.getEndPosition()) ) == 0
        )
        {
            // Means the square is not reachable by piece - unavailable in database
            System.out.println("Target Square unreachable by piece - not found in db");
            return false ;
        }

        int startX = (move.getStartPosition() / 8);
        int endX = (move.getEndPosition() / 8);

        int startY = (move.getStartPosition() % 8);
        int endY = (move.getEndPosition() % 8);

        int dx = endX - startX;
        int dy = endY - startY;

        if (Math.abs(endX - startX) != Math.abs(endY - startY)) {
            System.out.println("abs dx: " +Math.abs(endX - startX) + " abs dy: " +Math.abs(endY - startY));
            System.out.println("both x and y have to be different than starting position");
            return false;
        }

        int stepX = endX > startX ? 1 : -1 ;
        int stepY = endY > startY ? 1 : -1 ;

        for (int x = startX + stepX, y = startY + stepY;
             x != endX && y != endY;
             x += stepX, y += stepY) {
            if (this.getChessboard().isSquareOccupied(x, y)) {
                // Means there is a piece blocking the way
                return false;
            }
        }

        // Check the destination square separately
        if (this.getChessboard().isSquareOccupied(endX, endY)) {
            // For final square, we check if the piece can be captured (opposite color)
            if (this.getChessboard().isSameColor(endX, endY, move.getColor())) {
                System.out.println("same color piece at end position");
                return false;
            }
        }

        return true ;
    }

    public boolean queenMoveLegal(Move move)
    {

        if (
                ( core.getQUEEN_MOVE_MASK()[move.getStartPosition()]
                        & 1L << (move.getEndPosition()) ) == 0
        )
        {
            // Means the square is not reachable by piece - unavailable in database
            System.out.println("Target Square unreachable by piece - not found in db");
            return false ;
        }


        int startX = (move.getStartPosition() / 8);
        int endX = (move.getEndPosition() / 8);

        int startY = (move.getStartPosition() % 8);
        int endY = (move.getEndPosition() % 8);

        int dx = endX - startX;
        int dy = endY - startY;

        // Determine whether the movement is sliding or diagonal
        boolean isDiagonal = Math.abs(dx) == Math.abs(dy);
        boolean isSliding = dx == 0 || dy == 0 ;

        if (isSliding)
        {
            return this.rookMoveLegal(move);
        } else if (isDiagonal)
        {
            return this.bishopMoveLegal(move);
        }
        // Not a valid move
        return false ;
    }

    public boolean rookMoveLegal(Move move) {

        if (
                ( core.getROOK_MOVE_MASK()[move.getStartPosition()]
                        & 1L << (move.getEndPosition()) ) == 0
        )
        {
            // Means the square is not reachable by piece - unavailable in database
            System.out.println("Target Square unreachable by piece - not found in db");
            return false ;
        }

        int startX = (move.getStartPosition() / 8);
        int endX = (move.getEndPosition() / 8);

        int startY = (move.getStartPosition() % 8);
        int endY = (move.getEndPosition() % 8);

        int dx = endX - startX;
        int dy = endY - startY;

        if ((dx != 0 && dy != 0) || (dx == 0 && dy == 0)) {
            System.out.println("both x and y are different than starting position ");
            return false;
        }

        int stepX = (dx != 0) ? dx / Math.abs(dx) : 0;
        int stepY = (dy != 0) ? dy / Math.abs(dy) : 0;

        // Check for any obstructions along the path, excluding the destination square
        for (int x = startX + stepX, y = startY + stepY;
             x != endX && y != endY;
             x += stepX, y += stepY) {

            if (this.getChessboard().isSquareOccupied(x, y)) {
                // Means there is a piece blocking the way
                System.out.println("piece blocking the way");
                return false;
            }
        }

        // Check the destination square separately
        if (this.getChessboard().isSquareOccupied(endX, endY)) {
            // For final square, we check if the piece can be captured (opposite color)
            if (this.getChessboard().isSameColor(endX, endY, move.getColor())) {
                System.out.println("same color piece at end position");
                return false;
            }
        }

        return true;
    }



    public boolean kingMoveLegal(Move move) {
        // First, check if the move is within the king's move pattern
        boolean isMovePatternValid = (EngineCore.getKING_MOVE_MASK()[move.getStartPosition()] & (1L << move.getEndPosition())) != 0;
        if (!isMovePatternValid) {
            // If the move is not within the king's move pattern, it's illegal
            return false;
        }

        // Temporarily make the move on the chessboard
        chessboard.movePiece(move);

        // Check if the move results in the king being in check
        if (chessboard.isKingChecked(move.getColor())) {
            chessboard.undoMove(); // Undo the move since it's illegal
            return false;
        }


        // If the king is not checked and the move pattern is valid, the move is legal
        chessboard.undoMove(); // Undo the move as this was just a check
        return true;
    }


    public boolean pawnMoveLegal(Move move) {

        if (
                ( core.getPAWN_MOVE_MASK()[move.getColor().ordinal()][move.getStartPosition()]
                & 1L << (move.getEndPosition()) ) == 0
        )
        {
            // Means the square is not reachable by piece - unavailable in database
            //System.out.println("Target Square unreachable by piece - not found in Core");
            return false ;
        }

        int positionDifference = move.getEndPosition() - move.getStartPosition() ;
        int direction = move.getColor() == Color.WHITE ? 1 : -1 ;

        System.out.println("positionDifference: " + positionDifference + " | direction: " + direction);

        // Check for basic forward movement
        if ((positionDifference == 8 * direction && move.getPieceCaptured() == null))
        {
            return true ;
        }

        // Check for capture movement
        if (((positionDifference == (7 * direction)) || (positionDifference == (9 * direction)))
                && (move.getPieceCaptured() != null))
        {

            // En passant capture
            if (chessboard.getEnPassantTargetSquare() == move.getEndPosition() &&
                    (Math.abs(move.getStartPosition() / 8) - (move.getEndPosition() / 8)) == 1) { // Ensure it's a diagonal move
                return move.getColor() != chessboard.getEnPassantTargetColor(); // Ensure correct opposing pawn
            }

            // Regular capture
            return !chessboard.isSameColor(move.getEndPosition() / 8, move.getEndPosition() % 8, move.getColor());
        }

        // Check for double square forward movement from starting position
        if ((move.getColor() == Color.WHITE && move.getStartPosition() / 8 == 1
                || move.getColor() == Color.BLACK && move.getStartPosition() / 8 == 6)
                && positionDifference == 16 * direction && move.getPieceCaptured() == null) {
            return true;
        }

        return false ;
    }

    public ChessBoard getChessboard() {
        return this.chessboard;
    }
}
