package org.tommap.springdatajpacourse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Ticket extends BaseAuditEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2908340177752689576L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private LocalDateTime scheduledAt;

    @Builder
    public Ticket(String origin, String destination, LocalDateTime scheduledAt) {
        this.origin = origin;
        this.destination = destination;
        this.scheduledAt = scheduledAt;
    }
}
