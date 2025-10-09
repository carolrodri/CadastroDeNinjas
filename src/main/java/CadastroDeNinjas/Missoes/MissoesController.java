package CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {


    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todas as missões cadastrados", description = "Rota mostra todas as missões cadastradas no banco de dados")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista todas as missões cadastrados por Id", description = "Rota mostra todas as missões cadastradas no banco de dados pelo Id")
    public ResponseEntity<?> listarMissoesId(@PathVariable Long id) {
        MissoesDTO missoes = missoesService.listarMissoesId(id);
        if (missoes !=null) {
            return ResponseEntity.ok (missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o id: " + id + ", não encontrada em nosso banco de dados");
        }
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cria uma nova missão", description = "Rota cria uma nova missão e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão!")
    })
    public MissoesModel criarMissao(@RequestBody MissoesModel missao) {
        return missoesService.criarMissao(missao);
    }

    @PostMapping("/dificuldade")
    @Operation(summary = "Mostra a dificuldade das missões cadastrados", description = "Rota mostra a dificuldade das missões cadastradas no banco de dados")
    public MissoesModel criarDificuldadeDaMissao(@RequestBody MissoesModel dificuldade) {
        return missoesService.criarDificuldadeDaMissao(dificuldade);
    }

}
