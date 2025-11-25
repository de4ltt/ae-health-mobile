package feo.health.user.components.data.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.UserDomain
import feo.health.user.dto.common.UserDto

@Mapper
private object UserMapper : IMapper<UserDto, UserDomain> {
    override fun UserDto.toSecond(): UserDomain = UserDomain(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )

    override fun UserDomain.toFirst(): UserDto = UserDto(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )
}