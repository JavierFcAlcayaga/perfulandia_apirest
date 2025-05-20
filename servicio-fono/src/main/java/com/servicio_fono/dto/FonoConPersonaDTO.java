package com.servicio_fono.dto;

import com.servicio_fono.models.Fono;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FonoConPersonaDTO {
    private Fono fono;
    private PersonaDTO persona;
}
