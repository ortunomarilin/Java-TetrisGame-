// ===============================
// AUTHOR       : Marilin Ortuno
// CREATE DATE  : August 24, 2019
// CLASS NAME   : Tetromino.java
// PURPOSE      : This class is used to represent all the properties and methods (actions) of a tetromino piece. 
//==================================


package TetrisProject;

import javafx.scene.paint.Color;

public class Tetromino {
    
    private int rotationIndex = 0;
    private int[][][] tetrominoSet; 
    private int[][] matrix; 
    private Color color;
    private int x; 
    private int y; 
    
    
    public Tetromino(int x, int y, Color color, int[][][] tetrominoSet)
    {
        this.tetrominoSet = tetrominoSet;
        this.color = color;
        this.matrix = tetrominoSet[rotationIndex];
        this.x = x;
        this.y = y; 
    }
    
    
    public Color getColor()
    {
        return color; 
    }
    
    public int[][] getMatrix()
    {
        return matrix;
    }
    public int getX()
    {
        return x; 
    }
    public int getY()
    {
        return y; 
    }
    
 
    public void moveDown()
    {
        y += 1;
    }
    
    public void moveLeft()
    {
        x -= 1;
    }
    
    public void moveRight()
    {
        x += 1;
    }
    
    public void rotate(Color[][] board, Color empty)
    {
        int nextRotationIndex = (rotationIndex + 1) % tetrominoSet.length; 
        int[][] rotatedMatrix = tetrominoSet[nextRotationIndex];
        
        if (!collusion(x, y, rotatedMatrix, board, empty)) 
        { 
            rotationIndex = nextRotationIndex;
            matrix = rotatedMatrix;           
        }
    }
    
    
    public boolean collusion(int x, int y, int[][] piece, Color[][] board, Color empty)
    {
        for(int row = 0; row < piece.length; row++)
        {
            for(int col = 0; col < piece[row].length; col++)
            {
                if(piece[row][col] != 0)
                {
                    int possibleX = x + col;
                    int possibleY = y + row;

                    if (possibleX < 0 || possibleX >= 10 || possibleY >= 20) 
                    {
                        return true; 
                    }
                    if(possibleY >= 0)
                    {
                        if(board[possibleY][possibleX] != empty)
                        {
                            return true;
                        }
                    } 
                }
            }
        }
        return false;
    }
  
    
}
