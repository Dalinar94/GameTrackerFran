package com.fran.gametrackerdefran.validation

import com.fran.gametrackerdefran.data.model.GameFormErrors
import com.fran.gametrackerdefran.data.model.GameFormState

object GameFormValidator {

    fun validate(form: GameFormState): ValidationResult {

        var errors = GameFormErrors()

        if (form.nombre.isBlank()) {

            errors = errors.copy(
                nombre = "El nombre es obligatorio"
            )

        }

        if (form.plataforma.isBlank()) {

            errors = errors.copy(
                plataforma = "Selecciona una plataforma"
            )

        }

        if (form.horas.isBlank()) {

            errors = errors.copy(
                horas = "Introduce las horas"
            )

        }

        if (form.rating == 0) {

            errors = errors.copy(
                rating = "Selecciona una valoración"
            )

        }

        if (form.estado == null) {

            errors = errors.copy(
                estado = "Selecciona un estado"
            )

        }

        val isValid =

            errors.nombre == null &&
                    errors.plataforma == null &&
                    errors.horas == null &&
                    errors.rating == null &&
                    errors.estado == null

        return ValidationResult(
            isValid = isValid,
            errors = errors
        )

    }

}