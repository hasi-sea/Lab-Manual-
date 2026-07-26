import java.util.Scanner;

// ==========================================
// 1. SPOT CLASS (Encapsulation)
// Represents a single block on the chessboard.
// ==========================================
class Spot {
    private int x, y;
    private Piece piece;

    public Spot(int x, int y, Piece piece) {
        this.setX(x);
        this.setY(y);
        this.setPiece(piece);
    }

    public Piece getPiece() { return this.piece; }
    public void setPiece(Piece p) { this.piece = p; }
    public int getX() { return this.x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return this.y; }
    public void setY(int y) { this.y = y; }
}

// ==========================================
// 2. ABSTRACT PIECE CLASS (Abstraction)
// ==========================================
abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white) {
        this.setWhite(white);
    }

    public boolean isWhite() { return this.white; }
    public void setWhite(boolean white) { this.white = white; }
    public boolean isKilled() { return this.killed; }
    public void setKilled(boolean killed) { this.killed = killed; }

    // Polymorphic method to be implemented by all specific pieces
    public abstract boolean canMove(Board board, Spot start, Spot end);
    public abstract String getSymbol();
}

// ==========================================
// 3. CONCRETE PIECES (Inheritance & Polymorphism)
// ==========================================
class King extends Piece {
    public King(boolean white) { super(white); }
    @Override
    public String getSymbol() { return isWhite() ? "K" : "k"; }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return (x <= 1 && y <= 1);
    }
}

class Queen extends Piece {
    public Queen(boolean white) { super(white); }
    @Override
    public String getSymbol() { return isWhite() ? "Q" : "q"; }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return (x == 0 || y == 0 || x == y); // Straight or Diagonal
    }
}

class Rook extends Piece {
    public Rook(boolean white) { super(white); }
    @Override
    public String getSymbol() { return isWhite() ? "R" : "r"; }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return (x == 0 || y == 0); // Straight only
    }
}

class Bishop extends Piece {
    public Bishop(boolean white) { super(white); }
    @Override
    public String getSymbol() { return isWhite() ? "B" : "b"; }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return (x == y); // Diagonal only
    }
}

class Knight extends Piece {
    public Knight(boolean white) { super(white); }
    @Override
    public String getSymbol() { return isWhite() ? "N" : "n"; }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return (x * y == 2); // L-shape move
    }
}

class Pawn extends Piece {
    public Pawn(boolean white) { super(white); }
    @Override
    public String getSymbol() { return isWhite() ? "P" : "p"; }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int direction = isWhite() ? -1 : 1;
        int xDiff = end.getX() - start.getX();
        int yDiff = Math.abs(start.getY() - end.getY());

        if (yDiff == 0 && end.getPiece() == null && xDiff == direction) return true;
        if (yDiff == 1 && end.getPiece() != null && xDiff == direction) return true; // Capture
        
        return false;
    }
}

// ==========================================
// 4. BOARD CLASS
// Initializes the chessboard with default positions.
// ==========================================
class Board {
    Spot[][] boxes = new Spot[8][8];

    public Board() {
        this.resetBoard();
    }

    public Spot getBox(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) { return null; }
        return boxes[x][y];
    }

    public void resetBoard() {
        // Initialize empty spots
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }
        
        // Initialize White pieces
        boxes[7][0].setPiece(new Rook(true)); boxes[7][1].setPiece(new Knight(true));
        boxes[7][2].setPiece(new Bishop(true)); boxes[7][3].setPiece(new Queen(true));
        boxes[7][4].setPiece(new King(true)); boxes[7][5].setPiece(new Bishop(true));
        boxes[7][6].setPiece(new Knight(true)); boxes[7][7].setPiece(new Rook(true));
        for (int i = 0; i < 8; i++) boxes[6][i].setPiece(new Pawn(true));

        // Initialize Black pieces
        boxes[0][0].setPiece(new Rook(false)); boxes[0][1].setPiece(new Knight(false));
        boxes[0][2].setPiece(new Bishop(false)); boxes[0][3].setPiece(new Queen(false));
        boxes[0][4].setPiece(new King(false)); boxes[0][5].setPiece(new Bishop(false));
        boxes[0][6].setPiece(new Knight(false)); boxes[0][7].setPiece(new Rook(false));
        for (int i = 0; i < 8; i++) boxes[1][i].setPiece(new Pawn(false));
    }

    public void displayBoard() {
        System.out.println("   a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + "  ");
            for (int j = 0; j < 8; j++) {
                Piece p = boxes[i][j].getPiece();
                System.out.print((p == null ? "." : p.getSymbol()) + " ");
            }
            System.out.println(" " + (8 - i));
        }
        System.out.println("   a b c d e f g h\n");
    }
}

// ==========================================
// 5. PLAYER CLASS
// ==========================================
class Player {
    public boolean whiteSide;
    public Player(boolean whiteSide) {
        this.whiteSide = whiteSide;
    }
    public boolean isWhiteSide() {
        return this.whiteSide;
    }
}

// ==========================================
// 6. MOVE CLASS
// ==========================================
class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;

    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }
    // Getters and Setters omitted for brevity but conceptually present
}

// ==========================================
// 7. GAME CLASS (Logic for turns and moves)
// ==========================================
class Game {
    private Player[] players = new Player[2];
    private Board board = new Board();
    private Player currentTurn;

    public void initialize() {
        players[0] = new Player(true); // White
        players[1] = new Player(false); // Black
        currentTurn = players[0];
    }

    public void start() {
        initialize();
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== OOP Chess Game Started ===");
        System.out.println("Type moves like 'e2 e4' or type 'exit' to quit.\n");

        while (true) {
            board.displayBoard();
            String color = currentTurn.isWhiteSide() ? "White" : "Black";
            System.out.print(color + "'s turn. Enter move: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Game Ended.");
                break;
            }

            if(makeMove(input)) {
                // Alternate turns
                currentTurn = (currentTurn == players[0]) ? players[1] : players[0];
            }
        }
        scanner.close();
    }

    private boolean makeMove(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length != 2) {
            System.out.println("Error: Invalid move format.");
            return false;
        }

        Spot start = parseSpot(parts[0]);
        Spot end = parseSpot(parts[1]);

        if (start == null || end == null) {
            System.out.println("Error: Out of bounds.");
            return false;
        }

        Piece sourcePiece = start.getPiece();
        if (sourcePiece == null) {
            System.out.println("Error: No piece at start position.");
            return false;
        }
        if (sourcePiece.isWhite() != currentTurn.isWhiteSide()) {
            System.out.println("Error: Not your piece!");
            return false;
        }

        // Validate according to movement rules
        if (sourcePiece.canMove(board, start, end)) {
            Piece destPiece = end.getPiece();
            if (destPiece != null) {
                destPiece.setKilled(true);
                System.out.println(">>> " + destPiece.getClass().getSimpleName() + " captured! <<<");
            }
            end.setPiece(sourcePiece);
            start.setPiece(null);
            return true;
        } else {
            System.out.println("Error: Invalid move for " + sourcePiece.getClass().getSimpleName());
            return false;
        }
    }

    private Spot parseSpot(String s) {
        if (s.length() != 2) return null;
        int y = s.charAt(0) - 'a';
        int x = 8 - Character.getNumericValue(s.charAt(1));
        return board.getBox(x, y);
    }
}

// ==========================================
// 8. MAIN CLASS (Entry Point)
// ==========================================
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
