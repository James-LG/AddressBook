package ca.carleton.jameslg.models;

import javax.persistence.*;

@Entity
public class BuddyInfo {
    private Integer id = null;
    private AddressBook addressBook;
    private String name = "";
    private String address = "";
    private String phoneNumber = "";

    public BuddyInfo() {}

    public BuddyInfo(String name, String address, String phoneNumber, AddressBook addressBook) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.addressBook = addressBook;
    }

    /**
     * Use an integer ID because it is possible two people have the same name.
     */
    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + ' ' + address + ' ' + phoneNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;

        BuddyInfo o = (BuddyInfo)other;
        return (this.name.equals(o.getName()) &&
                this.address.equals(o.getAddress()) &&
                this.phoneNumber.equals(o.getPhoneNumber()));
    }
}
