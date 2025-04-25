package br.com.joao.barber_api.controller.dto;

import lombok.Getter;

public record CredentialsDTO(String login, char[] password) {
}
