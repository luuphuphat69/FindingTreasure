package entity;

import java.awt.image.BufferedImage;
import usage.Constant;

/**
 *
 * @author luuph
 */
public class Coin extends Object{
    private int x, y, type, action;
    public static int xDrawOffset;
    public static int yDrawOffset;
    
    private int[][] levelData;

    public Coin(int x, int y, int type, int action, int[][] levelData) {
        this.x = x;
        this.y = y;
       super.type = type;
       super.action = action;
        this.levelData = levelData;
        
        initHitbox(10, 10);
    }
    
    public int getIndex(int x, int y) {
        return levelData[y][x];
    }

    public void update() {
        importObjectImage();
        int totalSprites = Constant.ObjectConst.getSpritesAmount(type, action);
        animation = new BufferedImage[1];
        for (int i = 0; i < animation.length; i++) {
            animation[i] = bufferedImage.getSubimage(i, 0, 16, 16);
        }
        updateAnimations();
    }
}
