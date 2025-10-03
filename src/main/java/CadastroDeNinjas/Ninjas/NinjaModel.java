package CadastroDeNinjas.Ninjas;

import CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Entity transforma uma classe em entidade do banco de dados
@Entity
@Table(name = "tb_cadastro")
@Data //Cria todos os getters and setters (fica "invisivel")
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //fala como vai implementar o id automaticamente
    private Long id;

    private String nome;

    private String email;

    private int idade;

    //@ManyToone um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id") //Foreing Key ou chave estrangeira
    private MissoesModel missoes;

}
