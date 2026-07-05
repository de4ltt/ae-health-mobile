package feo.health.auth.data.mapper

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.dto.request.SignInRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serializable [SignInRequest] and domain [SignInDomain].
 */
@Mapper
private object SignInMapper: IMapper<SignInRequest, SignInDomain> {
    /**
     * Converts a [SignInRequest] request model to its corresponding domain [SignInDomain] entity.
     *
     * @return Resolved [SignInDomain].
     */
    override fun SignInRequest.toSecond(): SignInDomain = SignInDomain(
        email = email,
        password = password
    )

    /**
     * Converts a [SignInDomain] domain entity to its corresponding serial [SignInRequest] model.
     *
     * @return Resolved [SignInRequest].
     */
    override fun SignInDomain.toFirst(): SignInRequest = SignInRequest(
        email = email,
        password = password
    )
}