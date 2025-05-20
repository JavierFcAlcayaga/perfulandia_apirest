package com.servicio_fono.dto;

import lombok.Data;

@Data
public class PersonaDTO {
    private Integer id;
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private String telefono;
    private String email;
}
