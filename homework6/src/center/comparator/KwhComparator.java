package homework6.src.center.comparator;

import java.util.Comparator;

import homework6.src.device.IoTDevice;

public class KwhComparator implements Comparator<IoTDevice> {

    @Override
    public int compare(IoTDevice firstDevice, IoTDevice secondDevice) {
        return (int) (secondDevice.getPowerConsumptionKWh() - firstDevice.getPowerConsumptionKWh());
    }
}