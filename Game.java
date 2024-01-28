import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board g = new Board();


        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", g);
        System.out.println(g);

        boolean isWhiteTurn = true;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (isWhiteTurn) {
                System.out.println("White's Turn");
            } else {
                System.out.println("Black's Turn");
            }

            System.out.println("Enter your move (format: [start row] [start col] [end row] [end col]):");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                System.out.println("Quitting the game. Thanks for playing!");
                break;
            } else if (userInput.equalsIgnoreCase("new game") || userInput.equalsIgnoreCase("reset")) {
                g.clear();
                Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", g);
                isWhiteTurn = true;
                System.out.println(g);
                continue;
            }

            String[] inputParts = userInput.split(" ");
            if (inputParts.length != 4) {
                System.out.println("Invalid input format. Please try again.");
                continue;
            }

            try {
                int startRow = Integer.parseInt(inputParts[0]);
                int startCol = Integer.parseInt(inputParts[1]);
                int endRow = Integer.parseInt(inputParts[2]);
                int endCol = Integer.parseInt(inputParts[3]);

                if (g.movePiece(startRow, startCol, endRow, endCol)) {
                    System.out.println("Move successful.");
                    if(endRow==0||endRow ==7){
                        if(g.getPiece(endRow,endCol).getChar()=='\u2659'||g.getPiece(endRow,endCol).getChar()=='\u265f') {
                            System.out.println("Pawn Promoted: Enter the name of the replacement piece (Queen, Rook, Bishop, Knight):");
                            scanner = new Scanner(System.in);
                            String promote = scanner.nextLine();
                            while(g.getPiece(endRow,endCol).promotePawn(promote)){
                                System.out.println("Invalid Input.Enter the name of the replacement piece (Queen, Rook, Bishop, Knight): ");
                                promote= scanner.nextLine();
                            }
                        }
                    }
                    isWhiteTurn = !isWhiteTurn;

                    if (g.isGameOver()) {
                        System.out.println("Game over!");
                        break;
                    }
                } else {
                    System.out.println("Invalid move. Please try again.");
                }

                System.out.println(g);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for row and column.");
            }
        }
    }
}
