/**
 * MIT License
 *
 * Copyright (c) 2021-2023 TriumphTeam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.triumphteam.nebula.container.registry

import dev.triumphteam.nebula.container.Container
import dev.triumphteam.nebula.core.annotation.NebulaInternalApi
import dev.triumphteam.nebula.key.Keyed

/** A registry for storing, adding, and getting injection objects. */
public interface InjectionRegistry : Keyed, Iterable<Any> {

    /** A read only map containing all the objects that can be injected in the container. */
    @NebulaInternalApi
    public val instances: Map<Class<*>, Any>

    /** Gets an object based on a class. */
    @NebulaInternalApi
    public fun <T : Any> get(
        clazz: Class<out T>,
        target: Container?,
    ): T?

    /** Puts an object in the values map. */
    @NebulaInternalApi
    public fun <T : Any> put(
        clazz: Class<out T>,
        value: T,
    )
}

/**
 * Binds the provided value to the InjectionRegistry for the specified type T.
 *
 * @param value The value to bind. It should be an instance of type T.
 */
@OptIn(NebulaInternalApi::class)
public inline fun <reified T : Any> InjectionRegistry.bind(value: T) {
    put(T::class.java, value)
}
