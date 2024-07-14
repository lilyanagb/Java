package homework4;

public class GymCapacityExceededException extends Exception {
    public GymCapacityExceededException(){
        super("Gym capacity exceeded!");
    }
}
