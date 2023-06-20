/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usage;
import com.captainhook.findingtreasure.Game;

/**
 *
 * @author luuph
 */
public class CollisionDetection {
    /*
                        (x, y)    (x + width, y)
                             _____
                            |     |
                            |_____|
        
               (x, y + height)   (x + width, y + height)
    
        check 4 góc của hitbox, nếu 1 trong 4 góc chạm tới tile solid thì không di chuyển được
    */
    public static boolean checkIfCollision(int x, int y, int width, int height, int [][] levelData){
        if(!isSolid(x, y, levelData)){
            if(!isSolid(x + width, y + height, levelData)){
                if(!isSolid(x + width, y, levelData)){
                    if(!isSolid(x, y + height, levelData)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /* Kiểm tra những tile không di chuyển được
    
       Những giá trị 12, 57, 63, 120, 121, 128 là vị những tiles trong mảng levelTile bên LevelManager
    
    VD: Tại tọa độ (0,0) của levelData có giá trị là 12 (Bảng màu 0..255, Red = 12)
        12 là vị trí của 1 tile không đi được
    */
    public static boolean isSolid(int x, int y, int[][] levelData){
        
        if(x < 0|| x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        
        // Kiểm tra xem nhân vật đang ở tile nào
        int positionXInLevelData = x / Game.TILES_SIZE;
        int positionYInLevelData = y / Game.TILES_SIZE;
        
        int value = levelData[positionYInLevelData][positionXInLevelData];
        
        if(value == 12 || value == 57 || value == 63 || value == 120 || value == 121 || value == 128){
            return true;    // Không đi được
        }
        return false;       // Đi được
    }
    
    public static boolean checkIfEntityOnGround(int x, int y, int width, int height, int [][] levelData){
        if(!isSolid(x, y + height + 1, levelData)){
            if(!isSolid(x + width, y + height + 1, levelData)){
                return false; // Không ở trên mặt đất
            }
        }
        return true; // Ở trên mặt đất
    }
    
    public static boolean checkIfCollisionWhileMoving(int x, int y, int width, int height, int [][] levelData){
        
        /*Khi nhân vật di chuyển, có 2 trường hợp sẽ gặp đụng độ, chỉ cần xét
        2 góc trên, 1 góc có va chạm và góc còn lại không có va chạm
        
         - TH1: Va chạm 1 tile solid ở bên phải -> góc(x + width, y) có va chạm
         - TH2: va chạm 1 tile solid ở bên trái -> góc(x,y) có va chạm
        */
        
        if(!isSolid(x, y, levelData) && isSolid(x + width, y, levelData)){
            return true;
        }
        if(isSolid(x, y, levelData) && !isSolid(x + width, y, levelData)){
            return true;
        }
        return false;
    }
    
    public static boolean checkIfCollisionWhileJumping(int x, int y, int width, int height, int [][] levelData){
        
        /*Khi nhảy thì sẽ có trường hợp đụng tile solid, chỉ cần kiểm tra 2 góc
        trên cùng (x,y) và (x + width, y)*/
        
        if(!isSolid(x, y, levelData)){
            if(!isSolid(x + width, y, levelData)){
                return true;   // Nhảy không va chạm
            }
        }
        return false;    // Nhảy có va chạm
    }
}