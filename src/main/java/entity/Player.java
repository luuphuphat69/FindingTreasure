package entity;

import com.captainhook.findingtreasure.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import level.LevelManager;
import object.ObjectManager;
import usage.CollisionDetection;
import static usage.CollisionDetection.checkIfCollision;
import static usage.CollisionDetection.checkIfEntityOnGround;
import static usage.Constant.PlayerConst.*;
import static usage.Constant.PlayerConst.getImagePath;
import static usage.Constant.PlayerConst.getSpritesAmount;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class Player extends Entity {

    int[][] levelData = LevelManager.getLevelData();

    private BufferedImage[] animation;
    private BufferedImage bufferedImage;
    private int animationTick, animationIndex, speed = 5;
    private float xDrawOffset = 5 * Game.SCALE; //hitbox
    private float yDrawOffset = 3 * Game.SCALE; // hitbox
    public int playerIndex;

    private float airSpeed = 0.1f * Game.SCALE;
    private float gravity = 0.1f * Game.SCALE;
    private float jumpSpeed = -2.05f * Game.SCALE;
    private boolean inAir = false;

    private ObjectManager objManager;
    
    public Player() {
    }
    
    public Player(int xAxis, int yAxis, int width, int height, int action, boolean isMoving, boolean isAttacking, int playerIndex) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        this.action = action;
        this.isMoving = isMoving;
        this.isAttacking = isAttacking;
        this.playerIndex = playerIndex;

        createHitbox();
    }

    public static Player initPlayer(int playerIndex){
        switch (playerIndex) {
            case 1:
                return new Player(300, 270, 25 * (int)Game.SCALE, 38 * (int)Game.SCALE, IDLE, false, false, 1);
            case 2:
                return new Player(100, 180, 25 * (int)Game.SCALE, 38 * (int)Game.SCALE, IDLE, false, false, 2);
        }
        return null;
    }
    
    // Lấy ảnh (sprite) nhân vật theo hành động và nhân vật 1 hoặc 2
    public void importPlayerImage() {
        String animatePath = getImagePath(getAction(), playerIndex);
        bufferedImage = LoadSave.GetSpriteAtlas(animatePath);
    }
    
    // Lấy subImages từ sprite
    public void loadAnimations() {

        int totalSprites = getSpritesAmount(getAction());
        animation = new BufferedImage[totalSprites];
        for (int i = 0; i < animation.length; i++) {
            animation[i] = bufferedImage.getSubimage(i * 32, 0, 32, 32);
        }
    }
    
    // Vị trí của từng subimage tăng theo từng lần lặp và reset nếu vượt quá 
    // số lượng trong sprite
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

    // Hành động nào thì set hoạt ảnh đó
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
        
        if(isJump()){
            setAction(JUMPING);
        }

        if (startAnimation != getAction()) {
            resetAnimationTick();
        }
    }
    
    // Khi chuyển sang hành động khác thì reset tick và vị trí của subImage
    public void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    
    public void updatePosition(Player player) {

        player.isMoving = false;
        int xAxisTemp = 0;
        int yAxisTemp = 0;
        
        if (jump) {
            jump();
            yAxisTemp -= airSpeed;
        }

        if (!player.left && !player.right && !inAir) {
            return;
        }
        if (player.left) {
            xAxisTemp -= 3;
        }
        if (player.right) {
            xAxisTemp += 3;
        }

        if (!inAir) {
            if (!checkIfEntityOnGround((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height, levelData)) {
                inAir = true;
            }
        }
        
        if (inAir) {
            if (checkIfCollision((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height, levelData)) {
                this.yAxis += airSpeed;
                airSpeed += gravity;
                updateXPos(xAxisTemp);
                
            } else {          
                if(CollisionDetection.checkIfCollisionWhileJumping((int) hitBox.x, (int) hitBox.y + yAxisTemp, (int) hitBox.width, (int) hitBox.height, levelData)){
                    this.yAxis -= yAxisTemp;
                }else{
                    this.yAxis += 15;
                }
                if (airSpeed > 0) {
                    inAir = false;
                    airSpeed = 0;
                }
            }
        }
        
        isMoving = true;
        if (!inAir && isMoving) {
            if(!CollisionDetection.checkIfCollisionWhileMoving((int)hitBox.x + xAxisTemp, (int)hitBox.y, (int) hitBox.width, (int) hitBox.height, levelData)){
                this.xAxis += xAxisTemp;
            }
        }
    }

    private void updateXPos(int xSpeed) {
        if (checkIfCollision((int)hitBox.x + xSpeed, (int)hitBox.y, (int) hitBox.width, (int) hitBox.height, levelData)) {
            this.xAxis += xSpeed;
        }
    }
    
    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    public void update(Graphics g) {
        importPlayerImage();
        //drawHitbox(g);
        loadAnimations();
        updateAnimations();
        updateHitbox();
        drawHitbox(g);
        setAnimation();
        updatePosition(this);
    }

    public void setDirection() {
        setLeft(false);
        setRight(false);
        setUp(false);
        setDown(false);
    }
    /*
                    _________________
                    |                |
                    |   ( 0 0 )      |
                    |    \| |/  <--->|
                    |     /  \    x  |
                    |________________|
    
        Khoảng cách từ nhân vật đến hit box là xOffset và yOffset
    
        Phải loại bỏ 2 giá trị này để nhân vật khi vẽ ra ngoài panel không bị
        chân không chạm đất
    */
    public void render(Graphics g) {
        g.drawImage(animation[animationIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset), 40, 40, null);
    }
}
