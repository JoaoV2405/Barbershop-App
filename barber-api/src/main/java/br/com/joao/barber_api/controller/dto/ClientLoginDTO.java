package br.com.joao.barber_api.controller.dto;
import br.com.joao.barber_api.entity.Client_Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientLoginDTO{
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String token;

    public ClientLoginDTO(Client_Entity client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.phone = client.getPhone();
    }
}



