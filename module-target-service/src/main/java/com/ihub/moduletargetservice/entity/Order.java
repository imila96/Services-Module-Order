package com.ihub.moduletargetservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @JsonProperty("orderId")
    private String id;

    @JsonProperty("qty")
    private int quantity;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ShiftTarget> shiftTarget;

    public Order(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
