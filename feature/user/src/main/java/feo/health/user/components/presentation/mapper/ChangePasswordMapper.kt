package feo.health.user.components.presentation.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.presentation.model.ChangePassword
import feo.health.user.dto.request.ChangePasswordRequest

@Mapper
private object ChangePasswordMapper : IMapper<ChangePasswordDomain, ChangePassword> {
    override fun ChangePasswordDomain.toSecond(): ChangePassword =
        ChangePassword(oldPassword = oldPassword, newPassword = newPassword)

    override fun ChangePassword.toFirst(): ChangePasswordDomain =
        ChangePasswordDomain(oldPassword = oldPassword, newPassword = newPassword)
}