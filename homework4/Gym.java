package homework4;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import homework4.member.Address;
import homework4.member.GymMember;
import homework4.workout.Exercise;
import homework4.workout.Workout;

public class Gym implements GymAPI {
    private int capacity;
    private Address address;
    private SortedSet<GymMember> members;

    public Gym(int capacity, Address address){
        this.capacity = capacity;
        this.address = address;
        this.members = new TreeSet<>();
    }

    @Override
    public SortedSet<GymMember> getMembers() {
        return Collections.unmodifiableSortedSet(members);
    }

    @Override
    public SortedSet<GymMember> getMembersSortedByName() {
        SortedSet<GymMember> sorted = new TreeSet<>(Comparator.comparing(GymMember::getName));
        sorted.addAll(members);

        return Collections.unmodifiableSortedSet(sorted);
    }

    @Override
    public SortedSet<GymMember> getMembersSortedByProximityToGym() {
        List<GymMember> memberList = new ArrayList<>(members);
        memberList.sort(new ProximityComparator(address));
        SortedSet<GymMember> sorted = new TreeSet<>(memberList);

        return Collections.unmodifiableSortedSet(sorted);
    }

    @Override
    public void addMember(GymMember member) throws GymCapacityExceededException {
        if(member == null){
            throw new IllegalArgumentException("Incorrect member");
        }
        
        if(members.size() >= capacity){
            throw new GymCapacityExceededException();
        }

        members.add(member);
    }

    @Override
    public void addMembers(Collection<GymMember> newMembers) throws GymCapacityExceededException {
        if(newMembers == null){
            throw new IllegalArgumentException("Incorrect members");
        }
        
        int remaining = capacity - members.size();
        if(newMembers.size() > remaining){
            throw new GymCapacityExceededException();
        }

        members.addAll(newMembers);
    }

    @Override
    public boolean isMember(GymMember member) {
        if(member == null){
            throw new IllegalArgumentException("Incorrect member");
        }
        
        return members.contains(member);
    }

    @Override
    public boolean isExerciseTrainedOnDay(String exerciseName, DayOfWeek day) {
        if (exerciseName == null || exerciseName.isEmpty() || day == null) {
            throw new IllegalArgumentException("Exercise name and day cannot be null or empty.");
        }

        for (GymMember member : members) {
            Map<DayOfWeek, Workout> trainingProgram = member.getTrainingProgram();
            Workout work = trainingProgram.get(day);
    
            if (work != null) {
                for (Exercise exercise : work.exercises()) {
                    if (exerciseName.equals(exercise.name())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public Map<DayOfWeek, List<String>> getDailyListOfMembersForExercise(String exerciseName) {
        if(exerciseName == null){
            throw new IllegalArgumentException("Exercise name cannot be null");
        }

        Map<DayOfWeek, List<String>> dailyList = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            List<String> memberList = new ArrayList<>();

            for (GymMember member : members) {
                Map<DayOfWeek, Workout> trainingProgram = member.getTrainingProgram();
                Workout workout = trainingProgram.get(day);

                if (workout != null) {
                    for (Exercise exercise : workout.exercises()) {
                        if (exerciseName.equals(exercise.name())) {
                            memberList.add(member.getName());
                            break;
                        }
                    }
                }
            }

            if(!memberList.isEmpty()){
                dailyList.put(day, memberList);
            }
        }
    
        return Collections.unmodifiableMap(dailyList);
    }
}
