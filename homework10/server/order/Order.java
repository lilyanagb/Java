package homework10.server.order;

import homework10.server.destination.Destination;
import homework10.server.tshirt.TShirt;

public record Order(int id, TShirt tShirt, Destination destinantio) {
    
}
