package CadastroDeNinjas.Missoes;

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
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
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
    public MissoesModel criarMissao(@RequestBody MissoesModel missao) {
        return missoesService.criarMissao(missao);
    }

    @PostMapping("/dificuldade")
    public MissoesModel criarDificuldadeDaMissao(@RequestBody MissoesModel dificuldade) {
        return missoesService.criarDificuldadeDaMissao(dificuldade);
    }

}
