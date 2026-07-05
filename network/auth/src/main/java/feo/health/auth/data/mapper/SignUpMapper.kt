package feo.health.auth.data.mapper

import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.dto.request.SignUpRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serializable [SignUpRequest] and domain [SignUpDomain].
 */
@Mapper
private object SignUpMapper: IMapper<SignUpRequest, SignUpDomain> {
    /**
     * Converts a [SignUpRequest] request model to its corresponding domain [SignUpDomain] entity.
     *
     * @return Resolved [SignUpDomain].
     */
    override fun SignUpRequest.toSecond(): SignUpDomain = SignUpDomain(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        password = password
    )

    /**
     * Converts a [SignUpDomain] domain entity to its corresponding serial [SignUpRequest] model.
     *
     * @return Resolved [SignUpRequest].
     */
    override fun SignUpDomain.toFirst(): SignUpRequest = SignUpRequest(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        password = password
    )
}