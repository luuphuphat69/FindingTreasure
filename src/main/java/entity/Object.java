package entity;

import com.captainhook.findingtreasure.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author luuph
 */
public class Object {
    public int x, y, type;
    public Rectangle2D.Float hitBox;
    
    
    public Object(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public Object(){
    }
    
    protected void initHitbox(int width, int height){
        hitBox = new Rectangle2D.Float(x, y, (int)(width * Game.SCALE), (int)(height * Game.SCALE));
    }
    
    /*Set vị trí của hitbox, khi nhân vật được vẽ tại 1 vị trí cố định sẽ reset
      lại vị trí của hitbox = vị trí của nhân vật*/
    public void setHitBoxDirection(int x, int y) {
        hitBox.x = x;
        hitBox.y = y;
        this.x = x;
        this.y = y;
    }
    
    public void drawHitBox(Graphics g, int xLvlOffset, int yLvlOffset){
        g.setColor(Color.RED);
        g.drawRect(this.x, this.y, (int)hitBox.width - xLvlOffset, (int)hitBox.height - yLvlOffset);
    }
}