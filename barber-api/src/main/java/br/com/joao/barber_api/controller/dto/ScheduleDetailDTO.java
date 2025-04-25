package br.com.joao.barber_api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record ScheduleDetailDTO(
        Long id,
        Integer day,
        OffsetDateTime startAt,
        OffsetDateTime endAt,
        Long clientId,
        String clientName
) {
    public ScheduleDetailDTO(Long id,
                             OffsetDateTime startAt,
                             OffsetDateTime endAt,
                             Long clientId,
                             String clientName) {
        this(id, startAt.getDayOfMonth(), startAt, endAt, clientId, clientName);
    }
}
