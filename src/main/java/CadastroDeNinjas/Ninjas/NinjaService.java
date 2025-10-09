package CadastroDeNinjas.Ninjas;

import CadastroDeNinjas.Missoes.MissoesModel;
import CadastroDeNinjas.Missoes.MissoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;
    private final MissoesRepository missoesRepository;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper, MissoesRepository missoesRepository) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
        this.missoesRepository = missoesRepository;
    }

    //Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    //Listar todos os meus ninjas por ID
    public NinjaDTO listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
        return ninjaModel.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDTO buscarNinjaParaAtualizacao(Long id) {
        return listarNinjasPorId(id);
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        MissoesModel missaoModel = null;

        if (ninjaDTO.getIdMissao() != null) {
            missaoModel = missoesRepository.findById(ninjaDTO.getIdMissao()).orElse(null);
        }

        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);
        if (missaoModel != null) {
            ninjaModel.setMissoes(missaoModel);
        }
        NinjaModel ninjaSalvo = ninjaRepository.save(ninjaModel);

        return ninjaMapper.map(ninjaSalvo);
    }

    //Deletar o ninja - Tem que ser um metodo VOID
    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id);
    }

    //Atualizar ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()) {
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }

}
