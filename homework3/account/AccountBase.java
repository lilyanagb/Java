package homework3.account;

import homework3.course.Course;
import homework3.course.Resource;
import homework3.exception.CourseAlreadyPurchasedException;
import homework3.exception.CourseNotCompletedException;
import homework3.exception.CourseNotPurchasedException;
import homework3.exception.InsufficientBalanceException;
import homework3.exception.MaxCourseCapacityReachedException;

public abstract class AccountBase implements Account {
    String username;
    double balance;
    private Course[] purchasedCourses;
    private int courseCount;

    public AccountBase(String username, double balance){
        this.username = username;
        this.balance = balance;
        this.purchasedCourses = new Course[100];
        this.courseCount = 0;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void addToBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        balance += amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, MaxCourseCapacityReachedException, CourseAlreadyPurchasedException {
        if (balance < course.getPrice()) {
            throw new InsufficientBalanceException();
        }

        if (courseCount >= 100) {
            throw new MaxCourseCapacityReachedException();
        }

        if (hasPurchasedCourse(course)) {
            throw new CourseAlreadyPurchasedException();
        }

        balance -= course.getPrice();
        purchasedCourses[courseCount++] = course;
    }

    @Override
    public void completeResourcesFromCourse(Course course, Resource[] resourcesToComplete) throws CourseNotPurchasedException {
        if(course == null || resourcesToComplete == null){
            throw new IllegalArgumentException("Invalid params!");
        }

        if(!hasPurchasedCourse(course)){
            throw new CourseNotPurchasedException();
        }

        for(Resource resource : resourcesToComplete){
            resource.complete();
        }
    }

    @Override
    public void completeCourse(Course course, double grade) throws CourseNotPurchasedException, CourseNotCompletedException {
        if(grade < 2.00 || grade > 6.00){
            throw new IllegalArgumentException("Wrong grade!");
        }

        if (!hasPurchasedCourse(course)) {
            throw new CourseNotPurchasedException();
        }

        if(!course.isCompleted()){
            throw new CourseNotCompletedException();
        }

        completeCourse(course, grade);
    }

    @Override
    public Course getLeastCompletedCourse() {
        if (courseCount == 0) {
            return null;
        }

        Course leastCompletedCourse = purchasedCourses[0];
        for (int i = 1; i < courseCount; i++) {
            if (purchasedCourses[i].getCompletionPercentage() < leastCompletedCourse.getCompletionPercentage()) {
                leastCompletedCourse = purchasedCourses[i];
            }
        }

        return leastCompletedCourse;
    }

    protected boolean hasPurchasedCourse(Course course) {
        for (int i = 0; i < courseCount; i++) {
            if (purchasedCourses[i].equals(course)) {
                return true;
            }
        }
        return false;
    }
}
