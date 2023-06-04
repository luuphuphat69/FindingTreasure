package Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import level.LevelManager;
import usage.CollisionDetection;
import static usage.Constant.PlayerConst.*;
import static usage.Constant.PlayerConst.getImagePath;
import static usage.Constant.PlayerConst.getSpritesAmount;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class Player extends Entity {
    
    private BufferedImage[] animation;
    private BufferedImage bufferedImage;
    private int animationTick, animationIndex, speed = 15;

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

    public void importPlayerImage() {
        String animatePath = getImagePath(getAction());
        bufferedImage = LoadSave.GetSpriteAtlas(animatePath);
    }

    public void loadAnimations() {

        int totalSprites = getSpritesAmount(getAction());
        animation = new BufferedImage[totalSprites];
        for (int i = 0; i < animation.length; i++) {
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

    public void setAnimation() {
        int startAnimation = getAction();

        if (isMoving == true) {
            setAction(WALK);
        } else if (isMoving != true) {
            setAction(IDLE);
        }

        if (isAttacking) {
            setAction(ATTACK1);
        }

        if (startAnimation != getAction()) {
            resetAnimationTick();
        }
    }

    public void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }
    
    
    
    public void updatePosition() {

        isMoving = false;
        
        if(!left && !right && !up && !down){
            return;
        }
        
        int xAxisTemp = 0, yAxisTemp = 0;
        
        if (left && !right) {
            xAxisTemp -= 2;
        } else if (right && !left) {
            xAxisTemp += 2;
        }
        if (up && !down) {
            yAxisTemp -= 2;
        } else if (down && !up) {
            yAxisTemp += 2;
        }
        
        int[][] levelData = LevelManager.getLevelData();
        if(CollisionDetection.checkIfCollision(xAxis + xAxisTemp, yAxis + yAxisTemp, width, height, levelData)){
            this.xAxis += xAxisTemp;
            this.yAxis += yAxisTemp;
            isMoving = true;
        }
    }

    public void render(Graphics g) {
        g.drawImage(animation[animationIndex], getxAxis(), getyAxis(), 40, 40, null);
    }
}
