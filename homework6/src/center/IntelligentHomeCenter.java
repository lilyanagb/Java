package homework6.src.center;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import homework6.src.center.comparator.KwhComparator;
import homework6.src.center.comparator.RegistrationComparator;
import homework6.src.center.exceptions.DeviceAlreadyRegisteredException;
import homework6.src.center.exceptions.DeviceNotFoundException;
import homework6.src.device.DeviceType;
import homework6.src.device.IoTDevice;
import homework6.src.storage.DeviceStorage;

public class IntelligentHomeCenter {
    DeviceStorage storage;

    public IntelligentHomeCenter(DeviceStorage storage) {
        this.storage = storage;
    }

    /**
     * Adds a @device to the IntelligentHomeCenter.
     *
     * @throws IllegalArgumentException         in case @device is null.
     * @throws DeviceAlreadyRegisteredException in case the @device is already
     *                                          registered.
     */
    public void register(IoTDevice device) throws DeviceAlreadyRegisteredException {
        if (device == null) {
            throw new IllegalArgumentException("device cannot be null");
        }

        storage.store(device.getId(), device);
        device.setRegistration(LocalDateTime.now());
    }

    /**
     * Removes the @device from the IntelligentHomeCenter.
     *
     * @throws IllegalArgumentException in case null is passed.
     * @throws DeviceNotFoundException  in case the @device is not found.
     */
    public void unregister(IoTDevice device) throws DeviceNotFoundException {
        if (device == null) {
            throw new IllegalArgumentException("device cannot be null");
        }

        storage.delete(device.getId());
    }

    /**
     * Returns a IoTDevice with an ID @id if found.
     *
     * @throws IllegalArgumentException in case @id is null or empty.
     * @throws DeviceNotFoundException  in case device with ID @id is not found.
     */
    public IoTDevice getDeviceById(String id) throws DeviceNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("device cannot be null");
        }

        if (storage.exists(id)) {
            return storage.get(id);
        }
        else {
            throw new DeviceNotFoundException("device not found");
        }
    }

    /**
     * Returns the total number of devices with type @type registered in IntelligentHomeCenter.
     *
     * @throws IllegalArgumentException in case @type is null.
     */
    public int getDeviceQuantityPerType(DeviceType type) {
        int quantity = 0;

        for (IoTDevice value:storage.listAll()) {
            if (value.getType().getShortName().equals(type.getShortName())) {
                quantity++;
            }
        }

        return quantity;
    }

    /**
     * Returns a collection of IDs of the top @n devices which consumed
     * the most power from the time of their installation until now.
     *
     * The total power consumption of a device is calculated by the hours elapsed
     * between the two LocalDateTime-s: the installation time and the current time (now)
     * multiplied by the stated nominal hourly power consumption of the device.
     *
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<String> getTopNDevicesByPowerConsumption(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        List<IoTDevice> list = new LinkedList<>();
        for (IoTDevice value : storage.listAll()) {
            list.add(value);
        }
        KwhComparator compareKWh = new KwhComparator();
        Collections.sort(list, compareKWh);
        if (n >= list.size()) {
            n = list.size();
        }
        List<String> arrList = new ArrayList<String>();
        for (int i=0; i<n; i++) {
            arrList.add(list.get(i).getId());
        }
        return arrList;
    }

    /**
     * Returns a collection of the first @n registered devices, i.e the first @n that were added
     * in the IntelligentHomeCenter (registration != installation).
     *
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<IoTDevice> getFirstNDevicesByRegistration(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        List<IoTDevice> list = new LinkedList<>();


        for (IoTDevice value : storage.listAll()) {
            list.add(value);
        }

        RegistrationComparator compareReg = new RegistrationComparator();
        Collections.sort(list,compareReg);

        List<IoTDevice> arrList = new ArrayList<>();

        if (n >= list.size()) {
            n = list.size();
        }

        for (int i = 0; i <= n; i++) {
            arrList.add(list.get(i));
        }

        return arrList;
    }

//  Comparator<IoTDevice> comparatorKWh = new Comparator<IoTDevice>()
//	{
//		public int compare(IoTDevice firstDevice, IoTDevice secondDevice)
//		{
//			long hoursFirstDevice = Duration.between(firstDevice.getInstallationDateTime(), LocalDateTime.now()).toHours();
//			long hoursSecondDevice = Duration.between(secondDevice.getInstallationDateTime(), LocalDateTime.now()).toHours();
//			double firstDeviceConsumption = firstDevice.getPowerConsumption();
//			double secondDeviceConsumption = secondDevice.getPowerConsumption();
//
//			if (firstDeviceConsumption * hoursFirstDevice > secondDeviceConsumption*hoursSecondDevice)
//			{
//				return 1;
//			}
//			else if (firstDeviceConsumption * hoursFirstDevice < secondDeviceConsumption*hoursSecondDevice)
//			{
//				return -1;
//			}
//			else
//			{
//				return 0;
//			}
//
//		}
//	};
}