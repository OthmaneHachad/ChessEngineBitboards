package github.OthmaneHachad;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    /*
    FIELDS
     */
    private ChessBoard chessBoard ;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // do any of the initialization code that should be run before each
        // test method HERE
        chessBoard = new ChessBoard() ;
    }

    @Test
    public void testSetBitboardsWhite() {
        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.WHITE.ordinal();

        // The position where the piece will be set on the bitboard
        int position = 10;


        // Call the method to be tested
        chessBoard.setBitboards(PieceType.PAWN, Color.WHITE, position);

        // Retrieve the bitboard
        long[][] bitboards = chessBoard.getBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        // Check that the bitboard was updated correctly
        assertEquals(expectedBitboardValue, bitboards[pieceTypeOrdinal][colorOrdinal]);
    }

    @Test
    public void testSetBitboardsBlack() {
        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.BLACK.ordinal();

        // The position where the piece will be set on the bitboard
        int position = 55;


        // Call the method to be tested
        chessBoard.setBitboards(PieceType.PAWN, Color.BLACK, position);

        // Retrieve the bitboard
        long[][] bitboards = chessBoard.getBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        // Check that the bitboard was updated correctly
        assertEquals(expectedBitboardValue, bitboards[pieceTypeOrdinal][colorOrdinal]);
    }

    @Test
    public void testSetPiece()
    {
        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.WHITE.ordinal();

        System.out.println("pieceTypeOrdinal: "+ pieceTypeOrdinal +"\ncolorOrdinal: " + colorOrdinal);

        // The position where the piece will be set on the bitboard
        int row = 1 ;
        int column = 2 ;

        // expected computed position by setPiece
        int position = 10;

        // Call the method to be tested
        chessBoard.setPiece(PieceType.PAWN, Color.WHITE, row, column);

        // Retrieve the bitboard
        long[][] bitboards = chessBoard.getBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        // Check that the bitboard was updated correctly
        assertEquals(expectedBitboardValue, bitboards[pieceTypeOrdinal][colorOrdinal]);
    }

    @Test
    public void testParseFen()
    {
        // "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        // pre computed parsed Fen String
        HashMap<String, String> parsedFen = chessBoard.parseFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

        assertEquals(parsedFen.get("board_layout"), "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        assertEquals(parsedFen.get("playing"), "w");
        assertEquals(parsedFen.get("rock"), "KQkq");
        assertEquals(parsedFen.get("en_passant"), "-");
        assertEquals(parsedFen.get("full_move_clock"), "0");
        assertEquals(parsedFen.get("half_move_clock"), "1");
    }


    @Test
    public void testSetupPosition()
    {
        chessBoard = new ChessBoard();
        /**
         * FEN STRING has a flipped board representation, the furthest right the piece is
         * the "lower" its position on the y axis
         * lower case = black
         * upper case = white
         * black -> Highest values
         * white -> Lowest values
         *
         */
        long[][] board = chessBoard.getBitboards() ;
        System.out.println(chessBoard.getBoardInfo().get("board_layout"));

        //long bitboard = 0b11111111_11111111_00000000_00000000_00000000_00000000_11111111_11111111;

        long blackPawns = 0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        long whitePawns = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;

        long blackRooks = 0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long whiteRooks = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L;

        long blackKnights = 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long whiteKnights = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L;

        long blackBishops = 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long whiteBishops = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L;

        long blackQueen = 0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long whiteQueen = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L;

        long blackKing = 0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        long whiteKing = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L;

        System.out.println(board[0][0] + " PAWNS " + whitePawns);
        System.out.println(board[0][1]+ " PAWNS " + blackPawns);
        assertEquals(board[0][0], whitePawns);
        assertEquals(board[0][1], blackPawns);

        /**
         * Issue with the asserEquals here, fix the bitboards
         */

        System.out.println(board[1][0] == whiteRooks);
        System.out.println(board[1][1] == blackRooks);

        assertEquals(board[1][0], whiteRooks);
        assertEquals(board[1][1], blackRooks);

        System.out.println(board[2][0] == whiteKnights);
        System.out.println(board[2][1] == blackKnights);
        assertEquals(board[2][0], whiteKnights);
        assertEquals(board[2][1], blackKnights);

        System.out.println(board[3][0] == whiteBishops);
        System.out.println(board[3][1] == blackBishops);
        assertEquals(board[3][0], whiteBishops);
        assertEquals(board[3][1], blackBishops);

        System.out.println(board[4][0] == whiteQueen);
        System.out.println(board[4][1] == blackQueen);

        System.out.println(board[5][0] == whiteKing);
        System.out.println(board[5][1] == blackKing);
        assertEquals(board[4][0], whiteQueen);
        assertEquals(board[4][1], blackQueen);


        assertEquals(board[5][0], whiteKing);
        assertEquals(board[5][1], blackKing);
    }

    @Test
    void testIsSquareOccupiedFalse() {
        // TODO: Implement this test
        boolean squareOccupied  = chessBoard.isSquareOccupied(3,3);
        assertEquals(false, squareOccupied);
    }

    @Test
    void testIsSquareOccupiedTrue() {
        // TODO: Implement this test
        boolean squareOccupied  = chessBoard.isSquareOccupied(0,3);
        assertEquals(true, squareOccupied);
    }

    @Test
    void testIsSameColor() {
        // TODO: Implement this test
        //fail("Not yet implemented");
    }

    @Test
    void testMovePiece()
    {
        // TODO: Implement this test
        //fail("Not yet implemented");
    }

    @Test
    void testSetWhiteBitboard() {
        // TODO: Implement this test
        //fail("Not yet implemented");
    }

    @Test
    void testSetBlackBitboard() {
        // TODO: Implement this test
        //fail("Not yet implemented");
    }


}