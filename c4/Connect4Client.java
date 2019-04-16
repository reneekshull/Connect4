package c4;

import java.io.*;
import java.net.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class Connect4Client extends c4.Connect4GUI  implements c4.Connect4Constants {
    // Indicate whether the player has the turn
    private boolean myTurn = false;

    // Indicate the token for the player
    private char myToken = ' ';

    // Indicate the token for the other player
    private char otherToken = ' ';

    // Create and initialize cells
    private Cell[][] cell =  new Cell[ROW_SIZE][COL_SIZE];

    // Create and initialize a title label
    private Label lblTitle = new Label();

    // Create and initialize a status label
    private Label lblStatus = new Label();

    // Indicate selected row and column by the current move
    private int rowSelected;
    private int columnSelected;

    // Input and output streams from/to server
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    // Continue to play?
    private boolean continueToPlay = true;

    // Wait for the player to mark a cell
    private boolean waiting = true;

    // Host name or ip
    private String host = "localhost";
    private Socket socket;

    @Override
    public void start(Stage primaryStage) {

        connectToServer();

        Connect4GUI matrix = new Connect4GUI();
        Button bt[] = new Button[COLUMNS];
        for (int i = 0; i < bt.length; i++) {
            bt[i] = new Button("Col " + (i + 1));
            bt[i].setMaxSize(TILE_SIZE, TILE_SIZE);
            bt[i].setShape(new Circle(1.5));
            // set button position
            bt[i].setTranslateX(TILE_SIZE / 4 + i * (TILE_SIZE + 5));
            bt[i].setTranslateY(-260);
        }
        // Action Events
        bt[0].setOnAction(new EventHandler<>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                String label = ((Button) e.getSource()).getText();
                System.out.println(label);
                // column are from 1 to COLUMN, while index is from 0
                // hence subtracting 1
                fillColumn(Integer.parseInt(label.split(" ")[1]) - 1);
                if (!columnSpace(1)) {
                    System.out.println("No more column Space");
                } else {
                    matrix.add(1);
                }
            }
        });
        bt[1].setOnAction(new EventHandler<>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                String label = ((Button) e.getSource()).getText();
                System.out.println(label);
                // column are from 1 to COLUMN, while index is from 0
                // hence subtracting 1
                fillColumn(Integer.parseInt(label.split(" ")[1]) - 1);
                if (!columnSpace(2)) {
                    System.out.println("No more column Space");
                } else {
                    matrix.add(2);
                }
            }
        });
        bt[2].setOnAction(new EventHandler<>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                String label = ((Button) e.getSource()).getText();
                System.out.println(label);
                // column are from 1 to COLUMN, while index is from 0
                // hence subtracting 1
                fillColumn(Integer.parseInt(label.split(" ")[1]) - 1);
                if (!columnSpace(3)) {
                    System.out.println("No more column Space");
                } else {
                    matrix.add(3);
                }
            }
        });
        bt[3].setOnAction(new EventHandler<>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                String label = ((Button) e.getSource()).getText();
                System.out.println(label);
                // column are from 1 to COLUMN, while index is from 0
                // hence subtracting 1
                fillColumn(Integer.parseInt(label.split(" ")[1]) - 1);
                if (!columnSpace(4)) {
                    System.out.println("No more column Space");
                } else {
                    matrix.add(4);
                }
            }
        });
        bt[4].setOnAction(new EventHandler<>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                String label = ((Button) e.getSource()).getText();
                System.out.println(label);
                // column are from 1 to COLUMN, while index is from 0
                // hence subtracting 1
                fillColumn(Integer.parseInt(label.split(" ")[1]) - 1);
                if (!columnSpace(5)) {
                    System.out.println("No more column Space");
                } else {
                    matrix.add(5);
                }
            }
        });
        bt[5].setOnAction(new EventHandler<>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                String label = ((Button) e.getSource()).getText();
                System.out.println(label);
                // column are from 1 to COLUMN, while index is from 0
                // hence subtracting 1
                fillColumn(Integer.parseInt(label.split(" ")[1]) - 1);
                if (!columnSpace(6)) {
                    System.out.println("No more column Space");
                } else {
                    matrix.add(6);
                }
            }
        });
        bt[6].setOnAction(new EventHandler<>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                String label = ((Button) e.getSource()).getText();
                System.out.println(label);
                // column are from 1 to COLUMN, while index is from 0
                // hence subtracting 1
                fillColumn(Integer.parseInt(label.split(" ")[1]) - 1);
                if (!columnSpace(7)) {
                    System.out.println("No more column Space");
                } else {
                    matrix.add(7);
                }
            }
        });
        // Create rectangle grid
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);
        // create GridPane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5)); // pixel buffer layout
        pane.setHgap(5.5); // pixel per blocks
        pane.setVgap(5.5);

        // add everything to gridpane
        shape.setFill(Color.BLUE);
        pane.getChildren().addAll(shape);
        pane.getChildren().addAll(bt);
        circles = new Circle[ROWS - 1][COLUMNS];
        for (int i = 0; i < ROWS - 1; i++) {
            for (int i2 = 0; i2 < COLUMNS; i2++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(i2 * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(-20 + (i - 2) * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setFill(Color.WHITE);
                pane.getChildren().add(circle);
                circles[i][i2] = circle;
            }
        }
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Connect4 Game"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    public void setElement(int row, int col, char value) {
        objArray[row][col] = value;
    } // end of mutator setElement

    public void fillColumn(int col) {
        for (int i = ROWS - 2; i >= 0; i--) {
            Circle c = circles[i][col];
            if ((Color) c.getFill() == Color.WHITE) {
                if (determinePlayer % 2 == 0) {
                    c.setFill(Color.BLACK);
                } else {
                    c.setFill(Color.RED);
                }
                return;
            }
        }
    }

    public void connectToServer() {
        try {
            // Create a socket to connect to the server
            socket = new Socket(host, CONNECT4_PORT);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        //finally { try { socket.close(); } catch(Exception x) { x.printStackTrace(); }  finally {} }

        // Control the game on a separate thread
        new Thread(() -> {
            try {
                // Get notification from the server
                int player = fromServer.readInt();

                // Am I player 1 or 2?
                if (player == PLAYER1) {
                    myToken = 'X';
                    otherToken = 'O';
                    Platform.runLater(() -> {
                        lblTitle.setText("Player 1 with token 'X'");
                        lblStatus.setText("Waiting for player 2 to join");
                    });

                    // Receive startup notification from the server
                    fromServer.readInt(); // Whatever read is ignored

                    // The other player has joined
                    Platform.runLater(() ->
                            lblStatus.setText("Player 2 has joined. I start first"));

                    // It is my turn
                    myTurn = true;
                }
                else if (player == PLAYER2) {
                    myToken = 'O';
                    otherToken = 'X';
                    Platform.runLater(() -> {
                        lblTitle.setText("Player 2 with token 'O'");
                        lblStatus.setText("Waiting for player 1 to move");
                    });
                }

                // Continue to play
                while (continueToPlay) {
                    if (player == PLAYER1) {
                        waitForPlayerAction(); // Wait for player 1 to move
                        sendMove(); // Send the move to the server
                        receiveInfoFromServer(); // Receive info from the server
                    }
                    else if (player == PLAYER2) {
                        receiveInfoFromServer(); // Receive info from the server
                        waitForPlayerAction(); // Wait for player 2 to move
                        sendMove(); // Send player 2's move to the server
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    /** Wait for the player to mark a cell */
    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
        }

        waiting = true;
    }

    /** Send this player's move to the server */
    private void sendMove() throws IOException {
        toServer.writeInt(rowSelected); // Send the selected row
        toServer.writeInt(columnSelected); // Send the selected column
    }

    /** Receive info from the server */
    private void receiveInfoFromServer() throws IOException {
        // Receive game status
        int status = fromServer.readInt();

        if (status == PLAYER1_WON) {
            // Player 1 won, stop playing
            continueToPlay = false;
            if (myToken == 'X') {
                Platform.runLater(() -> lblStatus.setText("I won! (X)"));
            }
            else if (myToken == 'O') {
                Platform.runLater(() ->
                        lblStatus.setText("Player 1 (X) has won!"));
                receiveMove();
            }
        }
        else if (status == PLAYER2_WON) {
            // Player 2 won, stop playing
            continueToPlay = false;
            if (myToken == 'O') {
                Platform.runLater(() -> lblStatus.setText("I won! (O)"));
            }
            else if (myToken == 'X') {
                Platform.runLater(() ->
                        lblStatus.setText("Player 2 (O) has won!"));
                receiveMove();
            }
        }
        else if (status == DRAW) {
            // No winner, game is over
            continueToPlay = false;
            Platform.runLater(() ->
                    lblStatus.setText("Game is over, no winner!"));

            if (myToken == 'O') {
                receiveMove();
            }
        }
        else {
            receiveMove();
            Platform.runLater(() -> lblStatus.setText("My turn"));
            myTurn = true; // It is my turn
        }
    }

    private void receiveMove() throws IOException {
        // Get the other player's move
        int row = fromServer.readInt();
        int column = fromServer.readInt();
        Platform.runLater(() -> cell[row][column].setToken(otherToken));
    }

    // An inner class for a cell
    public class Cell extends Pane {
        // Indicate the row and column of this cell in the board
        private int row;
        private int column;

        // Token used for this cell
        private char token = ' ';

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
            this.setPrefSize(2000, 2000); // What happens without this?
            setStyle("-fx-border-color: black"); // Set cell's border
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        /** Return token */
        public char getToken() {
            return token;
        }

        /** Set a new token */
        public void setToken(char c) {
            token = c;
        }

        /* Handle a mouse click event */
        private void handleMouseClick() {
            // If cell is not occupied and the player has the turn
            if (token == ' ' && myTurn) {
                setToken(myToken);  // Set the player's token in the cell
                myTurn = false;
                rowSelected = row;
                columnSelected = column;
                lblStatus.setText("Waiting for the other player to move");
                waiting = false; // Just completed a successful move
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
