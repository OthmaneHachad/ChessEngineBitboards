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

        ChessBoard chessBoardEmpty = new ChessBoard("8/8/8/8/8/8/8/8 w - - 0 1");

        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.WHITE.ordinal();

        // The position where the piece will be set on the bitboard
        int position = 10;


        // Call the method to be tested
        chessBoardEmpty.setBitboards(PieceType.PAWN, Color.WHITE, position);

        // Retrieve the bitboard
        long[][] bitboards = chessBoardEmpty.getBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        // Check that the bitboard was updated correctly
        assertEquals(expectedBitboardValue, bitboards[pieceTypeOrdinal][colorOrdinal]);
    }

    @Test
    public void testSetBitboardsBlack() {

        ChessBoard chessBoardEmpty = new ChessBoard("8/8/8/8/8/8/8/8 w - - 0 1");

        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.BLACK.ordinal();

        // The position where the piece will be set on the bitboard
        int position = 55;


        // Call the method to be tested
        chessBoardEmpty.setBitboards(PieceType.PAWN, Color.BLACK, position);

        // Retrieve the bitboard
        long[][] bitboards = chessBoardEmpty.getBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        // Check that the bitboard was updated correctly
        assertEquals(expectedBitboardValue, bitboards[pieceTypeOrdinal][colorOrdinal]);
    }

    @Test
    public void testSetPiece()
    {
        ChessBoard chessBoardEmpty = new ChessBoard("8/8/8/8/8/8/8/8 w - - 0 1");
        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.WHITE.ordinal();

        System.out.println("pieceTypeOrdinal: "+ pieceTypeOrdinal +"\ncolorOrdinal: " + colorOrdinal);

        // The position where the piece will be set on the bitboard
        int row = 1 ;
        int column = 2 ;

        // expected computed position by setPiece
        int position = 10;

        assertEquals(0L, chessBoardEmpty.getBitboards()[pieceTypeOrdinal][colorOrdinal]);

        // Call the method to be tested
        chessBoardEmpty.setPiece(PieceType.PAWN, Color.WHITE, row, column);

        // Retrieve the bitboard
        long[][] bitboards = chessBoardEmpty.getBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        System.out.println(expectedBitboardValue + "  " + bitboards[pieceTypeOrdinal][colorOrdinal]);

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

        System.out.println(board[0][Color.WHITE.ordinal()] + " PAWNS " + whitePawns);
        System.out.println(board[0][Color.BLACK.ordinal()]+ " PAWNS " + blackPawns);
        assertEquals(board[0][Color.WHITE.ordinal()], whitePawns);
        assertEquals(board[0][Color.BLACK.ordinal()], blackPawns);

        /**
         * Issue with the asserEquals here, fix the bitboards
         */

        System.out.println(board[1][Color.WHITE.ordinal()] + " ROOKS " + whiteRooks);
        System.out.println(board[1][Color.BLACK.ordinal()] + " ROOKS " + blackRooks);

        assertEquals(board[1][Color.WHITE.ordinal()], whiteRooks);
        assertEquals(board[1][Color.BLACK.ordinal()], blackRooks);

        System.out.println(board[2][Color.WHITE.ordinal()] == whiteKnights);
        System.out.println(board[2][Color.BLACK.ordinal()] == blackKnights);
        assertEquals(board[2][Color.WHITE.ordinal()], whiteKnights);
        assertEquals(board[2][Color.BLACK.ordinal()], blackKnights);

        System.out.println(board[3][Color.WHITE.ordinal()] == whiteBishops);
        System.out.println(board[3][Color.BLACK.ordinal()] == blackBishops);
        assertEquals(board[3][Color.WHITE.ordinal()], whiteBishops);
        assertEquals(board[3][Color.BLACK.ordinal()], blackBishops);

        System.out.println(board[4][Color.WHITE.ordinal()] == whiteQueen);
        System.out.println(board[4][Color.BLACK.ordinal()] == blackQueen);

        System.out.println(board[5][Color.WHITE.ordinal()] == whiteKing);
        System.out.println(board[5][Color.BLACK.ordinal()] == blackKing);
        assertEquals(board[4][Color.WHITE.ordinal()], whiteQueen);
        assertEquals(board[4][Color.BLACK.ordinal()], blackQueen);


        assertEquals(board[5][Color.WHITE.ordinal()], whiteKing);
        assertEquals(board[5][Color.BLACK.ordinal()], blackKing);
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