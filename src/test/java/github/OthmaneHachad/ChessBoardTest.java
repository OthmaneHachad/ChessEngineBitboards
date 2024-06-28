package github.OthmaneHachad;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    public void testsetLayoutBitboardsWhite() {

        ChessBoard chessBoardEmpty = new ChessBoard("8/8/8/8/8/8/8/8 w - - 0 1", false);

        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.WHITE.ordinal();

        // The position where the piece will be set on the bitboard
        int position = 10;


        // Call the method to be tested
        chessBoardEmpty.setLayoutBitboards(PieceType.PAWN, Color.WHITE, position);

        // Retrieve the bitboard
        long[][] bitboards = chessBoardEmpty.getLayoutBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        // Check that the bitboard was updated correctly
        assertEquals(expectedBitboardValue, bitboards[pieceTypeOrdinal][colorOrdinal]);
    }

    @Test
    public void testsetLayoutBitboardsBlack() {

        ChessBoard chessBoardEmpty = new ChessBoard("8/8/8/8/8/8/8/8 w - - 0 1", false);

        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.BLACK.ordinal();

        // The position where the piece will be set on the bitboard
        int position = 55;


        // Call the method to be tested
        chessBoardEmpty.setLayoutBitboards(PieceType.PAWN, Color.BLACK, position);

        // Retrieve the bitboard
        long[][] bitboards = chessBoardEmpty.getLayoutBitboards();

        // Calculate the expected bitboard value
        long expectedBitboardValue = 1L << position;

        // Check that the bitboard was updated correctly
        assertEquals(expectedBitboardValue, bitboards[pieceTypeOrdinal][colorOrdinal]);
    }

    @Test
    public void testSetPiece()
    {
        ChessBoard chessBoardEmpty = new ChessBoard("8/8/8/8/8/8/8/8 w - - 0 1", false);
        // Assume that 0 is the ordinal for PAWN and 1 is for WHITE
        int pieceTypeOrdinal = PieceType.PAWN.ordinal();
        int colorOrdinal = Color.WHITE.ordinal();

        System.out.println("pieceTypeOrdinal: "+ pieceTypeOrdinal +"\ncolorOrdinal: " + colorOrdinal);

        // The position where the piece will be set on the bitboard
        int row = 1 ;
        int column = 2 ;

        // expected computed position by setPiece
        int position = 10;

        assertEquals(0L, chessBoardEmpty.getLayoutBitboards()[pieceTypeOrdinal][colorOrdinal]);

        // Call the method to be tested
        chessBoardEmpty.setPiece(PieceType.PAWN, Color.WHITE, row, column);

        // Retrieve the bitboard
        long[][] bitboards = chessBoardEmpty.getLayoutBitboards();

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
        long[][] board = chessBoard.getLayoutBitboards() ;
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
        boolean squareOccupied  = chessBoard.isSquareOccupied(3,3);
        assertEquals(false, squareOccupied);
    }

    @Test
    void testIsSquareOccupiedTrue() {
        boolean squareOccupied  = chessBoard.isSquareOccupied(0,3);
        assertEquals(true, squareOccupied);
    }


    @Test
    void testIsKingChecked() {

        // Outline:
            // one case per piece per color
            // total of 11 tests

        // Black King Checked
        // By queen
        ChessBoard cb_1 = new ChessBoard("3q4/r1p5/1p2P1N1/1R5K/2P2r2/Pp2n3/1Q1kP3/8 w - - 0 1");
        assertEquals(true, cb_1.isKingChecked(Color.BLACK));
        assertEquals(false, cb_1.isKingChecked(Color.WHITE));

        // by rook
        ChessBoard cb_2 = new ChessBoard("3q4/r1p5/1p2P1N1/1R5K/2P2r2/Pp2n3/1R1kP3/8 w - - 0 1");
        assertEquals(true, cb_2.isKingChecked(Color.BLACK));
        assertEquals(false, cb_2.isKingChecked(Color.WHITE));

        // by bishop
        ChessBoard cb_3 = new ChessBoard("3q4/r1p5/1p2P1N1/1R5K/1BP2r2/Pp2n3/1r1kP3/8 w - - 0 1");
        assertEquals(true, cb_3.isKingChecked(Color.BLACK));
        assertEquals(false, cb_3.isKingChecked(Color.WHITE));

        // by knight
        ChessBoard cb_4 = new ChessBoard("3q4/r1p5/1p2P1N1/1R5K/1BP2r2/Pp3N2/1r1kP3/8 w - - 0 1");
        assertEquals(true, cb_4.isKingChecked(Color.BLACK));
        assertEquals(false, cb_4.isKingChecked(Color.WHITE));

        // by pawn
        ChessBoard cb_5 = new ChessBoard("3q4/r1p5/1p2P1N1/1R5K/1BP2r2/Pp1k1N2/1r2P3/8 w - - 0 1");
        assertEquals(true, cb_5.isKingChecked(Color.BLACK));
        assertEquals(false, cb_5.isKingChecked(Color.WHITE));

        // ---------------------------------------------------------------------------

        // White King Checked
        // By queen
        ChessBoard cb_7 = new ChessBoard("3Q4/r1p5/1p2P1N1/7k/2P2r2/Pp2n3/1q1KP3/8 w - - 0 1");
        assertEquals(false, cb_7.isKingChecked(Color.BLACK));
        assertEquals(true, cb_7.isKingChecked(Color.WHITE));

        // by rook
        ChessBoard cb_8 = new ChessBoard("3Q4/r1p5/1p2P1N1/7k/2P2r2/Pp2n3/1r1KP3/8 w - - 0 1");
        assertEquals(false, cb_8.isKingChecked(Color.BLACK));
        assertEquals(true, cb_8.isKingChecked(Color.WHITE));

        // by bishop
        ChessBoard cb_9 = new ChessBoard("3Q4/r1p5/1p2P1N1/7k/1bP2r2/Pp2n3/1R1KP3/8 w - - 0 1");
        assertEquals(false, cb_9.isKingChecked(Color.BLACK));
        assertEquals(true, cb_9.isKingChecked(Color.WHITE));

        // by knight
        ChessBoard cb_10 = new ChessBoard("3Q4/r1p5/1p2P1N1/7k/1BP2r2/Pp3n2/1R1KP3/8 w - - 0 1");
        assertEquals(false, cb_10.isKingChecked(Color.BLACK));
        assertEquals(true, cb_10.isKingChecked(Color.WHITE));

        // by pawn
        ChessBoard cb_11 = new ChessBoard("3Q4/r1p5/1p2P1N1/7k/1Bp2r2/Pp1K1N2/1r2P3/8 w - - 0 1");
        assertEquals(false, cb_11.isKingChecked(Color.BLACK));
        assertEquals(true, cb_11.isKingChecked(Color.WHITE));

        //EDGE cases
        // by opposite king
        ChessBoard cb_6 = new ChessBoard("3q4/r1p5/1p2P1N1/1R5K/1BP2rk1/Pp3N2/1r2P3/8 w - - 0 1");
        assertEquals(true, cb_6.isKingChecked(Color.BLACK));
        assertEquals(true, cb_6.isKingChecked(Color.WHITE));




        //fail("Not yet implemented");
    }


    @Test
    void testIsSameColor() {
        ChessBoard cb = new ChessBoard("3Q4/r1p5/1p2P1N1/7k/1Bp2r2/Pp1K1N2/1r2P3/8 w - - 0 1");
        boolean notSameColor = cb.isSameColor(1, 1, Color.WHITE);
        boolean sameColor = cb.isSameColor(1, 1, Color.BLACK);

        assertEquals(false, notSameColor);
        assertEquals(true, sameColor);
    }

    @Test
    void testMovePiece()
    {
        EngineCore engine = new EngineCore() ;
        ChessBoard cb = new ChessBoard("8/3K2k1/1B1pP3/5B2/1pp3p1/3nPNn1/1b6/q2b4 w - - 0 1");
        Move wBishopCaptureBKnight = new Move(37, 19, PieceType.BISHOP, Color.WHITE, PieceType.KNIGHT);

        long wBishopBefore = cb.getLayoutBitboards()[PieceType.BISHOP.ordinal()][Color.WHITE.ordinal()];
        long bKnightBefore = cb.getLayoutBitboards()[PieceType.KNIGHT.ordinal()][Color.BLACK.ordinal()];

        long whitePiecesBefore = cb.getWhiteBitboard() ;
        long blackPiecesBefore = cb.getBlackBitboard() ;

        cb.movePiece(wBishopCaptureBKnight);

        long wBishopAfter = cb.getLayoutBitboards()[PieceType.BISHOP.ordinal()][Color.WHITE.ordinal()];
        long bKnightAfter = cb.getLayoutBitboards()[PieceType.KNIGHT.ordinal()][Color.BLACK.ordinal()];

        long whitePiecesAfter = cb.getWhiteBitboard() ;
        long blackPiecesAfter = cb.getBlackBitboard() ;

        // 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
        // 0b00000000_00000000_00000000_00000010_00000000_00001000_00000000_00000000L
        // assert moving piece layout bitboard updated

        System.out.println("w bishop after: ") ;
        System.out.println(Long.toBinaryString(wBishopAfter)) ;
        System.out.println(Long.toBinaryString(0b00000000_00000000_00000010_00000000_00000000_00001000_00000000_00000000L)) ;

        // Bishop
        assertEquals(
                0b00000000_00000000_00000010_00100000_00000000_00000000_00000000_00000000L,
                wBishopBefore
        );

        assertEquals(
                0b00000000_00000000_00000010_00000000_00000000_00001000_00000000_00000000L,
                wBishopAfter
        );

        //Knight
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_01001000_00000000_00000000L,
                bKnightBefore
        );
        assertEquals(
                0b00000000_00000000_00000000_00000000_00000000_01000000_00000000_00000000L,
                bKnightAfter
        );

        // all white pieces
        assertEquals(
                0b00000000_00001000_00010010_00100000_00000000_00110000_00000000_00000000L,
                whitePiecesBefore
        );
        assertEquals(
                0b00000000_00001000_00010010_00000000_00000000_00111000_00000000_00000000L,
                whitePiecesAfter
        );

        // all black pieces
        assertEquals(
                0b00000000_01000000_00001000_00000000_01000110_01001000_00000010_00001001L,
                blackPiecesBefore
        );
        assertEquals(
                0b00000000_01000000_00001000_00000000_01000110_01000000_00000010_00001001L,
                blackPiecesAfter
        );

        // assert color bitboard has been updated
        // if a piece was captured
            // assert piece layout bitboard updated
            // assert color bitboard has been updated
        // assert that the kings are in the correct check states
        // assert the FEN data updates are correct

    }


    @Test
    void testEnPassantMovePiece() {

        EngineCore engine = new EngineCore() ;
        ChessBoard cb_en_passant = new ChessBoard("rnbqkbnr/ppp1pppp/8/3pP3/8/8/PPP1PPPP/RNBQKBNR b KQkq e6 0 2");
        System.out.println(cb_en_passant.getEnPassantTargetSquare()) ;

        long whitePawnsAfterEnPassant = 0b00000000_00000000_00001000_00000000_00000000_00000000_11110111_00000000L ;
        long blackPawnsAfterEnPassant = 0b00000000_11110111_00000000_00000000_00000000_00000000_00000000_00000000L ;

        System.out.println(cb_en_passant) ;


        Move whitePawnEnPassantOnBlack = new Move(36, 43, PieceType.PAWN, Color.WHITE, PieceType.PAWN) ;
        cb_en_passant.movePiece(whitePawnEnPassantOnBlack);

        System.out.println("white: \n"  + Long.toBinaryString(cb_en_passant.getLayoutBitboards()[PieceType.PAWN.ordinal()][Color.WHITE.ordinal()]) + "\n" + Long.toBinaryString(whitePawnsAfterEnPassant)) ;
        System.out.println("black: \n"  + Long.toBinaryString(cb_en_passant.getLayoutBitboards()[PieceType.PAWN.ordinal()][Color.BLACK.ordinal()]) + "\n" + Long.toBinaryString(blackPawnsAfterEnPassant)) ;

        System.out.println(cb_en_passant) ;


        assertEquals(true, cb_en_passant.getLayoutBitboards()[PieceType.PAWN.ordinal()][Color.WHITE.ordinal()] == whitePawnsAfterEnPassant);
        assertEquals(true, cb_en_passant.getLayoutBitboards()[PieceType.PAWN.ordinal()][Color.BLACK.ordinal()] == blackPawnsAfterEnPassant);

    }

    @Test
    void testUndoMove()
    {
        EngineCore engine = new EngineCore() ;
        ChessBoard cb = new ChessBoard("8/3K2k1/1B1pP3/5B2/1pp3p1/3nPNn1/1b6/q2b4 w - - 0 1");
        Move wBishopCaptureBKnight = new Move(37, 19, PieceType.BISHOP, Color.WHITE, PieceType.KNIGHT);

        long whitePiecesBefore = cb.getWhiteBitboard() ;
        long blackPiecesBefore = cb.getBlackBitboard() ;

        long bishopBitboard = cb.getLayoutBitboards()[PieceType.BISHOP.ordinal()][Color.WHITE.ordinal()];
        long knightBitboard = cb.getLayoutBitboards()[PieceType.KNIGHT.ordinal()][Color.BLACK.ordinal()];

        // make the move
        cb.movePiece(wBishopCaptureBKnight);

        long whitePiecesAfter = cb.getWhiteBitboard() ;
        long blackPiecesAfter = cb.getBlackBitboard() ;



        // undo the move
        cb.undoMove();



        // all white pieces
        assertEquals(
                0b00000000_00001000_00010010_00100000_00000000_00110000_00000000_00000000L,
                cb.getWhiteBitboard()
        );

        // all black pieces
        assertEquals(
                0b00000000_01000000_00001000_00000000_01000110_01001000_00000010_00001001L,
                cb.getBlackBitboard()
        );

        assertEquals(
                bishopBitboard,
                cb.getLayoutBitboards()[PieceType.BISHOP.ordinal()][Color.WHITE.ordinal()]
        );

        assertEquals(
                knightBitboard,
                cb.getLayoutBitboards()[PieceType.KNIGHT.ordinal()][Color.BLACK.ordinal()]
        );


    }

    @Test
    void testGetPiecePosition()
    {
        EngineCore engine = new EngineCore() ;
        ChessBoard cb = new ChessBoard("8/3K2k1/1B1pP3/5B2/1pp3p1/3nPNn1/1b6/q2b4 w - - 0 1");

        ArrayList<Integer> KingPositions = cb.getPiecePosition(PieceType.KING, Color.WHITE); // 1L << 51
        assertEquals(51, KingPositions.get(0));
    }

    @Test
    void testSetWhiteBitboard() {
        // TODO: Implement this test testSetWhiteBitboard
        //fail("Not yet implemented");
    }

    @Test
    void testSetBlackBitboard() {
        // TODO: Implement this test testSetBlackBitboard
        //fail("Not yet implemented");
    }


}