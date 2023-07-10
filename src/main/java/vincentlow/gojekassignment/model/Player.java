package vincentlow.gojekassignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Player {

    private List<Ship> ships;
    private int totalMissiles;
    private List<Position> missilePositions;

    public Player(List<Ship> ships) {
        this.ships = ships;
        this.totalMissiles = 0;
    }
}
