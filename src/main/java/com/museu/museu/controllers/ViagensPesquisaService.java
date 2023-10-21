import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museu.museu.domain.ViagensPesquisa;
import com.museu.museu.repositories.ViagensPesquisaRepository;

@Service
public class ViagensPesquisaService {

    private final ViagensPesquisaRepository viagensPesquisaRepository;

    @Autowired
    public ViagensPesquisaService(ViagensPesquisaRepository viagensPesquisaRepository) {
        this.viagensPesquisaRepository = viagensPesquisaRepository;
    }

    public List<ViagensPesquisa> getViagensPesquisaByPesquisadorRole() {
        return viagensPesquisaRepository.findByFuncionarioRole(Role.PESQUISADOR);
    }
}
