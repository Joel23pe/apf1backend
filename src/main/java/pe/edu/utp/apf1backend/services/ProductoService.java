package pe.edu.utp.apf1backend.services;

import pe.edu.utp.apf1backend.dto.ProductoDTO;
import pe.edu.utp.apf1backend.models.Producto;
import pe.edu.utp.apf1backend.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<ProductoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public ProductoDTO guardar(ProductoDTO dto) {
        Producto entidad = toEntity(dto);
        Producto guardado = repository.save(entidad);
        return toDTO(guardado);
    }

    public Optional<ProductoDTO> actualizar(Long id, ProductoDTO dto) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(dto.getNombre());
            existente.setPrecio(dto.getPrecio());
            return toDTO(repository.save(existente));
        });
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    private ProductoDTO toDTO(Producto p) {
        return new ProductoDTO(p.getId(), p.getNombre(), p.getPrecio());
    }

    private Producto toEntity(ProductoDTO dto) {
        return new Producto(dto.getId(), dto.getNombre(), dto.getPrecio());
    }
}