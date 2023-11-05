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
    void testRookMoveLegal() {
        // Set up the board position using the FEN string
        ChessBoard chessBoard = new ChessBoard("8/8/2p5/2P5/3R4/2P5/2p5/8 w - - 0 1");
        MoveValidator validator = new MoveValidator(chessBoard);

        System.out.println(chessBoard);

        // Scenario 1: Basic Horizontal Movement to the Right
        Move horizontalRight = new Move(27, 35, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to h4
        assertEquals(true, validator.rookMoveLegal(horizontalRight));

        // Scenario 2: Basic Horizontal Movement to the Left
        Move horizontalLeft = new Move(27, 19, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to a4
        assertEquals(true, validator.rookMoveLegal(horizontalLeft));

        // Scenario 4: Basic Vertical Movement Downwards
        Move verticalDown = new Move(27, 3, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to d1
        assertEquals(true, validator.rookMoveLegal(verticalDown));

        // Scenario 5: Capture Movement to the Right
        Move captureRight = new Move(27, 34, PieceType.ROOK, Color.WHITE, PieceType.PAWN); // Assuming move from d4 to g4
        assertEquals(false, validator.rookMoveLegal(captureRight));

        // Scenario 6: Capture Movement to the Left
        Move captureLeft = new Move(27, 18, PieceType.ROOK, Color.WHITE, PieceType.PAWN); // Assuming move from d4 to b4
        assertEquals(false, validator.rookMoveLegal(captureLeft));

        // Scenario 7: Blocked Movement to the Right
        Move blockedRight = new Move(27, 36, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to i4 (invalid move)
        assertEquals(false, validator.rookMoveLegal(blockedRight));

        // Scenario 8: Blocked Movement Upwards
        Move blockedUp = new Move(27, 60, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to d9 (invalid move)
        assertEquals(false, validator.rookMoveLegal(blockedUp));

        // failed tests TODO: FIX THIS TEST

        Move verticalUp1 = new Move(27, 35, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to d8
        assertEquals(true, validator.rookMoveLegal(verticalUp1));

        Move verticalUp2 = new Move(27, 43, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to d8
        assertEquals(true, validator.rookMoveLegal(verticalUp2));

        Move verticalUp3 = new Move(27, 51, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to d8
        assertEquals(true, validator.rookMoveLegal(verticalUp3));

        // Scenario 3: Basic Vertical Movement Upwards
        Move verticalUp = new Move(27, 59, PieceType.ROOK, Color.WHITE, null); // Assuming move from d4 to d8
        assertEquals(true, validator.rookMoveLegal(verticalUp));

    }



    @Test
    void getChessboard() {
    }
}