package ru.phoenigm.stuffer.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Avatar {
    public String url;
    public String format;
}
