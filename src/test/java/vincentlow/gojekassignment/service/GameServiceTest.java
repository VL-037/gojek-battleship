package vincentlow.gojekassignment.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import vincentlow.gojekassignment.model.Player;
import vincentlow.gojekassignment.model.Position;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GameServiceTest {

    private final String OUTPUT_FILE_NAME = "OUTPUT_FILE_NAME.txt";

    private final int GRID_SIZE = 5;

    @InjectMocks
    private GameService gameService;

    @Mock
    private BufferedWriter writer;

    private Map<Integer, Player> players;

    private Player player1;

    private Player player2;

    @BeforeEach
    void setUp() {
        openMocks(this);

        List<Position> missilePositions = new ArrayList<>();

        player1 = new Player(new ArrayList<>());
        player1.setMissilePositions(missilePositions);

        player2 = new Player(new ArrayList<>());
        player2.setMissilePositions(missilePositions);

        players = new HashMap<>();
        players.put(0, player1);
        players.put(1, player2);

        gameService = new GameService(null, GRID_SIZE, players);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setOutputFile() {
        gameService.setOutputFile(OUTPUT_FILE_NAME);

//        Assertions.assertNotNull(writer);
    }

    @Test
    void closeOutputFile() throws IOException {
        gameService.setOutputFile(OUTPUT_FILE_NAME);
        gameService.closeOutputFile();

//        verify(writer).close();
    }

    @Test
    void simulateBattle() {

    }

    @Test
    void printGrids() {
    }

    @Test
    void printResults() {
    }
}