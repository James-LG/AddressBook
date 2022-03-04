package ca.carleton.jameslg.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AddressBook {
    private Integer id = null;
    private Set<BuddyInfo> buddyInfos = new HashSet<>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressBook")
    public Set<BuddyInfo> getBuddyInfos() {
        return buddyInfos;
    }

    public void setBuddyInfos(Set<BuddyInfo> buddyInfos) {
        this.buddyInfos = buddyInfos;
    }

    public void addBuddy(BuddyInfo buddyInfo) {
        buddyInfos.add(buddyInfo);
    }

    public int size() {
        return buddyInfos.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (BuddyInfo buddy : this.buddyInfos) {
            str.append(buddy.toString()).append('\n');
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;

        AddressBook o = (AddressBook) other;

        if (this.buddyInfos.size() != o.buddyInfos.size())
            return false;

        return (this.buddyInfos.containsAll(o.buddyInfos));
    }
}
