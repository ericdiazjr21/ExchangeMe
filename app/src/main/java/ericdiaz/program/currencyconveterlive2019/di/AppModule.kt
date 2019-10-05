package ericdiaz.program.currencyconveterlive2019.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class AppModule {
    /*
   This seems to be a more efficient way of creating modules because dagger will generate the
   necessary code for the abstract methods

   Reference - https://proandroiddev.com/dagger-2-annotations-binds-contributesandroidinjector-a09e6a57758f

     <p>{@code @Binds} methods are a drop-in replacement for {@link Provides} methods that simply
 * return an injected parameter. Prefer {@code @Binds} because the generated implementation is
 * likely to be more efficient.

    Must be {@code abstract}.
 *  May be {@linkplain javax.inject.Scope scoped}.
 *  May be {@linkplain javax.inject.Qualifier qualified}.
 *  Must have a single parameter whose type is assignable to the return type. The return type
 *       declares the bound type (just as it would for a {@literal @}{@link dagger.Provides} method)
 *       and the parameter is the type to which it is bound.
     */
//    @Singleton
//    @Binds
//    abstract fun providesApplication(application: CurrencyConverterApplication): CurrencyConverterApplication


    //This non-abstract method can coexist within an abstract class.
    @Module
    companion object {

        @Provides
        @JvmStatic /* *  <- Specifies that an additional static method needs to be generated from this element if it's a function.
                      * If this element is a property, additional static getter/setter methods should be generated. */
        fun providesApplicationContext(application: CurrencyConverterApplication): Context {
            return application.applicationContext
        }
    }
}