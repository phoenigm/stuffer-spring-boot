package ru.phoenigm.stuffer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripRequestConfirmation {
    private Long tripRequestId;
    private TripRequestStatus status;

    public enum TripRequestStatus {
        ACCEPTED,
        DECLINED,
        UNDER_CONSIDERATION
    }
}
