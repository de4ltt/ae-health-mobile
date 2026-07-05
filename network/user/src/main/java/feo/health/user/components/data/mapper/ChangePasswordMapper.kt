package feo.health.user.components.data.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.dto.request.ChangePasswordRequest

/**
 * Data mapping resolver between domain [ChangePasswordDomain] and serialization [ChangePasswordRequest] models.
 */
@Mapper
private object ChangePasswordMapper : IMapper<ChangePasswordDomain, ChangePasswordRequest> {
    /**
     * Converts a [ChangePasswordDomain] domain entity to its corresponding serial [ChangePasswordRequest] model.
     *
     * @return Resolved [ChangePasswordRequest].
     */
    override fun ChangePasswordDomain.toSecond(): ChangePasswordRequest =
        ChangePasswordRequest(oldPassword = oldPassword, newPassword = newPassword)

    /**
     * Converts a [ChangePasswordRequest] serial model to its corresponding domain [ChangePasswordDomain] entity.
     *
     * @return Resolved [ChangePasswordDomain].
     */
    override fun ChangePasswordRequest.toFirst(): ChangePasswordDomain =
        ChangePasswordDomain(oldPassword = oldPassword, newPassword = newPassword)
}