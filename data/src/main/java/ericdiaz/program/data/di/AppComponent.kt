package ericdiaz.program.data.di

import dagger.BindsInstance
import dagger.Component
import ericdiaz.program.data.db.di.DatabaseModule
import ericdiaz.program.data.network.di.NetworkModule
import javax.inject.Singleton

/*
The singleton annotation to tell dagger to create a single instance
of this component.
*/
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DatabaseModule::class])
@Singleton
interface AppComponent : Provider {

    /*
    This annotation helps dagger create a custom builder for this
    component.

    There must be exactly one abstract no-argument method that returns the component
   *type or one of its supertypes, called the "build method".

   *There may be other other abstract methods, called "setter methods".
   *Setter methods must take a single argument and return {@code void}, the builder
   *type or a supertype of the builder type.

   *There must be a setter method for each {@linkplain Component#dependencies
   *component dependency}.

   *There must be a setter method for each non-{@code abstract} {@linkplain
   *Component#modules module} that has non-{@code static} binding methods, unless Dagger can
   *instantiate that module with a visible no-argument constructor.

   *There may be setter methods for modules that Dagger can instantiate or does not
   *need to instantiate.

   *There may be setter methods annotated with {@code @BindsInstance}. These methods
   *bind the instance passed to them within the component. See {@link
   *BindsInstance @BindsInstance} for more information.

   *There may be non-{@code abstract} methods, but they are ignored as far as
   *       validation and builder generation are concerned.
     */
    @Component.Builder
    interface Builder {

        /*
        * <p>will allow clients of the builder or factory to pass their own instances of {@code Foo} and
        * {@code Bar}, and those instances can be injected within the component as {@code Foo} or
        * {@code @Blue Bar}, respectively.
        *

        * <p>Binding an instance is equivalent to passing an instance to a module constructor and providing
        * that instance, but is often more efficient. When possible, binding object instances should be
        * preferred to using module instances.
         */
        @BindsInstance
        fun application(application: CurrencyConverterApplication): Builder

        fun build(): AppComponent
    }

    fun inject(currencyConverterApplication: CurrencyConverterApplication)

}