package feo.health.mobile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

internal object ViewModelRegister {

    private val registry: MutableMap<KClass<out ViewModel>, ViewModelProvider.Factory> =
        mutableMapOf()

    internal inline fun <reified T : ViewModel> register(factory: ViewModelProvider.Factory): ViewModelRegister {
        registry[T::class] = factory
        return this
    }

    fun <T : ViewModel> getFactory(modelClass: KClass<T>): ViewModelProvider.Factory {
        return registry[modelClass]
            ?: throw IllegalArgumentException("Factory not found for ${modelClass.simpleName}")
    }
}