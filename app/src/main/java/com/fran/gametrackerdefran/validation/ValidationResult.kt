package com.fran.gametrackerdefran.validation

import com.fran.gametrackerdefran.data.model.GameFormErrors


data class ValidationResult(

    val isValid: Boolean,

    val errors: GameFormErrors = GameFormErrors()

)