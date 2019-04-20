package ru.phoenigm.stuffer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripRequestForm {
    private Long tripId;
    private String requestText;
}
