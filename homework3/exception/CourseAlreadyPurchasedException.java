package homework3.exception;

public class CourseAlreadyPurchasedException extends Exception {
    public CourseAlreadyPurchasedException(){
        super("Course already purchased!");
    }
}
