package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericResponseDto<T> {

    private T response;
    private ErrorMessageDto errorMessage;
}
