package vincentlow.gojekassignment.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import vincentlow.gojekassignment.model.Player;
import vincentlow.gojekassignment.model.Position;
import vincentlow.gojekassignment.model.Ship;
import vincentlow.gojekassignment.service.GameService;

import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameController {

    private BufferedWriter writer;

    private int gridSize;

    private Map<Integer, Player> players;

    @Autowired
    private GameService gameService;

    public GameController(int gridSize) {
        this.gridSize = gridSize;
        this.players = new HashMap<>();
        this.writer = null;
        this.gameService = new GameService(writer, gridSize, players);
    }

    public Map<Integer, Player> addPlayer(int id, List<Ship> ships, int totalMissiles, List<Position> missilePositions) {
        players.put(id, new Player(ships, totalMissiles, missilePositions));
        return players;
    }

    public void runGame() {
        String outputFileName = UUID.randomUUID() + ".txt";

        gameService.setOutputFile(outputFileName);
        gameService.simulateBattle();
        gameService.printGrids();
        gameService.printResults();
        gameService.closeOutputFile();
    }
}
