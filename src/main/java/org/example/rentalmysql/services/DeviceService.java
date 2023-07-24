package org.example.rentalmysql.services;

import org.example.rentalmysql.dao.DeviceDAO;
import org.example.rentalmysql.entities.Category;
import org.example.rentalmysql.entities.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeviceService {

    private DeviceDAO deviceDAO;

    public DeviceService(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public void addDevice(Device device) {
        // TODO walidacja?
        deviceDAO.save( device );
    }

    public void updateDevice(Device device) {
        deviceDAO.save( device );
    }

    public void deleteDevice( Device device ) {
        deviceDAO.delete( device );
    }

    public void deleteDeviceWithId( Long deviceId ) {
        deviceDAO.deleteById( deviceId );
    }


    public Optional<Device> getDeviceWithId(Long deviceId ) {
        return deviceDAO.findById( deviceId );
    }

    public List<Device> getAllDevicesInCategory(Category category) {
        return deviceDAO.findDevicesByCategoryId( category.getId() );
    }

    public List<Device> getAllDevices() {
        Iterable<Device> devices = deviceDAO.findAll();
        return StreamSupport.stream(devices.spliterator(), false)
                .toList();
    }
}
