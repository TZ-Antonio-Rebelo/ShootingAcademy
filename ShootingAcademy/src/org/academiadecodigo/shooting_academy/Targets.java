package org.academiadecodigo.shooting_academy;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Targets {

    public Face[] gameTargets;

    private String[] paths;

    public Targets(int targetNumber) {
        gameTargets = new Face[targetNumber];
    }

    public Face[] getGameTargets() {
        return gameTargets;
    }

    public void drawGameTargets() {

        targetArray();

        int counter = 1;
        for (int i = 0; i < gameTargets.length; i++) {
            int randomRow = (int) (Math.random() * 30) * 15;
            int randomCol = (int) (Math.random() * 70) * 15;
            int randomPath = (int) (Math.random() * 15);
            gameTargets[i] = new Face(new Picture(randomCol + 45, randomRow + 45, paths[randomPath]));
            System.out.println(counter + ": " + gameTargets[i].getPicture() + "\n");
            counter++;
        }
    }

    private String[] targetArray() {

        paths = new String[15];
        paths[0] = "6x6Andre.png";
        paths[1] = "8x8Andre.png";
        paths[2] = "10x10Andre.png";
        paths[3] = "6x6Pedro.png";
        paths[4] = "8x8Pedro.png";
        paths[5] = "10x10Pedro.png";
        paths[6] = "6x6Luis.png";
        paths[7] = "8x8Luis.png";
        paths[8] = "10x10Luis.png";
        paths[9] = "6x6Toze.png";
        paths[10] = "8x8Toze.png";
        paths[11] = "10x10Toze.png";
        paths[12] = "6x6Caroline.png";
        paths[13] = "8x8Caroline.png";
        paths[14] = "10x10Caroline.png";

        return paths;
    }
}
