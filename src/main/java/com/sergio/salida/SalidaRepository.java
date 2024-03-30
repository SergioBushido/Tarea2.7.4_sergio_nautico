// SalidaRepository.java
package com.sergio.salida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Integer> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
}
