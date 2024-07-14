package homework3.account;

import homework3.course.Course;
import homework3.exception.CourseAlreadyPurchasedException;
import homework3.exception.InsufficientBalanceException;
import homework3.exception.MaxCourseCapacityReachedException;

public class EducationalAccount extends AccountBase {
    private int consecutiveCoursesPassed;

    public EducationalAccount(String username, double balance) {
        super(username, balance);
        this.consecutiveCoursesPassed = 0;
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, MaxCourseCapacityReachedException, CourseAlreadyPurchasedException {
        if(consecutiveCoursesPassed == 5 && course.getCompletionPercentage() >= 0.45){
            consecutiveCoursesPassed = 0;
            Course discounted = new Course(course.getName(), 
                                    course.getDescription(),
                                    course.getPrice()*0.15, course.getContent(), course.getCategory());
            super.buyCourse(discounted);
        }
        else {
            super.buyCourse(course);
            consecutiveCoursesPassed++;
        }
    }
}
