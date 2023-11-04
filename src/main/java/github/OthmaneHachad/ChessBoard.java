package github.OthmaneHachad;

import java.util.HashMap;


/**
 * Notes:
 *      - board represented through bitboards
 *      - board is represented from top (black pieces) to bottom (white pieces)
 *      - in terms of bitboard, white pieces are lower values while black pieces are higher values
 *          - following this pattern, we don't have position attribution issues
 */
public class ChessBoard {

    // creates a 6x2 array of long values (64 bits)
    private long[][] bitboards ;
    private long whiteBitboard ;
    private long blackBitboard ;
    private HashMap<String, String> boardInfo ;

    // constructor to build the default position
    public ChessBoard()
    {
        bitboards = new long[PieceType.values().length][Color.values().length] ;
        setupStartingPosition();

        this.setWhiteBitboard();
        this.setBlackBitboard();
    }

    // constructor to build a specific position
    public ChessBoard(String fen)
    {
        bitboards = new long[PieceType.values().length][Color.values().length] ;
        setupPosition(fen);
        this.setBoardInfo(fen);

        this.setWhiteBitboard();
        this.setBlackBitboard();
    }


    public void movePiece(Move move)
    {

        // first OR operation with Piece bitboard
        long pieceBitboard = this.getBitboards()[move.getPiece().ordinal()][move.getColor().ordinal()];

        long startPositionBitMask = 1L << move.getStartPosition() ;
        long endPositionBitMask = 1L << move.getEndPosition() ;

        // clear start position - perform AND operation with negating (NOT) startPositionBitMask
        pieceBitboard = pieceBitboard & ~startPositionBitMask ;

        // fill end position - setBitboards()
        pieceBitboard = pieceBitboard | endPositionBitMask ;


        // update bitboards
        this.getBitboards()[move.getPiece().ordinal()][move.getColor().ordinal()] = pieceBitboard ;

        // TODO: compute and return updated FEN String

    }


    public boolean isSquareOccupied(int row, int column)
    {
        long mask = 1L << (row * 8 + column);
        // get a bitboard of occupied squares whether black or white
        long occupationBitboard = this.whiteBitboard | this.blackBitboard ;

        // if non-zero square occupied (true)
        // - if zero square not occupied (false)
        return (occupationBitboard & mask) != 0 ;
    }

    public boolean isSameColor(int x, int y, Color color)
    {
        // get the Color bitboards: 64 bitboards for each color side
        //get the color bitboard of the Color parameter
        long colorBitboard = color == Color.WHITE ? this.whiteBitboard : this.blackBitboard ;

        // perform bitwise AND with 1L << position of the x,y
        long newColorBitboard = colorBitboard | 1L << (x * 8 + y);

        // if bitboard decimal value has changed, it is not the same color
        return newColorBitboard == colorBitboard ;
        // TODO: test this method
    }

