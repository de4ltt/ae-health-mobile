package feo.health.common.model

sealed class AppError(val code: String) {
    sealed class Auth(code: String) : AppError(code) {
    }

    sealed class Catalog(code: String) : AppError(code) {
    }

    sealed class Common(code: String) : AppError(code) {
        object Unknown : Common("0-0")
    }
}
