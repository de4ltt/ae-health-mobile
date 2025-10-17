package feo.health.user.di

import dagger.BindsInstance
import dagger.Component
import feo.health.network.di.component.NetworkComponent
import feo.health.network.di.component.UserEndpointsComponent
import feo.health.user.api.IUserApi
import feo.health.user.di.module.UserModule

@NetworkUserScope
@Component(modules = [UserModule::class], dependencies = [NetworkComponent::class, UserEndpointsComponent::class])
interface NetworkUserComponent {

    fun userApi(): IUserApi

    @Component.Builder
    interface Builder {

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        fun bindUserEndpointsComponent(userEndpointsComponent: UserEndpointsComponent): Builder

        fun build(): NetworkUserComponent
    }
}