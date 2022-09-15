package org.academiadecodigo.shooting_academy;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu {

    private Picture menuBackround;
    private Picture mouse;
    private Picture spacebar;
    private Text pressSpacebar;
    private Text title;
    private Text shoot;
    private Text move;
    private final Game game;
    boolean hasStarted = false;

    public Menu() {
        Controls menuControls = new Controls();
        game = new Game();
    }

    public void init() {

        drawBackround();
        drawTitle();
        drawMouseAndSpacebar();
        drawShootAndMoveText();
        drawPressSpacebarText();

    }

    public void drawBackround() {

        menuBackround = new Picture(10, 10 , "BackgroundGame.png");
        menuBackround.draw();

    }

    public void drawTitle() {

        title = new Text(650,200,"Shooting Academy");
        title.setColor(Color.RED);
        title.grow(300,75);
        title.draw();
    }

    public void drawMouseAndSpacebar() {

        mouse = new Picture(810,490, "Mouse_.png");
        mouse.draw();

        spacebar = new Picture(975, 500, "space.png");
        spacebar.draw();

    }

    public void drawShootAndMoveText() {

        shoot = new Text(1105, 695, "Shoot");
        shoot.setColor(Color.WHITE);
        shoot.grow(17,10);
        shoot.draw();

        move = new Text(895, 695, "Move");
        move.setColor(Color.WHITE);
        move.grow(17,10);
        move.draw();
    }

    public void drawPressSpacebarText() {

        pressSpacebar = new Text(615,575, "Press Space to Start");
        pressSpacebar.setColor(Color.WHITE);
        pressSpacebar.grow(75, 20);
        pressSpacebar.draw();
    }

    public void closeMenu() {

        title.delete();
        menuBackround.delete();
        mouse.delete();
        spacebar.delete();
        shoot.delete();
        move.delete();
        pressSpacebar.delete();

        if(!hasStarted) {
            game.start();
            hasStarted = true;
        }

    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public class Controls implements KeyboardHandler {

        public Controls() {

            Keyboard keyboardMenu = new Keyboard(this);

            KeyboardEvent spaceMenu = new KeyboardEvent();
            spaceMenu.setKey(KeyboardEvent.KEY_SPACE);
            spaceMenu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboardMenu.addEventListener(spaceMenu);

        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {
            closeMenu();
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }
    }


}
