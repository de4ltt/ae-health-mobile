package feo.health.auth.di

import dagger.BindsInstance
import dagger.Component
import feo.health.auth.api.IAuthApi
import feo.health.auth.di.module.AuthModule
import feo.health.network.di.component.AuthEndpointsComponent
import feo.health.network.di.component.NetworkComponent

@NetworkAuthScope
@Component(modules = [AuthModule::class], dependencies = [NetworkComponent::class, AuthEndpointsComponent::class])
interface NetworkAuthComponent {

    fun authApi(): IAuthApi

    @Component.Builder
    interface Builder {

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        fun bindAuthEndpointsComponent(authEndpointsComponent: AuthEndpointsComponent): Builder

        fun build(): NetworkAuthComponent
    }
}