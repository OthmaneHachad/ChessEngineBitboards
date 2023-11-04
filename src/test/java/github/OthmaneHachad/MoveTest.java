package github.OthmaneHachad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    private Move mv ;

    @BeforeEach
    void setUp()
    {
        mv = new Move(20, 28, PieceType.PAWN, Color.WHITE, null);
    }

    @Test
    void getStartPosition()
    {
        assertEquals(20, mv.getStartPosition());
    }

    @Test
    void getEndPosition()
    {
        assertEquals(28, mv.getEndPosition());
    }

    @Test
    void getColor()
    {
        assertEquals(Color.WHITE, mv.getColor());
    }

    @Test
    void getPiece()
    {
        assertEquals(PieceType.PAWN, mv.getPiece());
    }

    @Test
    void getPieceCaptured()
    {
        assertEquals(null, mv.getPieceCaptured());

        Move secondMove = new Move(20, 28, PieceType.PAWN, Color.WHITE, PieceType.BISHOP);
        assertEquals(PieceType.BISHOP, secondMove.getPieceCaptured());
    }
}