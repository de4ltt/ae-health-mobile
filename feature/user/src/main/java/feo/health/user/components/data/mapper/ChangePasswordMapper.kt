package feo.health.user.components.data.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.dto.request.ChangePasswordRequest

@Mapper
private object ChangePasswordMapper : IMapper<ChangePasswordDomain, ChangePasswordRequest> {
    override fun ChangePasswordDomain.toSecond(): ChangePasswordRequest =
        ChangePasswordRequest(oldPassword = oldPassword, newPassword = newPassword)

    override fun ChangePasswordRequest.toFirst(): ChangePasswordDomain =
        ChangePasswordDomain(oldPassword = oldPassword, newPassword = newPassword)
}