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

    public class Direction {

        public static final int UP = 1;
        public static final int DOWN = 2;
        public static final int LEFT = 3;
        public static final int RIGHT = 4;
    }

    public class PlayerConst {

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

        public static String getImagePath(int playerAction, int playerIndex) {
            switch (playerAction) {
                case DEATH -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Death_8.png";
                    }
                    return "monster1/Death.png";
                }
                case IDLE -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Idle_4.png";
                    }
                    return "monster1/Idle.png";
                }
                case HURT -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Hurt_4.png";
                    }
                    return "monster1/Hurt.png";
                }
                case WALK -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Walk_6.png";
                    }
                    return "monster1/Walk.png";
                }
                case RUNNING -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Run_6.png";
                    }
                    return "monster1/Run.png";
                }
                case JUMPING -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Jump_8.png";
                    }
                    return "monster1/Jump.png";
                }
                case PUSHING -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Push_6.png";
                    }
                    return "monster1/Push.png";
                }
                case CLIMBING -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Climb_4.png";
                    }
                    return "monster1/Climb.png";
                }
                case ATTACK1 -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Attack1_4.png";
                    }
                    return "monster1/Attack1.png";
                }
                case ATTACK2 -> {
                    if(playerIndex == 2){
                        return "monster2/Dude_Monster_Attack2_6.png";
                    }
                    return "monster1/Attack2.png";
                }
            }
            return "character/Idle.png";
        }

        public static int getSpritesAmount(int playerAction) {
            switch (playerAction) {
                case DEATH -> {
                    return 3;
                }
                case IDLE -> {
                    return 4;
                }
                case HURT -> {
                    return 4;
                }
                case WALK -> {
                    return 6;
                }
                case RUNNING -> {
                    return 6;
                }
                case JUMPING -> {
                    return 8;
                }
                case PUSHING -> {
                    return 6;
                }
                case CLIMBING -> {
                    return 4;
                }
                case ATTACK1 -> {
                    return 4;
                }
                case ATTACK2 -> {
                    return 6;
                }
            }
            return 0;
        }
    }
    
    public class ObjectConst{
        
        public static final int CHEST = 10;
        public static final int COIN = 11;
        public static final String MENU = "menu/bg.png";
        public static final String BUTTON_UI = "menu/button UI.png";
        
        public static String getImagePath(int type){
            switch (type) {
                case CHEST:
                    return "chest/Chest.png";
                case COIN:
                    return "coin/greenCoin.png";
            }
            return null;
        }
    }
    public class SoundConst{
        public static final String GAME_SOUND = "gameaudio/Glorious_morning.wav";
        public static final String COIN_SOUND = "gameaudio/coinsound.wav";
        public static final String JUMP_SOUND = "gameaudio/jumpsound.wav";
        public static final String LEVEL_COMPLETED = "gameaudio/level_completed.wav";
    }
    public class UI_Const{
        public static final String BUTTON_UI = "menu/button UI.png";
        public static final String MENU = "menu/menu.png";
    }
}
