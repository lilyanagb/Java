package homework4;

import java.util.Comparator;

import homework4.member.Address;
import homework4.member.GymMember;

public class ProximityComparator implements Comparator<GymMember> {
    private Address gymAddress;

    public ProximityComparator(Address gymAddress) {
        this.gymAddress = gymAddress;
    }

    @Override
    public int compare(GymMember member1, GymMember member2) {
        double distance1 = member1.getAddress().getDistanceTo(gymAddress);
        double distance2 = member2.getAddress().getDistanceTo(gymAddress);

        return Double.compare(distance1, distance2);
    }
}
