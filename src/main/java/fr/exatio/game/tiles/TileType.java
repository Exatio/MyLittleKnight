package fr.exatio.game.tiles;

public enum TileType {

    // Background

    BRICK("brick_1"),
    METAL("metal", true),
    MUD("mud"),
    DOOR("door",true),
    ;

    TileType(String name) {
        this(name, false);
    }

    TileType(String name, boolean collideable) {
        this.name = name;
        this.collideable = collideable;
    }


    String name;
    boolean collideable;
}
