package feo.health.secrets.di

import dagger.BindsInstance
import dagger.Component
import feo.health.secrets.HSecrets

@CoreSecretsScope
@Component
interface CoreSecretsComponent {

    fun secrets(): HSecrets

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindSecrets(secrets: HSecrets): Builder

        fun build(): CoreSecretsComponent
    }
}