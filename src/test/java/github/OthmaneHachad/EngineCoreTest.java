package github.OthmaneHachad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineCoreTest {

    private EngineCore engine ;

    @BeforeEach
    void setUp() {
        engine = new EngineCore();
    }

    @Test
    void testGenerateReachableSquaresKnight()
    {
        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquaresKnight = engine.generateReachableSquaresKnight(35);
        // 00000000 00010100 00100000 00000000 00100010 00010100 00000000 00000000
        assertEquals(
                0b00000000_00010100_00100010_00000000_00100010_00010100_00000000_00000000L,
                reachableSquaresKnight
        );

        long reachableSquaresKnightBottomLeft = engine.generateReachableSquaresKnight(0);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000010_00000100_00000000L,
                reachableSquaresKnightBottomLeft
        );

        long reachableSquaresKnightTopLeft = engine.generateReachableSquaresKnight(56);
        System.out.println(Long.toBinaryString(reachableSquaresKnightTopLeft));
        // 00000000 00000100 00000010 00000000 00000000 00000000 00000000 00000000
        assertEquals(
                0b00000000_00000100_00000010_00000000_00000000_00000000_00000000_00000000L,
                reachableSquaresKnightTopLeft
        );


        long reachableSquaresKnightBottomRight = engine.generateReachableSquaresKnight(7);
        System.out.println(Long.toBinaryString(reachableSquaresKnightBottomRight));
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_01000000_00100000_00000000L,
                reachableSquaresKnightBottomRight
        );

        long reachableSquaresKnightTopRight = engine.generateReachableSquaresKnight(63);
        System.out.println(Long.toBinaryString(reachableSquaresKnightTopRight));
        assertEquals(
                0b00000000_00100000_01000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquaresKnightTopRight
        );
    }

    @Test
    void testGenerateReachableSquaresBishop()
    {
        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquaresBishop = engine.generateReachableSquaresBishop(35);
        assertEquals(
                0b01000001_00100010_00010100_00000000_00010100_00100010_01000001_10000000L,
                reachableSquaresBishop
        );

        long reachableSquaresBishopBottomLeft = engine.generateReachableSquaresBishop(0);
        assertEquals(
                0b10000000_01000000_00100000_00010000_00001000_00000100_00000010_00000000L,
                reachableSquaresBishopBottomLeft
        );

        long reachableSquaresBishopTopLeft = engine.generateReachableSquaresBishop(56);
        assertEquals(
                0b00000000_00000010_00000100_00001000_00010000_00100000_01000000_10000000L,
                reachableSquaresBishopTopLeft
        );


        long reachableSquaresBishopBottomRight = engine.generateReachableSquaresBishop(7);
        assertEquals(
                0b00000001_00000010_00000100_00001000_00010000_00100000_01000000_00000000L,
                reachableSquaresBishopBottomRight
        );

        long reachableSquaresBishopTopRight = engine.generateReachableSquaresBishop(63);
        assertEquals(
                0b00000000_01000000_00100000_00010000_00001000_00000100_00000010_00000001L,
                reachableSquaresBishopTopRight
        );
    }

    @Test
    void testGenerateReachableSquaresRook()
    {
        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquaresRook = engine.generateReachableSquaresRook(35);
        assertEquals(
                0b00001000_00001000_00001000_11110111_00001000_00001000_00001000_00001000L,
                reachableSquaresRook
        );

        long reachableSquaresRookBottomLeft = engine.generateReachableSquaresRook(0);
        assertEquals(
                0b00000001_00000001_00000001_00000001_00000001_00000001_00000001_11111110L,
                reachableSquaresRookBottomLeft
        );

        long reachableSquaresRookTopLeft = engine.generateReachableSquaresRook(56);
        assertEquals(
                0b11111110_00000001_00000001_00000001_00000001_00000001_00000001_00000001L,
                reachableSquaresRookTopLeft
        );


        long reachableSquaresRookBottomRight = engine.generateReachableSquaresRook(7);
        assertEquals(
                0b10000000_10000000_10000000_10000000_10000000_10000000_10000000_01111111L,
                reachableSquaresRookBottomRight
        );

        long reachableSquaresRookTopRight = engine.generateReachableSquaresRook(63);
        assertEquals(
                0b01111111_10000000_10000000_10000000_10000000_10000000_10000000_10000000L,
                reachableSquaresRookTopRight
        );
    }

    @Test
    void testGenerateReachableSquaresQueen()
    {
        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquaresQueen = engine.generateReachableSquaresQueen(35);

        long expected = 0b00001000_00001000_00001000_11110111_00001000_00001000_00001000_00001000L |
                0b01000001_00100010_00010100_00000000_00010100_00100010_01000001_10000000L ;
        assertEquals(
                expected,
                reachableSquaresQueen
        );

        long reachableSquaresQueenBottomLeft = engine.generateReachableSquaresQueen(0);
        assertEquals(
                0b00000001_00000001_00000001_00000001_00000001_00000001_00000001_11111110L
                    | 0b10000000_01000000_00100000_00010000_00001000_00000100_00000010_00000000L,
                reachableSquaresQueenBottomLeft
        );

        long reachableSquaresQueenTopLeft = engine.generateReachableSquaresQueen(56);
        assertEquals(
                0b11111110_00000001_00000001_00000001_00000001_00000001_00000001_00000001L
                    | 0b00000000_00000010_00000100_00001000_00010000_00100000_01000000_10000000L,
                reachableSquaresQueenTopLeft
        );


        long reachableSquaresQueenBottomRight = engine.generateReachableSquaresQueen(7);
        assertEquals(
                0b10000000_10000000_10000000_10000000_10000000_10000000_10000000_01111111L
                    | 0b00000001_00000010_00000100_00001000_00010000_00100000_01000000_00000000L,
                reachableSquaresQueenBottomRight
        );

        long reachableSquaresQueenTopRight = engine.generateReachableSquaresQueen(63);
        assertEquals(
                0b01111111_10000000_10000000_10000000_10000000_10000000_10000000_10000000L
                    | 0b00000000_01000000_00100000_00010000_00001000_00000100_00000010_00000001L,
                reachableSquaresQueenTopRight
        );
    }

    @Test
    void testGenerateReachableSquaresKing()
    {
        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquaresKing = engine.generateReachableSquaresKing(35);
        assertEquals(
                0b00000000_00000000_00011100_00010100_00011100_00000000_00000000_00000000L,
                reachableSquaresKing
        );

        long reachableSquaresKingBottomLeft = engine.generateReachableSquaresKing(0);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_00000011_00000010L,
                reachableSquaresKingBottomLeft
        );

        long reachableSquaresKingTopLeft = engine.generateReachableSquaresKing(56);
        assertEquals(
                0b00000010_00000011_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquaresKingTopLeft
        );


        long reachableSquaresKingBottomRight = engine.generateReachableSquaresKing(7);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_11000000_01000000L,
                reachableSquaresKingBottomRight
        );

        long reachableSquaresKingTopRight = engine.generateReachableSquaresKing(63);
        assertEquals(
                0b01000000_11000000_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquaresKingTopRight
        );
    }

    @Test
    void testGenerateReachableSquarePawn()
    {
        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquaresPawn = engine.generateReachableSquaresPawn(35, Color.WHITE);
        assertEquals(
                0b00000000_00000000_00011100_00000000_00000000_00000000_00000000_00000000L,
                reachableSquaresPawn
        );

        long reachableSquaresPawnStartRow = engine.generateReachableSquaresPawn(13, Color.WHITE);
        // 00100000 01110000 00000000 00000000

        System.out.println(Long.toBinaryString(reachableSquaresPawnStartRow));
        System.out.println(reachableSquaresPawnStartRow);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00100000_01110000_00000000_00000000L,
                reachableSquaresPawnStartRow
        );

        long reachableSquaresPawnStartRow2 = engine.generateReachableSquaresPawn(11, Color.WHITE);
        // 00100000 01110000 00000000 00000000

        System.out.println(Long.toBinaryString(reachableSquaresPawnStartRow2));
        System.out.println(reachableSquaresPawnStartRow2);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00001000_00011100_00000000_00000000L,
                reachableSquaresPawnStartRow2
        );

        // EDGE CASES

        //WHITE PAWN

        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquarePawnBottomLeftWhite = engine.generateReachableSquaresPawn(0, Color.WHITE);
        // 00001000 00011100 00000000 00000000
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_00000011_00000000L,
                reachableSquarePawnBottomLeftWhite
        );

        long reachableSquarePawnTopLeftWhite = engine.generateReachableSquaresPawn(56, Color.WHITE);
        System.out.println(Long.toBinaryString(reachableSquarePawnTopLeftWhite));
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquarePawnTopLeftWhite
        );


        long reachableSquarePawnBottomRightWhite = engine.generateReachableSquaresPawn(7, Color.WHITE);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_11000000_00000000L,
                reachableSquarePawnBottomRightWhite
        );

        long reachableSquarePawnTopRightWhite = engine.generateReachableSquaresPawn(63, Color.WHITE);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquarePawnTopRightWhite
        );

        // BLACK PAWN

        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquarePawnBottomLeftBlack = engine.generateReachableSquaresPawn(0, Color.BLACK);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquarePawnBottomLeftBlack
        );

        long reachableSquarePawnTopLeftBlack = engine.generateReachableSquaresPawn(56, Color.BLACK);
        // 00000001 10000000 00000000 00000000 00000000 00000000 00000000
        System.out.println(Long.toBinaryString(reachableSquarePawnTopLeftBlack));
        assertEquals(
                0b00000000_00000011_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquarePawnTopLeftBlack
        );


        long reachableSquarePawnBottomRightBlack = engine.generateReachableSquaresPawn(7, Color.BLACK);
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquarePawnBottomRightBlack
        );

        long reachableSquarePawnTopRightBlack = engine.generateReachableSquaresPawn(63, Color.BLACK);
        assertEquals(
                0b00000000_11000000_00000000_00000000_00000000_00000000_00000000_00000000L,
                reachableSquarePawnTopRightBlack
        );
    }
}