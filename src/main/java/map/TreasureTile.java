package map;

public class TreasureTile implements Tile{

    private TileType type;
    private final String html = "<td class=\"grass-back $visited\"><img class=\"tile-img $visitedimg\" src=\"images/treasure.png\"></td>";

    public TreasureTile()
    {
        this.type = TileType.TREASURE;
    }

    @Override
    public String getHtml() {
        return html;
    }

    @Override
    public TileType getType() {
        return type;
    }
}
