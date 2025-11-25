package feo.health.user.components.presentation.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.UserDomain
import feo.health.user.components.presentation.model.User

@Mapper
private object UserMapper : IMapper<User, UserDomain> {
    override fun User.toSecond(): UserDomain = UserDomain(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )

    override fun UserDomain.toFirst(): User = User(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )
}