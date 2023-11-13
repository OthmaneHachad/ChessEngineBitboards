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
        // 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        long reachableSquaresKnight = engine.generateReachableSquaresKnight(35);
        assertEquals(
                0b00000000_00010100_00100010_00000000_00100010_00010100_00000000_00000000L,
                reachableSquaresKnight
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
    }

    @Test
    void testGenerateReachableSquarePawnWhite()
    {
        // TODO: Generalize method for both colors and write test
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
    }
}