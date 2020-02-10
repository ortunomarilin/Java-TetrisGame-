
package TetrisProject;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);  
    }


    @Override
    public void start(Stage window) {
        
        GameBoard board = new GameBoard();
        
        Label lblTetris = new Label("TETRIS");
        lblTetris.setWrapText(true);
        lblTetris.setId("labelTetris");
        
        Label lblStart = new Label("Press ENTER to start");
        lblStart.setWrapText(true);
        lblStart.setId("labelInstructions");
        
        Label lblPause = new Label("Press SPACE to pause");
        lblPause.setWrapText(true);
        lblPause.setId("labelInstructions");

        
        VBox root = new VBox();
        
        HBox titleBox = new HBox();
        titleBox.getChildren().add(lblTetris);
        titleBox.getStyleClass().add("titleBox"); //Style for Box 
        titleBox.setAlignment(Pos.CENTER);
        
        HBox gameBox = new HBox();
        gameBox.getStyleClass().add("gameBox");
        
        HBox leftBox = new HBox();
        leftBox.setPrefSize(500, 800);
        leftBox.getChildren().add(board.getCanvas());
        leftBox.addEventHandler(KeyEvent.KEY_PRESSED, board);
        leftBox.getStyleClass().add("left"); //Style for Box 
        
        VBox rightBox = new VBox();
        rightBox.setPrefSize(400, 800);
        
        HBox rightTopBox = new HBox();
        rightTopBox.setPrefSize(400, 300);
        rightTopBox.getChildren().add(lblStart);
        
        HBox rightBottomBox = new HBox();
        rightBottomBox.setPrefSize(400, 300);
        rightBottomBox.getChildren().add(lblPause);

        gameBox.getChildren().addAll(leftBox, rightBox);
        rightBox.getChildren().addAll(rightTopBox,rightBottomBox);
        
        root.getChildren().addAll(titleBox, gameBox);
        root.getStyleClass().add("root");
        
         Scene scene = new Scene(root, 800,800);

        scene.getStylesheets().add("TetrisProject/StyleSheet.css");
        window.setTitle("Tetris Game");
        window.setResizable(false);
        window.setScene(scene);
        window.show();

        leftBox.requestFocus();
       
        
    }

    
    
}