    /**
     * setupStartingPosition will update the bitboards according to default 
     *      starting position
     */
    public void setupStartingPosition(){
        setupPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        this.setBoardInfo("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }


    /**
     * setupPosition will update the bitboards according to the FEN string representation
     * @param fen is the fen string representing the board + game state
     */
    public void setupPosition(String fen) {
        // Parse the FEN string to translate correctly to the 64 bitboard
        String[] board = this.parseFen(fen).get("board_layout").split("/");
        for (int s = board.length -1; s >= 0; s--) {
            // s initialized at 7 because black pieces at the top values
            int column = 0;  // Initialize column counter
            System.out.println(board[s]);

            for (int i = 0; i < board[s].length(); i++) {
                char ch = board[s].charAt(i);
                if (Character.isDigit(ch)) {
                    // If the character is a digit, skip the corresponding number of squares
                    column += Character.getNumericValue(ch);
                } else {
                    // Otherwise, update the bitboards
                    Color c = Character.isLowerCase(ch) ? Color.BLACK : Color.WHITE;
                    setPiece(charToPieceType(ch), c, s, column);
                    column++;  // Increment the column counter
                }
            }
        }
    }


    /**
     * 
     * @param pieceType is the type of piece we will update
     *      i.e Pawn
     * @param color is the color type of the piece we will update
     *      i.e Black/White Pawn
     * @param row is the row at which the piece is located
     * @param column is the column at which the piece is located
     * 
     * setPiece() will place a Piece on the board
     * 
     */
    public void setPiece(PieceType pieceType, Color color, int row, int column)
    {
        int position = row * 8 + column ;
        this.setBitboards(pieceType, color, position);
        
    }

    /**
     * charToPieceType performs translation between FEN piece representation and
     *      enum piece representation
     * @param ch is the string representation of a piece in the FEN string
     * @return the pieceType associated to ch
     */
    public PieceType charToPieceType(char ch) {
        switch (Character.toLowerCase(ch)) {
            case 'p': return PieceType.PAWN;
            case 'r': return PieceType.ROOK;
            case 'n': return PieceType.KNIGHT;
            case 'b': return PieceType.BISHOP;
            case 'q': return PieceType.QUEEN;
            case 'k': return PieceType.KING;
            default: throw new IllegalArgumentException("Invalid piece character: " + ch);
        }
    }

    public char pieceTypeToChar(PieceType piece, Color color) {
        char ch = '/';
        switch (piece) {
            case PAWN: ch = 'P'; break ;
            case ROOK: ch = 'R'; break ;
            case KNIGHT: ch = 'N'; break ;
            case BISHOP: ch = 'B'; break ;
            case QUEEN: ch = 'Q'; break ;
            case KING: ch = 'K'; break ;
            default: throw new IllegalArgumentException("Invalid piece character: " + ch);
        }
        // checks if the piece is white or black to return
        //      a lowercase or uppercase character
        return color == Color.BLACK ? Character.toLowerCase(ch) : ch ;
    }

    /**
     * 
     * @param fen is the notation in which the board is inputted
     *      simplest format for setup a position + game state
     * @return
     */
    public HashMap<String, String> parseFen(String fen)
    {
        // "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

        HashMap<String, String> boardInfo = new HashMap<>();

        boardInfo.put("board_layout", fen.split(" ")[0]);
        boardInfo.put("playing", fen.split(" ")[1]);
        boardInfo.put("rock", fen.split(" ")[2]);
        boardInfo.put("en_passant", fen.split(" ")[3]);
        boardInfo.put("full_move_clock", fen.split(" ")[4]);
        boardInfo.put("half_move_clock", fen.split(" ")[5]);

        return boardInfo;
    }

    /**
     * getter method fro bitboards
     * @return the 2D-array of long values
     */
    public long[][] getBitboards()
    {
        return this.bitboards;
    }

    public long getWhiteBitboard()
    {
        return this.whiteBitboard ;
    }

    public void setWhiteBitboard()
    {
        this.whiteBitboard = 0L ;
        for (int i = 0; i < this.bitboards.length; i++)
        {
            this.whiteBitboard |= this.bitboards[i][0] ;
        }
    }

    public long getBlackBitboard()
    {
        return this.blackBitboard ;
    }

    public void setBlackBitboard()
    {
        this.blackBitboard = 0L ;
        for (int i = 0; i < this.bitboards.length; i++)
        {
            this.blackBitboard |= this.bitboards[i][1] ;
        }
    }


    /**
     * setBitboards performs bitwise operation to put a piece on the board
     * 
     * @param pieceType is the type of piece we will update
     *      i.e Pawn
     * @param color is the color type of the piece we will update
     *      i.e Black/White Pawn
     * @param position is the position from 0-63
     */
    public void setBitboards(PieceType pieceType, Color color, int position)
    {
            this.bitboards[pieceType.ordinal()][color.ordinal()] |= (1L << position);
            // 000000000000...0000000 || 000000010000...0000000 = 000000010000...0000000
    }

    public HashMap<String, String> getBoardInfo()
    {
        return this.boardInfo ;
    }

    public void setBoardInfo(String fen)
    {
        this.boardInfo = this.parseFen(fen);
    }

    @Override
    public String toString() {
        String boardToString = "" ;
        for (int row = 0; row < 8 ; row++) { // Start from the top row (black's side)
            for (int col = 0; col < 8; col++) {
                int position = row * 8 + col;
                char pieceChar = '.';
                for (PieceType pieceType : PieceType.values()) {
                    for (Color color : Color.values()) {
                        if ((bitboards[pieceType.ordinal()][color.ordinal()] & (1L << position)) != 0) {
                            pieceChar = pieceTypeToChar(pieceType, color);
                            break;
                        }
                    }
                }
                boardToString += pieceChar + " ";
            }
            boardToString += "\n";
        }
        return boardToString ;
    }
    /** OUTPUT
     * r n b q k b n r
     * p p p p p p p p
     * . . . . . . . .
     * . . . . . . . .
     * . . . . . . . .
     * . . . . . . . .
     * P P P P P P P P
     * R N B Q K B N R
     */

}