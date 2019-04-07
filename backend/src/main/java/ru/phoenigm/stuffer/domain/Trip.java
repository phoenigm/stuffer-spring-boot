package ru.phoenigm.stuffer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue
    private Long id;
    private User author;
    private LocalDateTime publicationDate;
    private LocalDateTime destinationDate;
    private LocalDateTime startingDate;
    private Long price;
    private String info;

}
