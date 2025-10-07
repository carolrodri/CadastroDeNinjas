package CadastroDeNinjas.Ninjas;

import CadastroDeNinjas.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private long id;
    private String nome;
    private String email;
    private String imgUrl;
    private int idade;
    private String rank;
    public void setId(Long id) {
        this.id = id;
    }
    private MissoesModel missoes;

}
