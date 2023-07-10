package vincentlow.gojekassignment.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vincentlow.gojekassignment.model.Player;
import vincentlow.gojekassignment.model.Position;
import vincentlow.gojekassignment.model.Ship;
import vincentlow.gojekassignment.service.GameService;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.openMocks;

class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Mock
    private BufferedWriter writer;

    @Mock
    private GameService gameService;

    private int gridSize;
    private Map<Integer, Player> players;

    private List<Ship> ships;

    private List<Position> missilePositions;

    @BeforeEach
    void setUp() {
        openMocks(this);

        players = new HashMap<>();
        ships = new ArrayList<>();
        missilePositions = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {

        verifyNoMoreInteractions(gameService);
    }

    @Test
    void addPlayer() {
        int playerId = 1;
        int totalMissiles = 10;

        players = gameController.addPlayer(playerId, ships, totalMissiles, missilePositions);

        // Verify that the player was added to the players map
        assertEquals(1, players.size());
        assertTrue(players.containsKey(playerId));
        Player player = players.get(playerId);
        assertNotNull(player);
        assertEquals(ships, player.getShips());
        assertEquals(totalMissiles, player.getTotalMissiles());
        assertEquals(missilePositions, player.getMissilePositions());
    }

    @Test
    void runGame() {
        String outputFileName = "output.txt";

        // Set up mock objects and stub method calls
        doNothing().when(gameService).setOutputFile(outputFileName);
        doNothing().when(gameService).simulateBattle();
        doNothing().when(gameService).printGrids();
        doNothing().when(gameService).printResults();
        doNothing().when(gameService).closeOutputFile();

        // Invoke the method under test
        gameController.runGame();

        // Verify that the expected method calls were made
        verify(gameService).setOutputFile(outputFileName);
        verify(gameService).simulateBattle();
        verify(gameService).printGrids();
        verify(gameService).printResults();
        verify(gameService).closeOutputFile();

    }
}