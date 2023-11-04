package github.OthmaneHachad;

public class Move {

    private int startPosition ;
    private int endPosition ;
    private PieceType piece ;
    private Color color ;
    private PieceType pieceCaptured ;

    public Move(int start, int end, PieceType p, Color c, PieceType pCaptured)
    {
        this.startPosition = start ;
        this.endPosition = end ;
        this.piece = p ;
        this.color = c;
        this.pieceCaptured = pCaptured ;
    }

    public int getStartPosition()
    {
        return this.startPosition ;
    }

    public int getEndPosition()
    {
        return this.endPosition ;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPiece() {
        return piece;
    }

    public PieceType getPieceCaptured() {
        return pieceCaptured;
    }
}
