package homework10.server;

import java.util.Collection;

import homework10.server.order.Order;

public record Response(Status status, String additionalInfo, Collection<Order> orders) {
    private enum Status {
        OK, CREATED, DECLINED, NOT_FOUND
    }

    // TODO: Add implementation if needed
}
