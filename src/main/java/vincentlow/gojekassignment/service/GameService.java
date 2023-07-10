package vincentlow.gojekassignment.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vincentlow.gojekassignment.model.Player;
import vincentlow.gojekassignment.model.Position;
import vincentlow.gojekassignment.model.Ship;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class GameService {

    private BufferedWriter writer;

    private int gridSize;

    private Map<Integer, Player> players;

//    public GameService(BufferedWriter writer, int gridSize, Map<Integer, Player> players) {
//        this.writer = writer;
//        this.gridSize = gridSize;
//        this.players = players;
//    }

    public void setOutputFile(String filename) {
        try {
            writer = new BufferedWriter(new FileWriter((filename)));
        } catch (IOException e) {
            System.out.println("#GameServiceImpl#setOutputFile error: " + e.getMessage());
        }
    }

    public void closeOutputFile() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("#GameServiceImpl#closeOutputFile error: " + e.getMessage());
        }
    }

    public void simulateBattle() {
        for (int attackerID : players.keySet()) {
            Player attacker = players.get(attackerID);
            for (int defenderID : players.keySet()) {
                if (defenderID != attackerID) {
                    Player defender = players.get(defenderID);
                    for (Position missilePosition : attacker.getMissilePositions()) {
                        checkHit(defender, missilePosition);
                    }
                }
            }
        }
    }

    public void printGrids() {
        try {
            for (int playerNumber : players.keySet()) {
                Player player = players.get(playerNumber);
                System.out.println("Player " + playerNumber);
                writer.write("Player " + playerNumber);
                writer.newLine();
                printGrid(player);
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("#GameServiceImpl#printGrids error: " + e.getMessage());
        }
    }

    public void printResults() {
        try {
            for (int playerNumber : players.keySet()) {
                Player player = players.get(playerNumber);
                System.out.println("P" + playerNumber + ": " + getHits(player));
                writer.write("P" + playerNumber + ": " + getHits(player));
                writer.newLine();
            }

            int maxHits = 0;
            List<Integer> winningPlayers = new ArrayList<>();
            for (int playerNumber : players.keySet()) {
                Player player = players.get(playerNumber);
                int hits = getHits(player);
                if (hits > maxHits) {
                    maxHits = hits;
                    winningPlayers.clear();
                    winningPlayers.add(playerNumber);
                } else if (hits == maxHits) {
                    winningPlayers.add(playerNumber);
                }
            }

            if (winningPlayers.size() > 1) {
                System.out.println("It is a draw");
                writer.write("It is a draw");
            } else {
                System.out.println("Player " + winningPlayers.get(0) + " wins");
                writer.write("Player " + winningPlayers.get(0) + " wins");
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("#GameServiceImpl#printResults error: " + e.getMessage());
        }
    }

    private void checkHit(Player defender, Position missilePosition) {
        for (Ship ship : defender.getShips()) {
            if (ship.getPosition().getX() == missilePosition.getX() && ship.getPosition().getY() == missilePosition.getY()) {
                ship.setAlive(false);
                break;
            }
        }
    }

    private void printGrid(Player player) {
        char[][] grid = new char[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '_';
            }
        }

        for (Ship ship : player.getShips()) {
            Position position = ship.getPosition();
            grid[position.getX()][position.getY()] = ship.isAlive() ? 'B' : 'X';
        }

        for (Position position : player.getMissilePositions()) {
            if (grid[position.getX()][position.getY()] == '_') {
                grid[position.getX()][position.getY()] = 'O';
            }
        }

        try {
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    System.out.print(grid[i][j] + " ");
                    writer.write(grid[i][j] + " ");
                }
                System.out.println();
                writer.newLine();
            }
            System.out.println();
            writer.newLine();
        } catch (IOException e) {
            System.out.println("#GameServiceImpl#printGrid error: " + e.getMessage());
        }
    }

    private int getHits(Player player) {
        int hits = 0;
        for (Ship ship : player.getShips()) {
            if (!ship.isAlive()) {
                hits++;
            }
        }
        return hits;
    }
}
