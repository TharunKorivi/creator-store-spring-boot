package com.tharunkorivi.creatorstore.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_name" , nullable = false)
    @NotNull(message = "Customer name is required")
    @NotBlank(message = "Customer name can't be blank")
    private String customerName;

    @Email(message =  "Email is invalid")
    @Column(name = "customer_email" , nullable = false)
    @NotNull(message =  "Email is required")
    @NotBlank(message = "Email can't be blank")
    private String customerEmail;

    @Column(name = "total_price" ,nullable = false)
    @NotNull(message = "Total price is required")
    @DecimalMin(value =  "0.0" , inclusive = false , message = "Total Price must be greater than 0.0")
    private BigDecimal totalPrice;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
