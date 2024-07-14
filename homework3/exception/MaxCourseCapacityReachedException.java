package homework3.exception;

public class MaxCourseCapacityReachedException extends Exception {
    public MaxCourseCapacityReachedException(){
        super("Max course capacity reached!");
    }
}
