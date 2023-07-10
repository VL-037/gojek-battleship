package vincentlow.gojekassignment.model;

import lombok.Data;

@Data
public class Ship {

    private Position position;
    private boolean isAlive;

    public Ship() {
        this.isAlive = true;
    }

    public Ship(Position position) {
        this.position = position;
        this.isAlive = true;
    }
}
