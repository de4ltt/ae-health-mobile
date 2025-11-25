package feo.health.auth.data.mapper

import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.dto.request.SignUpRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SignUpMapper: IMapper<SignUpRequest, SignUpDomain> {
    override fun SignUpRequest.toSecond(): SignUpDomain = SignUpDomain(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        password = password
    )
    override fun SignUpDomain.toFirst(): SignUpRequest = SignUpRequest(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        password = password
    )
}