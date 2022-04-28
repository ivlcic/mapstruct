package org.mapstruct.ap.test.conditional.tpn;

import org.mapstruct.*;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.*;
import org.mapstruct.factory.Mappers;

import java.util.*;

/**
 * @author Nikola Ivačič
 */
public interface TargetPropertyNameMapper2 {

    class PathSegment {
        String name;
        boolean cycleDetected;

        public PathSegment(String name, boolean cycleDetected) {
            this.name = name;
            this.cycleDetected = cycleDetected;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    class CollectionPathSegment extends PathSegment {
        int index;

        public CollectionPathSegment(String name, boolean cycleDetected) {
            super( name, cycleDetected );
        }
    }

    class CollectionElementPathSegment extends PathSegment {
        int index;
        public CollectionElementPathSegment(String name, int index, boolean cycleDetected) {
            super( name, cycleDetected );
            this.index = index;
        }
    }

    class MapContext {
        Set<String> ignoreProps = new HashSet<>();
        Set<Integer> mapped = new HashSet<>();
        Deque<PathSegment> path = new LinkedList<>();
        Deque<String> visited = new LinkedList<>();
        String lastProp = null;

        private String pathToString() {
            StringBuilder strPath = new StringBuilder();
            for ( PathSegment segment : path ) {
                if ( segment instanceof CollectionElementPathSegment ) {
                    strPath.append( segment.name );
                } else {
                    if ( strPath.length() > 0 ) {
                        strPath.append( "." );
                    }
                    strPath.append( segment.name );
                }
            }
            if (path.isEmpty()) {
                return lastProp;
            }
            strPath.append( "." );
            strPath.append( lastProp );
            return strPath.toString();
        }

        @Condition
        public boolean collect(@TargetPropertyName String propName) {
            PathSegment segment = path.peekLast();
            if ( ignoreProps.contains( propName ) || (segment != null && segment.cycleDetected)) {
                return false;
            }
            this.lastProp = propName;
            this.visited.offerLast( pathToString() );
            return true;
        }
    }

    interface FirmMapper<E extends Entity, D extends Dto> {

        E map(D dto, @Context MapContext ctx);

        @BeforeMapping
        default void before(Object dto, @Context MapContext ctx) {
            if ( dto == null ) {
                return;
            }
            boolean cycleDetected = !ctx.mapped.add( dto.hashCode() );

            if ( ctx.lastProp == null ) {
                return;
            }
            if ( dto instanceof Collection ) {
                ctx.path.offerLast( new CollectionPathSegment( ctx.lastProp, cycleDetected ) );
            } else {
                PathSegment segment = ctx.path.peekLast();
                if (segment instanceof CollectionPathSegment) {
                    ctx.path.offerLast(
                            new CollectionElementPathSegment(
                                    "[" + ( (CollectionPathSegment) segment ).index + "]",
                                    ( (CollectionPathSegment) segment ).index,
                                    cycleDetected
                            )
                    );
                    ( (CollectionPathSegment) segment ).index++;
                } else {
                    ctx.path.offerLast( new PathSegment( ctx.lastProp, cycleDetected ) );
                }
            }
        }

        @AfterMapping
        default void after(Object dto, @Context MapContext ctx) {
            ctx.path.pollLast();
            PathSegment segment = ctx.path.peekLast();
            if ( segment != null ) {
                ctx.lastProp = segment.name;
            } else {
                ctx.lastProp = null;
            }
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
