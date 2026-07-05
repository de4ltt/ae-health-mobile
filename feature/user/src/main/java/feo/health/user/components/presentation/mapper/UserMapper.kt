package feo.health.user.components.presentation.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.UserDomain
import feo.health.user.components.presentation.model.User

/**
 * Mapper that converts [User] (presentation model) to [UserDomain] (domain model) and vice-versa.
 */
@Mapper
private object UserMapper : IMapper<User, UserDomain> {
    /**
     * Converts a [User] presentation model to its [UserDomain] domain model representation.
     *
     * @return The converted [UserDomain].
     */
    override fun User.toSecond(): UserDomain = UserDomain(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )

    /**
     * Converts a [UserDomain] domain model back to its [User] presentation model representation.
     *
     * @return The converted [User].
     */
    override fun UserDomain.toFirst(): User = User(
        name = name,
        email = email,
        dateOfBirth = dateOfBirth,
        weightKg = weightKg,
        height = height
    )
}