/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usage;

/**
 *
 * @author luuph
 */
public class Constant {
    public class Direction{
        public static final int UP = 1;
        public static final int DOWN = 2;
        public static final int LEFT = 3;
        public static final int RIGHT = 4;
    }
    
    public class PlayerConst{
        public static final int DEATH = 0;
        public static final int IDLE = 1;
        public static final int HURT = 2;
        public static final int WALK = 3;
        public static final int RUNNING = 4;
        public static final int JUMPING = 5;
        public static final int PUSHING = 6;
        public static final int CLIMBING = 7;
        public static final int ATTACK1 = 8;
        public static final int ATTACK2 = 9;
        
        
        public static String getImagePath(int playerAction){
        switch (playerAction) {
            case 0 -> {
                return "character/Death.png";
                }
            case 1 -> {
                return "character/Idle1.png";
                }
            case 2 -> {
                return "character/Hurt.png";
                }
            case 3 -> {
                return "character/Walk.png";
                }
            case 4 -> {
                return "character/Run.png";
                }
            case 5 -> {
                return "character/Jump.png";
                }
            case 6 -> {
                return "character/Push.png";
                }
            case 7 -> {
                return "character/Climb.png";
                }
            case 8 -> {
                return "character/Attack1.png";
                }
            case 9 -> {
                return "character/Attack2.png";
                }
             }
            return "character/Idle1.png";
        }
        
        public static int getSpritesAmount(int playerAction){
            switch (playerAction) {
            case 0 -> {
                return 3;
                }
            case 1 -> {
                return 4;
                }
            case 2 -> {
                return 4;
                }
            case 3 -> {
                return 6;
                }
            case 4 -> {
                return 6;
                }
            case 5 -> {
                return 8;
                }
            case 6 -> {
                return 6;
                }
            case 7 -> {
                return 4;
                }
            case 8 -> {
                return 4;
                }
            case 9 -> {
                return 6;
                }
             }
            return 0;
        }
    }
}
