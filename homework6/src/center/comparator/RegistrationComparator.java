package homework6.src.center.comparator;

import java.util.Comparator;

import homework6.src.device.IoTDevice;

public class RegistrationComparator implements Comparator<IoTDevice> {

    @Override
    public int compare(IoTDevice firstDevice, IoTDevice secondDevice) {
        return (int) (firstDevice.getRegistration() - secondDevice.getRegistration());
    }

}
