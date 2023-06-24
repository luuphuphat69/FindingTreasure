package entity;

import com.captainhook.findingtreasure.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import usage.Constant;
import static usage.Constant.PlayerConst.getSpritesAmount;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class Object {
    public int x, y, type, action;
    public Rectangle2D.Float hitBox;
    protected boolean doAnimation, isActive = false;
    protected int animationTick, animationIndex, speed = 15;
    public BufferedImage[] animation;
    protected BufferedImage bufferedImage;
    public int xDrawOffset;
    public int yDrawOffset;

    public BufferedImage[] getAnimation() {
        return animation;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public int getAction() {
        return action;
    }
   

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public void setxDrawOffset(int xDrawOffset) {
        this.xDrawOffset = xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

    public void setyDrawOffset(int yDrawOffset) {
        this.yDrawOffset = yDrawOffset;
    }
    

    public Object(int x, int y, int type, int action) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.action = action;
       
    }
    public Object(){
    }
    public void importObjectImage() {
        String animatePath = Constant.ObjectConst.getImagePath(type, action);
        bufferedImage = LoadSave.GetSpriteAtlas(animatePath);
    }

    public void updateAnimations() {

        animationTick++;
        if (animationTick >= speed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= Constant.ObjectConst.getSpritesAmount(type, action)) {
                animationIndex = 0;
            }
        }
    }
    
    public void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }   
    
    public void loadAnimations() {

        int totalSprites = Constant.ObjectConst.getSpritesAmount(type, action);
        animation = new BufferedImage[totalSprites];
        for (int i = 0; i < animation.length; i++) {
            animation[i] = bufferedImage.getSubimage(i * 32, 0, 32, 32);
        }
    }
    
    protected void initHitbox(int width, int height){
        hitBox = new Rectangle2D.Float(x, y, (int)(width * Game.SCALE), (int)(height * Game.SCALE));
    }
    
    /*Set vị trí của hitbox, khi nhân vật được vẽ tại 1 vị trí cố định sẽ reset
      lại vị trí của hitbox = vị trí của nhân vật*/
    public void setHitBoxDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void drawHitBox(Graphics g, int xLvlOffset, int yLvlOffset){
        g.setColor(Color.RED);
        g.drawRect(x, y, (int)hitBox.width - xLvlOffset, (int)hitBox.height - yLvlOffset);
    }
}