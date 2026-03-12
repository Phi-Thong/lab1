package com.poly.ennity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "J6roles")
public class J6role {
    @Id
    @Column(name = "Id", nullable = false, length = 50)
    private String id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

}