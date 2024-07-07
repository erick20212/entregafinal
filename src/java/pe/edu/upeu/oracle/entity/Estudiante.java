package pe.edu.upeu.oracle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/*atributos con sus tipos de datso , int , ...*/

public class Estudiante {
    private int idestudiante;
    private String nombre;
    private String apellido;
}
    