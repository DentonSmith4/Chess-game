public class Piece {
    // Create Instance Variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    public char getChar(){
        return character;
    }
    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return isBlack;
    }

    /**
     * Handle promotion of a pawn.
     * @param row Current row of the pawn
     * @param isBlack Color of the pawn
     */


    //NEED TO finish promote Pawn with rest of characters.
    public boolean promotePawn(String promoType) {
        boolean stillPawn = true;
        if (promoType.equalsIgnoreCase("Queen")) {
            character = isBlack ? '\u265B' : '\u2655';
            stillPawn=false;
        }
        else if (promoType.equalsIgnoreCase("Rook")) {
            character = isBlack ? '\u265C' : '\u2656';
            stillPawn =false;
        }
        else if (promoType.equalsIgnoreCase("Bishop")) {
            character = isBlack ? '\u265D' : '\u2657';
            stillPawn = false;
        }
        else if (promoType.equalsIgnoreCase("Knight")) {
            character = isBlack ? '\u265E' : '\u2658';
            stillPawn = false;
        }
        else{
            System.out.println("Invalid input. Enter the name of the replacement piece (Queen, Rook, Bishop, Knight): ");
        }
        // Clear the scanner's input buffer
        return stillPawn;
    }
    //    public void promotePawn(int row){
//        Scanner ns = new Scanner(System.in);
//        System.out.println("Pawn Promoted: Enter the first character of the replacement piece (q:Queen, r:Rook, b:Bishop, k:Knight):");
//        while(true){
//            String promo = ns.nextLine();
//            if (promo.length() == 1) { //check if input is valid length
//                char newChar =
//
//            }
//            if(row==7){
//        }
//
//        }
//    }
    private boolean isValidPromoType(String promoType) {
        return promoType.equalsIgnoreCase("Queen") || promoType.equalsIgnoreCase("Rook") ||
                promoType.equalsIgnoreCase("Bishop") || promoType.equalsIgnoreCase("Knight");
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        String s = "";
        s+=String.valueOf(character);
        return s;
    }

}
