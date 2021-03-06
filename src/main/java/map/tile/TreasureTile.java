package map.tile;

/**
 * This is a class which represents a treasure tile
 */
public class TreasureTile extends Tile{

    private TileType type;
    //holds the class name
    private String className;
    //this is the initial html for the treasure tile
    private final String html = "<td class=\"$class\"><img class=\"tile-img\" src=\"images/treasure.png\">$team.player</td>";

    /**
     * Constructor for a treasure tile
     */
    public TreasureTile()
    {
        this.className = "grass-back";
        this.type = TileType.TREASURE;
    }

    /**
     * Getter for html
     * @return
     */
    @Override
    public String getHtml() {
        return html;
    }

    /**
     * Getter for type
     * @return type
     */
    @Override
    public TileType getType() {
        return type;
    }

    /**
     * Getter for class name
     * @return class name
     */
    @Override
    public String getClassName() {
        return className;
    }
}
