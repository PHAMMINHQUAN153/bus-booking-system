package com.minhquan.busbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Điểm đi không được để trống")
    @Nationalized
    @Column(nullable = false, length = 100)
    private String origin;

    @NotBlank(message = "Điểm đến không được để trống")
    @Nationalized
    @Column(nullable = false, length = 100)
    private String destination;

    @Positive(message = "Khoảng cách phải lớn hơn 0")
    private int distanceKm;

    @Positive(message = "Thời gian di chuyển phải lớn hơn 0")
    private int durationMinutes;
}