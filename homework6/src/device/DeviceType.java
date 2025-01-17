package homework6.src.device;

public enum DeviceType {

    SMART_SPEAKER("SPKR"),
    BULB("BLB"),
    THERMOSTAT("TMST");

    private final String shortName;

    private DeviceType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

}
