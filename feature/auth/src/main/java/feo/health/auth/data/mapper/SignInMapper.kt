package feo.health.auth.data.mapper

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.dto.request.SignInRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SignInMapper: IMapper<SignInRequest, SignInDomain> {
    override fun SignInRequest.toSecond(): SignInDomain = SignInDomain(
        email = email,
        password = password
    )
    override fun SignInDomain.toFirst(): SignInRequest = SignInRequest(
        email = email,
        password = password
    )
}