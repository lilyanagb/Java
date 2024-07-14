package homework5.plot;

import java.util.Map;

import homework5.property.buildable.Buildable;

public class Plot implements PlotAPI {
    private int buildableArea;
    Map<String, E> buildables;

    public Plot(int buildableArea){
        this.buildableArea = buildableArea;
        this.buildables = new HashMap<>();
    }

    @Override
    public void construct(String address, Buildable buildable) {
        if(address == null || address.isBlank() || buildable == null){
            throw new IllegalArgumentException("Address or buildable cannot be null!");
        }

        if(buildables.containsKey(address)){
            throw new BuildableAlreadyExistsException();
        }

        if(buildable.getArea() > buildableArea){
            throw new InsufficientPlotAreaException();
        }

        buildable.put(address, buildable);
        buildableArea -= buildable.getArea();
    }

    @Override
    public void constructAll(Map buildables) {
        if(buildables == null || buildables.isEmpty()){
            throw new IllegalArgumentException("Buildables is null ot empty");
        }

        for(Map.Entry<String, E> entry : buildables.entrySet()){
            if(entry.getKey() == null || entry.getKey().isBlank()){
                throw new IllegalArgumentException("Address cannot be null or blank");
            }
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("Buildable cannot be null");
            }
            if (buildables.containsKey(entry.getKey())) {
                throw new BuildableAlreadyExistsException();
            }
            if (entry.getValue().getArea() > buildableArea) {
                throw new InsufficientPlotAreaException();
            }

            construct(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void demolish(String address) {
        if(address == null || address.isBlank()){
            throw new IllegalArgumentException("Address cannot be null or blank");
        }

        E be = buildables.remove(address);

        if(be == null){
            throw new BuildableNotFoundException();
        }

        buildableArea += be.getArea();
    }

    @Override
    public void demolishAll() {
        buildables.clear();
        buildableArea = 0;
    }

    @Override
    public Map getAllBuildables() {
        return Collections.unmodifiableMap(buildables);
    }

    @Override
    public int getRemainingBuildableArea() {
        return buildableArea;
    }

}
