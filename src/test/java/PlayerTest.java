import exceptions.MapNotSetException;
import files.Director;
import map.Map;
import map.Tile;
import map.TileType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import player.Direction;
import player.Player;
import player.Position;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;
    Random randomMocked;
    Map map = Map.getMap();
    @Before
    public void setup() throws MapNotSetException {
        int mapSize =5;
        randomMocked = Mockito.mock(Random.class);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0,0,0,0,1,1,1,2,2,3,3,2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 1,2,1, 0, 3);
        map.setSize(mapSize, randomMocked);
        player = new Player(randomMocked);
    }

    @After
    public void teardown() {
        player = null;
    }

    /**
     * Test for getter for tiles visited
     */
    @Test
    public void testGetTilesVisitedBySize() {
        player.move(Direction.UP);
        player.move(Direction.RIGHT);
        player.move(Direction.DOWN);
        player.move(Direction.LEFT);

        //getting player actual coordinates
        Set<Tile> tilesVisited = player.getTilesVisited();

        assertEquals("Asserting number of tiles", 5, tilesVisited.size());
    }

    /**
     * Test for starting position generation
     */
    @Test
    public void testGenerateStartRandom() throws MapNotSetException {
        Player player2 = new Player(new Random());
        assertEquals("Asserting type of starting tile", TileType.GREEN, map.getMapTile(player2.getStart()).getType());
    }

    /**
     * Test for starting position generation
     */
    @Test
    public void testGenerateStart() throws MapNotSetException {
        int mapSize = 5;
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(4,4,0,1,1,0,1,2,0,3,3,2, 2, 1, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 1,2,1, 0, 3);
        map.setSize(mapSize, randomMocked);
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0, 0,1,3,2);
        Player player2 = new Player(randomMocked);
        player2.setPosition(new Position(4,4));
        assertEquals("Asserting type of starting tile", TileType.GREEN, map.getMapTile(player2.getStart()).getType());
    }


    /**
     * Test for getter and setter
     */
    @Test
    public void testPositionGetterAndSetter() {
        Position position = new Position(1, 0);
        player.setPosition(position);

        //getting player actual coordinates
        Position playerPos = player.getPosition();

        //checking x coordinate from get
        assertEquals("Asserting x position coordinate", 1, playerPos.getxCoordinate());
        //checking y coordinate from get
        assertEquals("Asserting  y coordinate", 0, playerPos.getyCoordinate());
    }

    /**
     * Test for getter and setter for start Position
     */
    @Test
    public void testStartPositionGetterAndSetter() {
        Position position = new Position(1, 0);
        player.setStart(position);

        //getting player actual coordinates
        Position playerPos = player.getStart();

        //checking x coordinate from get
        assertEquals("Asserting x position coordinate", 1, playerPos.getxCoordinate());
        //checking y coordinate from get
        assertEquals("Asserting  y coordinate", 0, playerPos.getyCoordinate());
    }

    /**
     * Test for move up
     */
    @Test
    public void testMoveUp(){

        Position position = new Position(0, 0);
        player.setPosition(position);

        //move up
        player.move(Direction.UP);
        //checking y coordinate after move up
        assertEquals("Asserting y position after move up", 0, player.getPosition().getyCoordinate());

    }

    /**
     * Test for getting moves
     */
    @Test
    public void testGetMoves(){

        Position position = new Position(0, 0);
        player.setPosition(position);

        player.move(Direction.DOWN);
        player.move(Direction.DOWN);

        //asserting moves list
        assertEquals("Asserting moves list size", 2, player.getMoves().size());
    }

    /**
     * Test for move down
     */
    @Test
    public void testMoveDown(){
        Position position = new Position(0, 1);
        player.setPosition(position);

        //move down
        player.move(Direction.DOWN);
        //checking y coordinate after move down
        assertEquals("Asserting y position after move down", 2, player.getPosition().getyCoordinate());
    }

    /**
     * Test for move right
     */
    @Test
    public void testMoveRight(){
        Position position = new Position(0, 0);
        player.setPosition(position);

        //move right
        player.move(Direction.RIGHT);
        //checking x coordinate after move right
        assertEquals("Asserting x position after move right", 1, player.getPosition().getxCoordinate());
    }

    /**
     * Test for move left
     */
    @Test
    public void testMoveLeft(){
        Position position = new Position(1, 0);
        player.setPosition(position);

        //move left
        player.move(Direction.LEFT);
        //checking x coordinate after move left
        assertEquals("Asserting y position after move left", 0, player.getPosition().getxCoordinate());
    }


    /**
     * Test for move up when cannot
     */
    @Test
    public void testMoveUpBad(){
        Position position = new Position(0, 0);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move up", player.move(Direction.UP));

    }

    /**
     * Test for move down when cannot
     */
    @Test
    public void testMoveDownBad(){
        Position position = new Position(0, 4);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move down", player.move(Direction.DOWN));
    }

    /**
     * Test for move right when cannot
     */
    @Test
    public void testMoveRightBad(){
        Position position = new Position(4, 6);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move right", player.move(Direction.RIGHT));

    }

    /**
     * Test for move left when cannot
     */
    @Test
    public void testMoveLeftBad(){
        Position position = new Position(0, 0);
        player.setPosition(position);

        //checking if false is returned
        assertFalse("Asserting false on move left", player.move(Direction.LEFT));

    }

}