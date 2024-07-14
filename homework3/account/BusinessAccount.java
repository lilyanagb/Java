package homework3.account;

import homework3.course.Category;
import homework3.course.Course;
import homework3.exception.CourseAlreadyPurchasedException;
import homework3.exception.InsufficientBalanceException;
import homework3.exception.MaxCourseCapacityReachedException;

public class BusinessAccount extends AccountBase {
    private Category[] allowedCategory;

    public BusinessAccount(String username, double balance, Category[] allowedCategories){
        super(username, balance);
        this.allowedCategory = allowedCategories;
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, MaxCourseCapacityReachedException, CourseAlreadyPurchasedException {
        if(isCategoryAllowed(course.getCategory())){
            Course discounted = new Course(course.getName(), 
                                    course.getDescription(),
                                    course.getPrice()*0.20, course.getContent(), course.getCategory());
            super.buyCourse(discounted);
        }     
    }

    private boolean isCategoryAllowed(Category category){
        for(Category allowed : allowedCategory){
            if(allowed.equals(category)) return true;
        }

        return false;
    }
}
