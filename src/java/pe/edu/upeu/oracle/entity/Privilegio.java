package pe.edu.upeu.oracle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Docente
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilegio {
    private int idprivilegio;
    private String nombre;
    private String url;
    private String icono;
}
