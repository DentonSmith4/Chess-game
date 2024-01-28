public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - row);
        int colDiff = Math.abs(endCol - col);

        // Queen can move horizontally, vertically, or diagonally
        if (rowDiff == 0 || colDiff == 0 || rowDiff == colDiff) {
            // Check if there are any pieces in the path
            int rowDirection = Integer.compare(endRow, row);
            int colDirection = Integer.compare(endCol, col);
            int currentRow = row + rowDirection;
            int currentCol = col + colDirection;

            while (currentRow != endRow || currentCol != endCol) {
                if (board.getPiece(currentRow, currentCol) != null) {
                    return false; // Path is blocked
                }
                currentRow += rowDirection;
                currentCol += colDirection;
            }

            Piece destinationPiece = board.getPiece(endRow, endCol);
            return destinationPiece == null || destinationPiece.getIsBlack() != isBlack;
        }

        return false;
    }
}
