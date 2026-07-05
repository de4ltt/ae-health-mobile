package feo.health.user.components.presentation.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.presentation.model.ChangePassword

/**
 * Mapper that converts [ChangePasswordDomain] (domain model) to [ChangePassword] (presentation model) and vice-versa.
 */
@Mapper
private object ChangePasswordMapper : IMapper<ChangePasswordDomain, ChangePassword> {
    /**
     * Converts a [ChangePasswordDomain] domain model to its [ChangePassword] presentation model representation.
     *
     * @return The converted [ChangePassword].
     */
    override fun ChangePasswordDomain.toSecond(): ChangePassword =
        ChangePassword(oldPassword = oldPassword, newPassword = newPassword)

    /**
     * Converts a [ChangePassword] presentation model back to its [ChangePasswordDomain] domain model representation.
     *
     * @return The converted [ChangePasswordDomain].
     */
    override fun ChangePassword.toFirst(): ChangePasswordDomain =
        ChangePasswordDomain(oldPassword = oldPassword, newPassword = newPassword)
}