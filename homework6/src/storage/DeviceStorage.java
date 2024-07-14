package homework6.src.storage;

import java.util.Collection;

import homework6.src.device.IoTDevice;

public interface DeviceStorage {
    IoTDevice get(String id);

    IoTDevice store(String id, IoTDevice device);

    boolean exists(String id);

    boolean delete(String id);

    Collection<IoTDevice> listAll();
}
