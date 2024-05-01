package com.java.ventaCoffe.model.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private String correoUsuarioDto;
    private String claveUsuarioDto;
    private String resptValidacionUsuarioDto;

}
