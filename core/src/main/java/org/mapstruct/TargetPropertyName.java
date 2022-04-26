/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks a <em>presence check method</em> parameter as a property name parameter.
 * <p>
 *   This parameter enables conditional filtering based on source property name in run-time.
 * </p>
 * @author Nikola Ivačič.
 * @since 1.5
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.CLASS)
public @interface TargetPropertyName {
}
