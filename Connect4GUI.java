package c4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Connect4GUI extends Application {

    public static final int TILE_SIZE = 80;
    public static final int COLUMNS = 7;
    public static int ROWS = 7;
    public static Circle circles[][];

    // instance for each 7 columns in array. starts at bottom
    public static int row1 = 6;
    public static int row2 = 6;
    public static int row3 = 6;
    public static int row4 = 6;
    public static int row5 = 6;
    public static int row6 = 6;
    public static int row7 = 6;

    // determines total number of possible moves
    protected static int count = 42;

    // instance to determine who's move it is
    protected static int determinePlayer = 0;

    //create array
    public static char[][] objArray;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * constructor
     *
     */
    public Connect4GUI() {
        objArray = new char[7][7];
    } // end of constructor

    /**
     * Accessor for elements of object Connect 4
     *
     * @param row row value
     * @param col column value
     * @return element from objects row/column
     */
    public char getElement(int row, int col) {
        return objArray[row][col];
    } // end of accessor getElement
    /**
     * Mutator that adds game pieces to Connect4 2D array
     *
     * @param row row value
     * @param col column value
     * @param value element to be added to object
     */
    public void setElement(int row, int col, char value) {
        objArray[row][col] = value;
    } // end of mutator setElement


    /**
     *
     * fills column
     *
     * @param col
     *
     */
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

    /**
     *
     * start
     *
     * @param primaryStage
     *
     */
    @Override
    public void start(Stage primaryStage) {
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

    /**
     *
     * @param x
     *
     */
    public void add(int x) {
        switch (x) {
            case 1:
                if (determinePlayer % 2 == 0) {
                    setElement(row1, 0, 'X');
                } else {
                    setElement(row1, 0, 'O');
                }
                row1--;
                count--;
                determinePlayer++;
                break;
            case 2:
                if (determinePlayer % 2 == 0) {
                    setElement(row2, 1, 'X');
                } else {
                    setElement(row2, 1, 'O');
                }
                row2--;
                count--;
                determinePlayer++;
                break;
            case 3:
                if (determinePlayer % 2 == 0) {
                    setElement(row3, 2, 'X');
                } else {
                    setElement(row3, 2, 'O');
                }
                row3--;
                count--;
                determinePlayer++;
                break;
            case 4:
                if (determinePlayer % 2 == 0) {
                    setElement(row4, 3, 'X');
                } else {
                    setElement(row4, 3, 'O');
                }
                row4--;
                count--;
                determinePlayer++;
                break;
            case 5:
                if (determinePlayer % 2 == 0) {
                    setElement(row5, 4, 'X');
                } else {
                    setElement(row5, 4, 'O');
                }
                row5--;
                count--;
                determinePlayer++;
                break;
            case 6:
                if (determinePlayer % 2 == 0) {
                    setElement(row6, 5, 'X');
                } else {
                    setElement(row6, 5, 'O');
                }
                row6--;
                count--;
                determinePlayer++;
                break;
            case 7:
                if (determinePlayer % 2 == 0) {
                    setElement(row7, 6, 'X');
                } else {
                    setElement(row7, 6, 'O');
                }
                row7--;
                count--;
                determinePlayer++;
                break;
        } // end of switch
    } // end of

    /** checks columnspace
     *
     * @return boolean
     *
     * @param choice
     *
     */
    public static boolean columnSpace(int choice) {
        if (choice == 1 && row1 < 1) {
            return false;
        } else if (choice == 2 && row2 < 1) {
            return false;
        } else if (choice == 3 && row3 < 1) {
            return false;
        } else if (choice == 4 && row4 < 1) {
            return false;
        } else if (choice == 5 && row5 < 1) {
            return false;
        } else if (choice == 6 && row6 < 1) {
            return false;
        } else if (choice == 7 && row7 < 1) {
            return false;
        } else {
            return true;
        } // if not false then true there is spcae
    }
}