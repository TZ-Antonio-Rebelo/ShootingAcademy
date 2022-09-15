package org.academiadecodigo.shooting_academy;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Game {
    private Targets targets;
    private Crosshair crosshair;

    private final Sound backroundMusic = new Sound(1);
    private Face[] gameTargets;
    private int score;

    public Game() {
    }

    public void start() {

        targets = new Targets(15);
        crosshair = new Crosshair();
        Grid grid = new Grid();

        gameTargets = targets.getGameTargets();

        score = 0;

        targets.drawGameTargets();

        backroundMusic.play();
        backroundMusic.loop();

        Thread thread = new Thread(new GameLoop());
        thread.start();

    }

    void moveTargets() {

        for (Face f : gameTargets)
            if (!f.isHit()) {
                switch (f.getDirection()) {
                    case UP:
                        if (f.getPicture().getY() / 15 > 1) {
                            moveUp(f);
                        } else {
                            f.setDirection(Direction.DOWN);
                        }
                        break;
                    case DOWN:
                        if (f.getPicture().getY() / 15 < 38) {
                            moveDown(f);
                        } else {
                            f.setDirection(Direction.UP);
                        }
                        break;
                    case LEFT:
                        if (f.getPicture().getX() / 15 > 1) {
                            moveLeft(f);
                        } else {
                            f.setDirection(Direction.RIGHT);
                        }
                        break;
                    case RIGHT:
                        if (f.getPicture().getX() / 15 < 70) {
                            moveRight(f);
                        } else {
                            f.setDirection(Direction.LEFT);
                        }
                        break;
                }
                if (f.checkMove()) {
                    f.changeMove();
                }
            }
    }

    public void shotSound(){
        Sound shotSound = new Sound(0);
        shotSound.play();
    }

    public void moveUp(Face gameTarget) {
        gameTarget.getPicture().translate(0, -15);
    }

    public void moveDown(Face gameTarget) {
        gameTarget.getPicture().translate(0, 15);
    }

    public void moveLeft(Face gameTarget) {
        gameTarget.getPicture().translate(-15, 0);
    }

    public void moveRight(Face gameTarget) {
        gameTarget.getPicture().translate(15, 0);
    }


    public class GameLoop implements Runnable {

        private boolean endGame = false;
        private boolean isShot = true;
        private int mRow = crosshair.getmRow();
        private int mCol = crosshair.getmCol();
        private int spaceCount = 2;

        private Text myScore;

        @Override
        public void run() {

            myScore = drawScore();

            while (!endGame) {

                try {

                    Thread.sleep(50);

                    moveTargets();
                    shotSoundLogic();
                    saveShotCoordinates();
                    checkIfTargetIsHitLogic();
                    endGame = checkIfAllTargetsHaveBeenHit();
                    finishGame();


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        public void shotSoundLogic() {

            if (crosshair.getkeySpacePress()) {
                if (isShot) {
                    shotSound();
                    isShot = false;
                }
            }
            if (!crosshair.getkeySpacePress()) {
                isShot = true;
            }

        }

        public Text drawScore () {

            Text myScore = new Text(95, 30, "SCORE: " + score);
            myScore.setColor(Color.WHITE);
            myScore.grow(75, 20);
            myScore.draw();
            return myScore;
        }

        public void saveShotCoordinates() {

            if (crosshair.getkeySpacePress()) {
                if (spaceCount > 0) {
                    mRow = crosshair.getmRow();
                    mCol = crosshair.getmCol();
                    spaceCount--;
                } else {
                    mCol = mRow = -1;
                    spaceCount = 5;
                }
            } else {
                spaceCount = 5;
            }
        }

        public void checkIfTargetIsHitLogic() {

            for (int i = 0; i < targets.getGameTargets().length; i++) {

                int tInitialRow = targets.getGameTargets()[i].getPicture().getY() / 15;
                int tInitialCol = targets.getGameTargets()[i].getPicture().getX() / 15;
                int tFinalRow = targets.getGameTargets()[i].getPicture().getMaxY() / 15;
                int tFinalCol = targets.getGameTargets()[i].getPicture().getMaxX() / 15;

                if (crosshair.getkeySpacePress()) {
                    System.out.println(mRow);
                    System.out.println(mCol);
                    if (mRow >= tInitialRow && mRow <= tFinalRow && mCol >= tInitialCol && mCol <= tFinalCol) {
                        System.out.println("shot it");
                        if (!targets.getGameTargets()[i].isHit()) {
                            targets.getGameTargets()[i].getPicture().delete();
                            targets.getGameTargets()[i].setFaceHit();
                            score += 50;
                            myScore.setText("SCORE: " + score);
                            i = targets.getGameTargets().length;
                        }
                    } else {
                        score--;
                        if (score < 0) {
                            score = 0;
                            myScore.setText("Score: " + score);
                        }
                        myScore.setText("Score: " + score);
                    }
                }

            }
        }

        public boolean checkIfAllTargetsHaveBeenHit() {

            for (int i = 0; i < targets.getGameTargets().length; i++) {
                endGame = true;
                if (!targets.getGameTargets()[i].isHit()) {
                    endGame = false;
                    break;
                }
            }

            return endGame;
        }

        public void finishGame() {

            if (checkIfAllTargetsHaveBeenHit()) {
                showEndScore();
                endGame = true;
                restartGame();
            }
        }

        public void showEndScore () {
            myScore.delete();

            Text endScore = new Text(600, 350, "Final score: " + score);
            endScore.setColor(Color.WHITE);
            endScore.draw();

            for (int i = 1 ; i<12;i++) {
                try {
                    Thread.sleep(30);
                    endScore.grow(i*2,i*0.5);
                    endScore.draw();
                    Thread.sleep(30);
                    endScore.grow(i*4,i);
                    endScore.draw();
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void restartGame() {
            try {
                Thread.sleep(4000);
                backroundMusic.stop();
                Menu menu = new Menu();
                menu.setHasStarted(false);
                menu.init();


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
