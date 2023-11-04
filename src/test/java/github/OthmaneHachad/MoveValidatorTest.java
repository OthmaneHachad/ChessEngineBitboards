package github.OthmaneHachad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveValidatorTest {

    private MoveValidator validator ;
    private ChessBoard chessBoard ;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        validator = new MoveValidator(chessBoard);
    }

    @Test
    void moveLegal() {
    }


    @Test
    void testPawnMoveLegal() {

        // Scenario 1: Basic Forward Movement for White Pawn
        Move basicForwardWhite = new Move(8, 16,
                PieceType.PAWN, Color.WHITE, null); // Assuming move from a2 to a3
        assertEquals(true, validator.pawnMoveLegal(basicForwardWhite));

        // Scenario 2: Basic Forward Movement for Black Pawn
        Move basicForwardBlack = new Move(55, 47,
                PieceType.PAWN, Color.BLACK, null); // Assuming move from a7 to a6
        assertEquals(true, validator.pawnMoveLegal(basicForwardBlack));

        // Scenario 3: Capture Movement for White Pawn
        chessBoard.setPiece(PieceType.PAWN, Color.BLACK, 2, 1);  // Assuming there is a black pawn at b3
        Move captureWhite = new Move(8, 17,
                PieceType.PAWN, Color.WHITE, PieceType.PAWN); // Assuming move from a2 to b3
        assertEquals(true, validator.pawnMoveLegal(captureWhite));

        // Scenario 4: Capture Movement for Black Pawn
        chessBoard.setPiece(PieceType.PAWN, Color.WHITE, 5, 6);  // Assuming there is a white pawn at g6
        Move captureBlack = new Move(55, 46,
                PieceType.PAWN, Color.BLACK, PieceType.PAWN); // Assuming move from a7 to g6
        assertEquals(true, validator.pawnMoveLegal(captureBlack));

        // Scenario 5: Double Square Forward Movement from Starting Position for White Pawn
        Move doubleForwardWhite = new Move(8, 24,
                PieceType.PAWN, Color.WHITE, null); // Assuming move from a2 to a4
        assertEquals(true, validator.pawnMoveLegal(doubleForwardWhite));

        // Scenario 6: Double Square Forward Movement from Starting Position for Black Pawn
        Move doubleForwardBlack = new Move(55, 39,
                PieceType.PAWN, Color.BLACK, null); // Assuming move from a7 to a5
        assertEquals(true, validator.pawnMoveLegal(doubleForwardBlack));

        // Scenario 7: Illegal Movement (not following any legal pawn movement pattern)
        Move illegalMove = new Move(8, 40,
                PieceType.PAWN, Color.WHITE, null); // Assuming move from a2 to a6
        assertEquals(false, validator.pawnMoveLegal(illegalMove));

    }


    @Test
    void testRookMoveLegal()
    {

    }

    @Test
    void getChessboard() {
    }
}