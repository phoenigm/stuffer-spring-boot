package ru.phoenigm.stuffer.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.phoenigm.stuffer.domain.TripRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripRequestConfirmation {
    private Long tripRequestId;
    private TripRequest.TripRequestStatus status;

}
