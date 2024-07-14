package homework5.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import homework5.property.billable.Billable;

public class UtilityService implements UtilityServiceAPI {
    private Map<UtilityType, Double> taxRates;

    public UtilityService(Map<UtilityType, Double> taxRates){
        this.taxRates = new HashMap<>(taxRates);
    }

    @Override
    public <T extends Billable> double getUtilityCosts(UtilityType utilityType, T billable) {
        if(utilityType == null || billable == null){
            throw new IllegalArgumentException("Incorrect utilityType or billable!");
        }

        double utilityConsumption;
        switch(utilityType) {
            case WATER:
                utilityConsumption = billable.getWaterConsumption();
                break;
            case ELECTRICITY:
                utilityConsumption = billable.getElectricityConsumption();
                break;
            case NATURAL_GAS:
                utilityConsumption = billable.getNaturalGasConsumption();
                break;
            default:
                throw new IllegalArgumentException("Invalid utility type!");
        }

        return Math.round((taxRates.get(utilityType) * utilityConsumption) * 100.0) / 100.0;
    }

    @Override
    public <T extends Billable> double getTotalUtilityCosts(T billable) {
        if(billable == null){
            throw new IllegalArgumentException("Incorrect billable!");
        }

        double allCosts = 0;
        
        for(UtilityType type : UtilityType.values()){
            allCosts += getUtilityCosts(type, billable);
        }

        return Math.round(allCosts * 100.0) / 100.0;
    }

    @Override
    public <T extends Billable> Map<UtilityType, Double> computeCostsDifference(T firstBillable, T secondBillable) {
        if(firstBillable == null || secondBillable == null){
            throw new IllegalArgumentException("Incorrect first or second billable!");
        }

        double diff = 0;
        Map<UtilityType, Double> comparison = new HashMap<>();

        for(UtilityType type : UtilityType.values()){
            diff = Math.abs(getUtilityCosts(type, firstBillable) - getUtilityCosts(type, secondBillable));
            comparison.put(type, diff);
        }

        return Collections.unmodifiableMap(comparison);
    }

}
