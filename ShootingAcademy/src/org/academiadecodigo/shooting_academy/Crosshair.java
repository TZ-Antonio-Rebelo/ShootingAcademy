package org.academiadecodigo.shooting_academy;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Crosshair {
    boolean keySpacePress = false;
    private int mCol = -1;
    private int mRow = -1;
    private MouseEvent move;


    public Crosshair() {
        Aim aim = new Aim();
    }

    public boolean getkeySpacePress() {
        return keySpacePress;
    }

    public int getmCol() {
        return mCol;
    }

    public int getmRow() {
        return mRow;
    }

    public class Aim implements MouseHandler, KeyboardHandler {

        public Aim() {

            Mouse mouse = new Mouse(this);
            Keyboard keyboard = new Keyboard(this);

            mouse.addEventListener(MouseEventType.MOUSE_MOVED);

            KeyboardEvent spaceOn = new KeyboardEvent();
            spaceOn.setKey(KeyboardEvent.KEY_SPACE);
            spaceOn.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            KeyboardEvent spaceOff = new KeyboardEvent();
            spaceOff.setKey(KeyboardEvent.KEY_SPACE);
            spaceOff.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

            keyboard.addEventListener(spaceOn);
            keyboard.addEventListener(spaceOff);

        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            move = mouseEvent;
        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                mRow = (int) move.getY() / 15;
                mCol = (int) move.getX() / 15;

                if (!keySpacePress) {
                    keySpacePress = true;
                }
            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                mRow = -1;
                mCol = -1;
                keySpacePress = false;
            }
        }
    }

}
