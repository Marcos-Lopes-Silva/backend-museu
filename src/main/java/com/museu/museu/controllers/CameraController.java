import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Camera;
import com.museu.museu.repositories.CameraRepository;

@RestController
@RequestMapping("/cameras")
public class CameraController {

    @Autowired
    private CameraRepository cameraRepository;

    // Operação para criar uma nova câmera
    @PostMapping("/criar")
    public ResponseEntity<DadosCamera> criarCamera(@RequestBody Camera camera) {
        Camera novaCamera = cameraRepository.save(camera);
        DadosCamera dadosCamera = new DadosCamera(novaCamera.getId(), novaCamera.getModelo(), novaCamera.getMarca(), novaCamera.getResolucao());
        return ResponseEntity.ok(dadosCamera);
    }

    // Operação para listar todas as câmeras
    @GetMapping
    public ResponseEntity<List<DadosCamera>> listarCameras() {
        List<Camera> cameras = cameraRepository.findAll();
        List<DadosCamera> dadosCameras = cameras.stream()
                .map(camera -> new DadosCamera(camera.getId(), camera.getModelo(), camera.getMarca(), camera.getResolucao()))
                .toList();
        return ResponseEntity.ok(dadosCameras);
    }

    // Operação para buscar uma câmera por ID
    @GetMapping("/{id}")
    public ResponseEntity<DadosCamera> detalharCamera(@PathVariable Integer id) {
        Optional<Camera> cameraOptional = cameraRepository.findById(id);
        if (cameraOptional.isPresent()) {
            Camera camera = cameraOptional.get();
            DadosCamera dadosCamera = new DadosCamera(camera.getId(), camera.getModelo(), camera.getMarca(), camera.getResolucao());
            return ResponseEntity.ok(dadosCamera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Operação para atualizar uma câmera por ID
    @PutMapping("/{id}")
    public ResponseEntity<DadosCamera> atualizarCamera(@PathVariable Integer id, @RequestBody Camera cameraAtualizada) {
        Optional<Camera> cameraOptional = cameraRepository.findById(id);
        if (cameraOptional.isPresent()) {
            Camera cameraExistente = cameraOptional.get();
            cameraExistente.setModelo(cameraAtualizada.getModelo());
            cameraExistente.setMarca(cameraAtualizada.getMarca());
            cameraExistente.setResolucao(cameraAtualizada.getResolucao());
            cameraRepository.save(cameraExistente);
            DadosCamera dadosCamera = new DadosCamera(cameraExistente.getId(), cameraExistente.getModelo(), cameraExistente.getMarca(), cameraExistente.getResolucao());
            return ResponseEntity.ok(dadosCamera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Operação para excluir uma câmera por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCamera(@PathVariable Integer id) {
        Optional<Camera> cameraOptional = cameraRepository.findById(id);
        if (cameraOptional.isPresent()) {
            cameraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
