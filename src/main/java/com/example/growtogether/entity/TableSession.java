package com.example.growtogether.entity;

import com.example.growtogether.constants.TableSessionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableSessionId;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "staff_id",nullable = false)
    private Staff staff;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TableSessionStatus status;

    @OneToMany(mappedBy = "tableSession")
    private List<Orders> orders;

    @OneToOne(mappedBy = "tableSession")
    private Invoice invoice;


}
