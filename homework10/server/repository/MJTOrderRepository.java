package homework10.server.repository;

import homework10.server.Response;

public class MJTOrderRepository implements OrderRepository {
    
    public MJTOrderRepository(){

    }

    @Override
    public Response request(String size, String color, String destination) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'request'");
    }

    @Override
    public Response getOrderById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    @Override
    public Response getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public Response getAllSuccessfulOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSuccessfulOrders'");
    }
    
}
