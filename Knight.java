public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - row);
        int colDiff = Math.abs(endCol - col);

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {

            Piece destinationPiece = board.getPiece(endRow, endCol);
            return destinationPiece == null || destinationPiece.getIsBlack()!= isBlack;
        }

        return false;
    }
}