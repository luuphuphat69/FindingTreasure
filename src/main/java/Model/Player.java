/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static usage.Constant.PlayerConst.*;
import static usage.Constant.PlayerConst.getImagePath;
import static usage.Constant.PlayerConst.getSpritesAmount;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class Player {
    public int xAxis, yAxis;
    public int width, height;
    private int action;
    public boolean isMoving;
    public boolean isAttacking;
    private boolean left, right, up, down;
    private Rectangle hitBox;

    private BufferedImage[] animation;
    private BufferedImage bufferedImage;
    private int animationTick , animationIndex, speed = 15;
    
    public Player() {
    }

    public Player(int xAxis, int yAxis, int width, int height, int action, boolean isMoving, boolean isAttacking) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        this.action = action;
        this.isMoving = isMoving;
        this.isAttacking = isAttacking;
        
        createHitbox();
    }
    
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
    
    private void createHitbox(){
        hitBox = new Rectangle(xAxis, yAxis, width, height); 
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
    
    public void importPlayerImage() {
        String animatePath = getImagePath(getAction());
        bufferedImage = LoadSave.GetSpriteAtlas(animatePath);
    }
        
    public void loadAnimations(){
        
        int totalSprites = getSpritesAmount(getAction());
        animation = new BufferedImage[totalSprites];
        for(int i = 0; i < animation.length; i++){
            animation[i] = bufferedImage.getSubimage(i * 32, 0, 32, 32);
        }
    }
    
    public void updateAnimations() {

        animationTick++;
        if (animationTick >= speed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpritesAmount(getAction())) {
                animationIndex = 0;
            }
        }
    }
    
        
    public void setAnimation(){
        int startAnimation = getAction();
        
        if(isMoving == true){
            setAction(WALK);
        }else if(isMoving != true ){
            setAction(IDLE);
        }
        
        if(isAttacking){
            setAction(ATTACK1);
        }
        
        if(startAnimation != getAction()){
            resetAnimationTick();
        }
    }
    
    public void resetAnimationTick(){
        animationTick = 0;
        animationIndex = 0;
    }
    
    public void updatePosition() {
        
        isMoving = false;
        
            if(left && !right){
                xAxis -= 2;
                isMoving = true;
            }else if(right && !left){
                xAxis += 2;
                isMoving = true;
            }
            if(up && !down){
                yAxis -= 2;
                isMoving = true;
            }else if(down && !up){
                yAxis += 2;
                isMoving = true;
            }
        
    }
    
    public void render(Graphics g){
        g.drawImage(animation[animationIndex], getxAxis(), getyAxis(), 40, 40, null);
    }
}
