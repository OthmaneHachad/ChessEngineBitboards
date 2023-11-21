package github.OthmaneHachad;

public class Application {

    public static void main(String[] args)
    {

        ChessBoard chessboard = new ChessBoard();
        System.out.println(chessboard);

        Move pawnMove = new Move(11, 19, PieceType.PAWN, Color.WHITE, null);

        chessboard.movePiece(pawnMove);

        System.out.println(chessboard);

        // undo move
        chessboard.undoMove() ;

        // print again board

        System.out.println(chessboard);


    }
}
