package feo.health.user.components.data.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.UserDomain
import feo.health.user.dto.common.UserDto

/**
 * Data mapping resolver between serialization [UserDto] and domain [UserDomain] models.
 */
@Mapper
private object UserMapper : IMapper<UserDto, UserDomain> {
    /**
     * Converts a [UserDto] serial model to its corresponding domain [UserDomain] entity.
     *
     * @return Resolved [UserDomain].
     */
    override fun UserDto.toSecond(): UserDomain = UserDomain(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )

    /**
     * Converts a [UserDomain] domain entity to its corresponding serial [UserDto] model.
     *
     * @return Resolved [UserDto].
     */
    override fun UserDomain.toFirst(): UserDto = UserDto(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )
}