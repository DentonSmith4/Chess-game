public class Board {

    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
    }

    //returns the piece at given location
    public Piece getPiece(int row, int col) {
        if (isValidPosition(row, col)) {
            return board[row][col];
        } else {
            return null; // Return null for out-of-bounds positions
        }
    }
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    //set given piece at given location
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        // Verify that the source and destination are valid
        if (board[startRow][startCol] == null){
            System.out.println("Bruv update the piece loco");
            return false;
        }
        if (!verifySourceAndDestination(startRow, startCol, endRow, endCol, board[startRow][startCol].getIsBlack())) {
            return false;
        }

        Piece piece = getPiece(startRow, startCol);

        // Check if the move is legal for the piece
        if (!piece.isMoveLegal(this, endRow, endCol)) {
            return false;
        }

        // Update the board: remove the piece from the source and place it at the destination
        setPiece(endRow, endCol, piece);
        board[endRow][endCol].setPosition(endRow, endCol);
        setPiece(startRow, startCol, null);


        return true;
    }

    public boolean isGameOver() {
        boolean whiteKingFound = false;
        boolean blackKingFound = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getPiece(i, j);
                if (piece != null) {
                    if (piece.getChar() == '\u2654') { // White king
                        whiteKingFound = true;
                    } else if (piece.getChar() == '\u265A') { // Black king
                        blackKingFound = true;
                    }
                }
            }
        }

        return !(whiteKingFound && blackKingFound);
    }

    public String toString (){
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    public void clear () {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }
    }

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {

        if (startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8
                && endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8) {
            Piece startPiece = getPiece(startRow, startCol);
            if (startPiece == null) {
                System.out .println("Start piece positon not found");
                return false;
            }
            if (startPiece.getIsBlack() != isBlack) {
                System.out .println("Not the right piece color");
                return false;
            }
            Piece endPiece = getPiece(endRow, endCol);
            return endPiece == null || endPiece.getIsBlack() != isBlack;
        }
        return false;
    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        // Calculate the absolute difference between the start and end rows and columns
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);

        // The move is considered adjacent if the absolute differences are both 1 or less
        if (rowDiff <= 1 && colDiff <= 1) {
            return true;
        }

        // If the absolute differences are greater than 1, the move is not adjacent
        return false;
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            int colStep = Integer.compare(endCol, startCol);

            for (int col = startCol + colStep; col != endCol; col += colStep) {
                Piece piece = getPiece(startRow, col);
                if (piece != null) {
                    System.out.println("There's a piece in the way.");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol == endCol) {
            int step = Integer.compare(endRow, startRow);

            for (int row = startRow + step; row != endRow; row += step) {
                Piece piece = getPiece(row, startCol);
                if (piece != null) {
                    System.out.println("There's a piece in the way.");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }



    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        if (rowDiff == colDiff) {
            int rowStep = Integer.compare(endRow, startRow);
            int colStep = Integer.compare(endCol, startCol);
            for (int i = 1; i < rowDiff; i++) {
                Piece piece = getPiece(startRow + i * rowStep, startCol + i * colStep);
                if (piece != null) {
                    System.out.println("There's a piece in the way.");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}