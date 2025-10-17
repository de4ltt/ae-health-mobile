package feo.health.mobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

internal class HViewModelProvider(
    private val owner: ViewModelStoreOwner,
) {
    internal inline operator fun <reified T : ViewModel> invoke(): T {
        val factory = ViewModelRegister.getFactory(T::class)
        return ViewModelProvider(owner = owner, factory = factory)[T::class]
    }
}