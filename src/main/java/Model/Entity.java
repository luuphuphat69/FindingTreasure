/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author luuph
 */
public class Entity {
    public int xAxis, yAxis;
    public int width, height;
    public int action;
    public boolean isMoving;
    public boolean isAttacking;
    public boolean left, right, up, down;
    public Rectangle hitBox;
    public boolean ifCollistion = false;
    
      public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public void setIsAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    public void createHitbox(){
        hitBox = new Rectangle(xAxis, yAxis, width , height); 
    }
    
    public void updateHitbox(){
        hitBox.x = getxAxis();
        hitBox.y = getyAxis();
    }
    
    public Rectangle getHitbox(){
        return hitBox;
    }
    
    public void drawHitbox(Graphics g){
        g.setColor(Color.red);
        g.drawRect(xAxis, yAxis, width, height);
    }
}
