package entity;

import com.captainhook.findingtreasure.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button {
    private int x;
    private int y;
    private int width, height;
    private BufferedImage img;
    private boolean mousePressed;
    protected Rectangle bounds;

    public Button(int x, int y, int height, int width, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.img = img;
        
        createBounds();
    }
    public Button() {
    }
    private void createBounds() {
        bounds = new Rectangle(x, y * Game.TILES_SIZE, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void draw(Graphics g, int x, int y){
        g.setColor(Color.red);
        g.drawImage(img , x, y, Game.TILES_SIZE, Game.TILES_SIZE, null);
    }
    public void draw(Graphics g){
        g.drawImage(img, x, y, width, height, null);
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}