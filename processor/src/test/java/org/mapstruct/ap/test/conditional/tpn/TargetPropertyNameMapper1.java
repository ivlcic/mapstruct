package org.mapstruct.ap.test.conditional.tpn;

import org.mapstruct.*;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.Entity;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.Dto;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.Employee;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.EmployeeDto;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.Address;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.AddressDto;
import org.mapstruct.factory.Mappers;

import java.util.*;

/**
 * @author Nikola Ivačič <nikola.ivacic@dropchop.org> on 27. 04. 22.
 */
public interface TargetPropertyNameMapper1 {

    class MapContext {
        Set<String> ignoreProps = new HashSet<>();
        Deque<String> path = new LinkedList<>();
        Deque<String> visited = new LinkedList<>();
        String lastProp = null;

        @Condition
        public boolean collect(@TargetProperty String propName) {
            if ( ignoreProps.contains( propName ) ) {
                return false;
            }
            this.lastProp = propName;
            this.path.offerLast( this.lastProp );
            this.visited.offerLast( String.join( ".", this.path ) );
            this.path.pollLast();
            return true;
        }
    }

    interface FirmMapper<E extends Entity, D extends Dto> {

        E map(D dto, @Context MapContext ctx);

        @BeforeMapping
        default void before(Object dto, @Context MapContext ctx) {
            if (ctx.lastProp != null && dto != null) {
                ctx.path.offerLast( ctx.lastProp );
            }
        }

        @AfterMapping
        default void after(Object dto, @Context MapContext ctx) {
            ctx.path.pollLast();
            ctx.lastProp = ctx.path.peekLast();
        }
    }

    @Mapper
    interface EmployeeMapper extends FirmMapper<Employee, EmployeeDto> {
        EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );
    }

    @Mapper
    interface AddressMapper extends FirmMapper<Address, AddressDto> {
        AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );
    }
}
