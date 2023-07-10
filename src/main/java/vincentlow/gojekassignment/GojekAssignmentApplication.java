package vincentlow.gojekassignment;

import vincentlow.gojekassignment.controller.GameController;
import vincentlow.gojekassignment.model.Position;
import vincentlow.gojekassignment.model.Ship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GojekAssignmentApplication {

    private static Scanner scanner = new Scanner(System.in);

    private static final int DEFAULT_PLAYER_SIZE = 2;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        int gridSize = 0;
        try {
            gridSize = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Grid Size must be Integer!");
        }

        int totalShips = 0;
        try {
            totalShips = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Total ship must be Integer!");
        }

        GameController gameController = new GameController(gridSize);

        List<List<Ship>> playerShips = new ArrayList<>();
        for (int i = 0; i < DEFAULT_PLAYER_SIZE; i++) {
            List<Ship> ships = new ArrayList<>();

            String positionStr = reader.readLine();
            String[] positions = positionStr.split(" : ");
            for (int j = 0; j < totalShips; j++) {
                String[] coordinates = positions[j].trim().split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);

                Ship ship = new Ship(new Position(x, y));
                ships.add(ship);
            }

            playerShips.add(ships);
        }

        int totalMissiles = Integer.parseInt(reader.readLine());

        for (int i = 0; i < DEFAULT_PLAYER_SIZE; i++) {
            List<Position> missilePositions = new ArrayList<>();

            String missilePositionStr = reader.readLine();
            String[] positions = missilePositionStr.split(" : ");
            for (int j = 0; j < totalMissiles; j++) {
                String[] coordinates = positions[j].trim().split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);

                missilePositions.add(new Position(x, y));
            }
            gameController.addPlayer(i + 1, playerShips.get(i), totalMissiles, missilePositions);
        }

        gameController.runGame();
    }

}
