/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import com.captainhook.findingtreasure.Game;
import java.awt.Graphics;


public class Chest extends Object{
    
    private int x, y, type, action;
    public static int xDrawOffset;
    public static int yDrawOffset;
    
    private int[][] levelData;
    
    public Chest(int x, int y, int type, int action, int[][]levelData){
        super(x, y, type, action);
        
        this.x = x;
        this.y = y;
        this.type = type;
        this.action = action;
        this.levelData = levelData;
        
        xDrawOffset = (int) (5 * Game.SCALE);
        yDrawOffset = (int) (4 * Game.SCALE);
    }
    
    public int getIndex(int x, int y){
        return levelData[y][x];
    }
    
    
    public void update(){
        importObjectImage();
        loadAnimations();
        updateAnimations();
    }
}