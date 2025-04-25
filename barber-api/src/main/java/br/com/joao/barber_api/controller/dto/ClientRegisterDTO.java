package br.com.joao.barber_api.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ClientRegisterDTO{
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String token;

    public ClientRegisterDTO(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}

