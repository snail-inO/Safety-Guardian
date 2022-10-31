package me.portfolioof.application.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CrimeType {
    @Id
    private int id;
    private String name;
    private float weight;
    @JsonIgnore
    @OneToMany(targetEntity = CrimeData.class)
    private List<CrimeData> crimeData;

    public CrimeType() {
    }

    public CrimeType(int id, String name, float weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrimeType)) return false;

        CrimeType crimeType = (CrimeType) o;

        if (getId() != crimeType.getId()) return false;
        if (Float.compare(crimeType.getWeight(), getWeight()) != 0) return false;
        return getName() != null ? getName().equals(crimeType.getName()) : crimeType.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getWeight() != +0.0f ? Float.floatToIntBits(getWeight()) : 0);
        return result;
    }
}
