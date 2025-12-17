package edu.cis.Model;

public class MenuItem {
    private String name, description, id, type;
    private int ammountAvailable;
    double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmmountAvailable() {
        return ammountAvailable;
    }

    public void setAmmountAvailable(int ammountAvailable) {
        this.ammountAvailable = ammountAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", ammountAvailable=" + ammountAvailable +
                ", price=" + price +
                '}';
    }
}
