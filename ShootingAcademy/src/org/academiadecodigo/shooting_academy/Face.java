package org.academiadecodigo.shooting_academy;


import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Face {
    private Picture picture;
    private Direction direction;
    private boolean faceHit;
    private int moveCount;
    private int myMoves;

    public Face(Picture picture) {
        this.picture = picture;
        direction = Direction.getFirstMove();
        faceHit = false;
        moveCount = 1;
        myMoves = randomMoves();
        picture.draw();
    }
    public void changeMove(){
        direction = Direction.changeMove(direction);
    }

    private int randomMoves(){
        return (int)(Math.random()*10);
    }

    public Picture getPicture() {
        return picture;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public void setFaceHit() {
        faceHit = true;
    }

    public boolean isHit() {
        return faceHit;
    }

    public boolean checkMove() {
        if (moveCount < myMoves) {
            moveCount++;
            return false;
        } else {
            myMoves = randomMoves();
            moveCount = 1;
            return true;
        }
    }

}
