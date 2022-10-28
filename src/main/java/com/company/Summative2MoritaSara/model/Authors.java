package com.company.Summative2MoritaSara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "author")
public class Authors implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Set<Book> books = new HashSet<>();
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public boolean equals(Object object)
    {

        if (object == null || getClass() != object.getClass()) {

            return false;
        }

        if (this == object){
            return true;
        }

        Authors authors = (Authors) object;

        return Objects.equals(id, authors.id) &&
                Objects.equals(firstName, authors.firstName) &&
                Objects.equals(lastName, authors.lastName) &&
                Objects.equals(street, authors.street) &&
                Objects.equals(city, authors.city) &&
                Objects.equals(state, authors.state) &&
                Objects.equals(postalCode, authors.postalCode) &&
                Objects.equals(phone, authors.phone) &&
                Objects.equals(email, authors.email);

    }
    @Override
    public int hashCode(){

        return Objects.hash(id, firstName, lastName, street, city, state, postalCode, phone, email);
    }

    public String toString(){
        String result = "Author{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';

        return result;
    }
}
