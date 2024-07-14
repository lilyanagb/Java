package homework3.exception;

public class CourseNotFoundException extends Exception {
    public CourseNotFoundException(){
        super("Course not found!");
    }
}
