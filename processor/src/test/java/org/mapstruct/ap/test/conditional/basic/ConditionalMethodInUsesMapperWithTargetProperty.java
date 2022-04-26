/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conditional.basic;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.TargetProperty;
import org.mapstruct.ap.test.conditional.basic.TargetPropertyModel.Employee;
import org.mapstruct.ap.test.conditional.basic.TargetPropertyModel.EmployeeDto;
import org.mapstruct.factory.Mappers;

/**
 * @author Filip Hrisafov
 * @author Nikola Ivačič
 */
@Mapper(uses = ConditionalMethodInUsesMapperWithTargetProperty.PresenceUtils.class)
public interface ConditionalMethodInUsesMapperWithTargetProperty {

    ConditionalMethodInUsesMapperWithTargetProperty INSTANCE
            = Mappers.getMapper( ConditionalMethodInUsesMapperWithTargetProperty.class );

    Employee map(EmployeeDto employee);

    class PresenceUtils {

        @Condition
        public boolean isNotBlank(String value, @TargetProperty String propName) {
            if ( propName.equalsIgnoreCase( "lastName" ) ) {
                return false;
            }
            return value != null && !value.trim().isEmpty();
        }

    }
}
