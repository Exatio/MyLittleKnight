package fr.exatio.game.tiles;

import fr.exatio.game.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map {

    int[][] map;
    int mapRows, mapCols;
    int defaultSpawnRow, defaultSpawnCol, defaultDirection;
    boolean isSingleScreen;

    public Map(int screenNumber) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("levels/screen_" + screenNumber))));
            mapRows = Integer.parseInt(br.readLine());
            mapCols = Integer.parseInt(br.readLine());
            map = new int[mapRows][mapCols];

            for(int i = 0 ; i < mapRows ; i++) {
                String line = br.readLine();
                for(int j = 0 ; j < mapCols ; j++) {
                    map[i][j] = Character.getNumericValue(line.charAt(j));
                }
            }
            String additionalData = br.readLine();
            defaultSpawnRow = Character.getNumericValue(additionalData.charAt(0));
            defaultSpawnCol = Character.getNumericValue(additionalData.charAt(1));
            defaultDirection = Character.getNumericValue(additionalData.charAt(2));
            isSingleScreen = Character.getNumericValue(additionalData.charAt(3)) == 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTileType(int row, int col) {
        return map[row-1][col-1];
    }
}
