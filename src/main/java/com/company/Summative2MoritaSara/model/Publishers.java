package com.company.Summative2MoritaSara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "publisher")
public class Publishers implements Serializable {

    @Id
    @Column(name = "publisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Set<Books> books = new HashSet<>();

    @NotNull
    private String name;
    private String street;
    private String city;

    @NotNull
    private String state;
    private String postalCode;
    private String phone;
    private String email;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object object){

        Publishers publishers = (Publishers) object;
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        if (this == object){
            return true;
        }

        return Objects.equals(id, publishers.id) &&
                Objects.equals(name, publishers.name) &&
                Objects.equals(street, publishers.street) &&
                Objects.equals(city, publishers.city) &&
                Objects.equals(state, publishers.state) &&
                Objects.equals(postalCode, publishers.postalCode) &&
                Objects.equals(phone, publishers.phone) &&
                Objects.equals(email, publishers.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, street, city, state, postalCode, phone, email);
    }

    public String toString(){

        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';

    }
}
