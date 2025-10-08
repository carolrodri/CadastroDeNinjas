package CadastroDeNinjas.Missoes;

public class MissoesMapper {

    public MissoesModel map (MissoesDTO missoesDTO) {

        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());

        return missoesModel;
    }

    public MissoesDTO map (MissoesModel missoesModel) {

        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(missoesDTO.getId());
        missoesDTO.setNome(missoesDTO.getNome());
        missoesDTO.setDificuldade(missoesDTO.getDificuldade());

        return missoesDTO;
    }

}
