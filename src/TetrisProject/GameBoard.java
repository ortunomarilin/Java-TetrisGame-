
package TetrisProject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class GameBoard implements EventHandler<KeyEvent> {
    
    private Timeline timeline; 
    private KeyFrame draw;

    private Canvas canvas = new Canvas(550,700);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    private final int blockSize = 30;
    private Color[][] board = new Color[20][10];
    private Color emptyColor = Color.web("#000026");
 
    private Tetrominoes tetrominoes;
    private Tetromino piece;
    private Tetromino nextPiece; 
    
    private boolean start = false;
    private boolean gameOver = false;
    private boolean paused = false;
    
    private int lines = 0;
    private int score = 0; 
    

    public GameBoard()
    {
        tetrominoes = new Tetrominoes(3, -4);
        piece = tetrominoes.getRandomTetrominoSet((int) (Math.random() * 7));
        nextPiece = tetrominoes.getRandomTetrominoSet((int) (Math.random() * 7));
    }
    
    
    public Canvas getCanvas()
    {
        return canvas;
    }
    
    public void startGame()
    {
        initializeBoard();
        
        timeline = new Timeline();      
        draw = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event) {
               
                fillBoard();
                pieceFall();
                showScoreAndNextShape();
            }  
        });
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(draw);
        timeline.play();
    }
    
    public void gameOver()
    {
        timeline.stop();
        gameOver = true;
        start = false;
        score =0;
        lines =0;
        
        gc.setFill(emptyColor);
        gc.fillRect(0, 0, board[0].length * blockSize, board.length * blockSize);
        gc.setFont(new Font("Impact", 50));
        gc.setFill(Color.WHITE);
        gc.fillText("Game Over", 45, 300, 400);   
    }
    
    
    public void pieceFall()
    {
        if(!piece.collusion(piece.getX(), piece.getY()+1, piece.getMatrix(), board, emptyColor))
        {
            piece.moveDown();  
            fillTetromino();
        }
        else{
            
            copyPieceToBoard();
            removeRows();
            
            if(piece.getY() <= 0)
            {
                gameOver();
                return;
            }

            tetrominoes = new Tetrominoes(3, -4);
            piece = nextPiece;
            nextPiece = tetrominoes.getRandomTetrominoSet((int) (Math.random() * 7));
        }
    }

    public void copyPieceToBoard()
    {
        for (int r = 0; r < piece.getMatrix().length; r++) 
        {
            for (int c = 0; c < piece.getMatrix()[r].length; c++) 
            {
                if(piece.getY() + r < 0)
                {
                    break;
                }
                if (piece.getMatrix()[r][c] != 0) 
                {
                    board[piece.getY() + r][piece.getX() + c] = piece.getColor();
                }
            }
        }   
    }
    
    
    //Create a method to check full rows and remove them from gameBoard
    public void removeRows() 
    {
        int totalLinesDeleted = 0; 
        for (int r = board.length - 1; r > 0; r--) 
        {
            int count = 0;
            for (int c = 0; c < board[r].length; c++) 
            {
                if (board[r][c] != emptyColor) 
                {
                    count++;
                }
            }
            if (count == 10) 
            {
                moveAllRowsDown(r);
                lines++; 
                totalLinesDeleted++; 
                r++;
            }
        }
            score = score + (totalLinesDeleted * 40); 
            fillBoard();
    }

    //Create a method to move rows above the full row down 1 spot. 
    public void moveAllRowsDown(int NumRowFull) 
    {
        for (int r = NumRowFull; r > 0; r--) 
        {
            for (int c = 0; c < board[0].length; c++) 
            {
                board[r][c] = board[r - 1][c];
            }
        }
    }

    public void initializeBoard()
    {
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {
                board[row][col] = emptyColor;
            }
        }   
    }
    
    public void fillTetromino()
    {
        for(int row = 0; row < piece.getMatrix().length; row++)
        {
            for(int col = 0; col < piece.getMatrix()[row].length; col++)
            {
                if(piece.getMatrix()[row][col] != 0)
                {
                    fillBlock(piece.getX() + col, piece.getY() + row, piece.getColor(), blockSize);
                }
            }
        }
    }

    
    public void fillBoard()
    {
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {
                fillBlock(0 + col, 0 + row, board[row][col], blockSize);
            }
        }  
    }
    
    public void fillBlock(int x, int y, Color color, int size)
    {
        gc.setLineWidth(3);
        gc.setStroke(emptyColor);
        gc.strokeRect(x * size, y * size, size, size);
        gc.setFill(color);
        gc.fillRect(x * size, y * size, size, size); 
    }
    

    public void showScoreAndNextShape()
    {
        // Next Shape 
        fillContainer(325, 1, 200, 125);
        fillNextPiece(25);
        fillText("Next Shape", 370, 30);
        
        //Lines Board 
        fillContainer(325, 200, 200, 125);
        fillText("Lines", 370, 230);
        fillText(String.valueOf(lines), 370, 260);
        
        //Score Board 
        fillContainer(325, 400, 200, 125);
        fillText("Score", 370, 430);
        fillText(String.valueOf(score), 370, 460);  
    }
    
    
    public void fillContainer(int x, int y, int w, int h)
    {
        gc.setStroke(Color.WHITE);
        gc.strokeRect(x, y, w, h);
        gc.setFill(emptyColor);
        gc.fillRect(x, y, w, h);
    }
    
    public void fillNextPiece(int size)
    {
        for(int row = 0; row < nextPiece.getMatrix().length; row++)
        {
            for(int col = 0; col < nextPiece.getMatrix()[row].length; col++)
            {
                if(nextPiece.getMatrix()[row][col] != 0)
                {
                    fillBlock(15 + col, 2 + row, nextPiece.getColor(), size);
                }
            }
        }  
    }
     
    public void fillText(String text, int x, int y)
    {
        gc.setFont(new Font("Courier", 20));
        gc.setFill(Color.WHITE);
        gc.fillText(text, x, y);
    }
    
    
    
    @Override
    public void handle(KeyEvent event) {
        
        switch(event.getCode()){
            case DOWN: 
                if(!paused && !gameOver && start)
                {
                    fillBoard();
                    pieceFall(); 
                    break;
                }
                
            case LEFT:
                if(!paused && !gameOver && start)
                {
                    if(!piece.collusion(piece.getX() -1, piece.getY(), piece.getMatrix(), board, emptyColor))
                    {
                        fillBoard();
                        piece.moveLeft(); 
                        fillTetromino();
                    }
                }
                break;
                
            case RIGHT:
                if(!paused && !gameOver && start)
                {
                    if(!piece.collusion(piece.getX() +1, piece.getY(), piece.getMatrix(), board, emptyColor))
                    {
                        fillBoard();
                        piece.moveRight(); 
                        fillTetromino();
                    }
                }
                break;
                
            case UP:
                if(!paused && !gameOver && start)
                {
                    piece.rotate(board, emptyColor);
                    fillBoard();
                    fillTetromino();
                }
                break;
                
            case SPACE:
                if(!gameOver)
                {
                    if(paused)
                    {
                        timeline.play();
                        paused = false;
                    }
                    else
                    {
                        timeline.pause();
                        paused = true;           
                    }
                }
            case ENTER:
                if(!start)
                {
                    tetrominoes = new Tetrominoes(3, -4);
                    piece = tetrominoes.getRandomTetrominoSet((int) (Math.random() * 7));
                    nextPiece = tetrominoes.getRandomTetrominoSet((int) (Math.random() * 7));
                    gameOver = false;
                    paused = false;
                    start = true;
                    startGame(); 
                }
        }  
    }   
}
