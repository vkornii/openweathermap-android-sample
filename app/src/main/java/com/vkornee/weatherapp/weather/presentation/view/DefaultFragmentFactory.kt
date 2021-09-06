package com.vkornee.weatherapp.weather.presentation.view

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class DefaultFragmentFactory @Inject constructor(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = creators[fragmentClass] ?: return createDefault(classLoader, className)
        return creator.get()
    }

    private fun createDefault(classLoader: ClassLoader, className: String): Fragment {
        Log.w("DefaultFragmentFactory","Unregistered fragment: $className, create by default")
        return super.instantiate(classLoader, className)
    }

}