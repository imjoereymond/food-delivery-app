package com.tomato.orders.modal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "orders", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        description = "Order Information"
)
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Schema(
            description = "First Name"
    )
    private String firstName;

    @Column
    @Schema(
            description = "Last Name"
    )
    private String lastName;

    @Column(nullable = false, unique = true)
    @Schema(
            description = "Email"
    )
    private String email;

    @Column(nullable = false)
    @Schema(
            description = "Street"
    )
    private String street;

    @Column(nullable = false)
    @Schema(
            description = "City"
    )
    private String city;

    @Column(nullable = false)
    @Schema(
            description = "State"
    )
    private String state;

    @Column(nullable = false)
    @Schema(
            description = "Zipcode"
    )
    private String zipcode;

    @Column(nullable = false)
    @Schema(
            description = "Country"
    )
    private String country;

    @Column(nullable = false)
    @Schema(
            description = "Phone Number"
    )
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @Schema(
            description = "Orders"
    )
    private List<Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
