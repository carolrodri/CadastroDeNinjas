package CadastroDeNinjas.Ninjas;

import CadastroDeNinjas.Missoes.MissoesDTO;
import CadastroDeNinjas.Missoes.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    private final MissoesService missoesService;

    public NinjaControllerUI(NinjaService ninjaService, MissoesService missoesService) {
        this.ninjaService = ninjaService;
        this.missoesService = missoesService;
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        List<MissoesDTO> missoesDisponiveis = missoesService.listarMissoes();
        model.addAttribute("missoesDisponiveis", missoesDisponiveis);
        return "adicionarNinja";
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/visualizar/{id}")
    public String visualizarNinjaPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesninja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }


    @GetMapping("/atualizar/{id}")
    public String mostrarFormularioAdtualizarNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.buscarNinjaParaAtualizacao(id);
        List<MissoesDTO> missoesDisponiveis = missoesService.listarMissoes();
        model.addAttribute("missoesDisponiveis", missoesDisponiveis);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "atualizarNinja";
        } else {
            model.addAttribute("mensagem", "Ninja com esse id não encontrado.");
            return "listarNinjas";
        }
    }



    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes){
    ninjaService.criarNinja(ninja);
    redirectAttributes.addFlashAttribute("mensagem", "ninjaCadastrado");
    return "redirect:/ninjas/ui/listar";
    }

    @PostMapping("/salvar-atualizacao/{id}")
    public String salvarAtualizacaoNinja(
            @PathVariable Long id,
            @ModelAttribute NinjaDTO ninja,
            RedirectAttributes redirectAttributes
    ) {
        ninjaService.atualizarNinja(id, ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/visualizar/" + id;
    }
}
