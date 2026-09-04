package pe.edu.utp.apf1backend.repositories;

import pe.edu.utp.apf1backend.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}