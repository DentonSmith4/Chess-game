public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - row);
        int colDiff = Math.abs(endCol - col);

        // A king can move one square in any direction.
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if there's no piece at the destination or if the destination piece is of a different color.
            Piece destinationPiece = board.getPiece(endRow, endCol);
            return destinationPiece == null || destinationPiece.getIsBlack() != isBlack;
        }

        return false;
    }
}