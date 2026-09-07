package pe.edu.utp.apf1backend.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

// Importa tus clases reales (ajusta si tus paquetes difieren)
import pe.edu.utp.apf1backend.models.Producto; 
import pe.edu.utp.apf1backend.repositories.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test
    void buscarPorId_debeRetornarProducto_cuandoExiste() {
        Producto producto = new Producto(1L, "Monitor 27", 345.90);
        when(repository.findById(1L)).thenReturn(Optional.of(producto));
        
        var resultado = service.buscarPorId(1L);
        
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Monitor 27");
    }

    @Test
    void buscarPorId_debeRetornarVacio_cuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        
        var resultado = service.buscarPorId(99L);
        
        assertThat(resultado).isEmpty();
    }
}