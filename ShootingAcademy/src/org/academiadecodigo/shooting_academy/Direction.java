package org.academiadecodigo.shooting_academy;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Direction getFirstMove(){
        return  Direction.values()[(int)(Math.random()*(Direction.values().length))];
    }

    public static Direction changeMove(Direction dir){
        if (dir == UP || dir == DOWN) {
            return Direction.values()[(2+(int)(Math.random()*2))];
        } else {
            return Direction.values()[(int)(Math.random()*2)];
        }
    }

}
