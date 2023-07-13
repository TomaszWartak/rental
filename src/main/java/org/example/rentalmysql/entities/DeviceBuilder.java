package org.example.rentalmysql.entities;

public class DeviceBuilder {
    private Long id;
    private String name;
    private String description;
    private int availability;
    private double price;
    private Category category;

    public DeviceBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public DeviceBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public DeviceBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public DeviceBuilder withAvailability(int availability) {
        this.availability = availability;
        return this;
    }

    public DeviceBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public DeviceBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public Device build() {
        Device device = new Device();
        device.setId(id);
        device.setName(name);
        device.setDescription(description);
        device.setAvailability(availability);
        device.setPrice(price);
        device.setCategory(category);
        return device;
    }
}
