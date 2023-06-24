package entity;

import com.captainhook.findingtreasure.Game;

/**
 *
 * @author luuph
 */
public class Coin extends Object{
    private int type, action;
    public static int xDrawOffset;
    public static int yDrawOffset;
    public boolean Claimed = false;

    public Coin(int x, int y, int type) {
        super.x = x;
        super.y = y;
        super.type = type;

        initHitbox(20, 20);
    }

    public boolean isClaimed() {
        return Claimed;
    }

    public void setClaimed(boolean Claimed) {
        this.Claimed = Claimed;
    }
        
    public int getY() {
        return super.y;
    }

    public int getX() {
        return super.x;
    }

    public Coin() {
    }
}
