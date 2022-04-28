package org.mapstruct.ap.test.conditional.tpn;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.AddressDto;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.Employee;
import org.mapstruct.ap.test.conditional.tpn.TargetPropertyNameModels.EmployeeDto;
import org.mapstruct.ap.testutil.ProcessorTest;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.GeneratedSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Nikola Ivačič <nikola.ivacic@dropchop.org> on 27. 04. 22.
 */
@WithClasses({
        TargetPropertyNameModels.class
})
public class TargetPropertyNameMapperTest {

    @RegisterExtension
    final GeneratedSource generatedSource = new GeneratedSource();

    @ProcessorTest
    @WithClasses({
            TargetPropertyNameMapper1.class
    })
    public void testTargetPropertyNameMapper1() {
        TargetPropertyNameMapper1.EmployeeMapper mapper = TargetPropertyNameMapper1.EmployeeMapper.INSTANCE;
        TargetPropertyNameMapper1.MapContext context = new TargetPropertyNameMapper1.MapContext();
        context.ignoreProps.add( "boss" );

        EmployeeDto bossDto = new EmployeeDto( "Boss", new AddressDto("Testing st. 18"), new ArrayList<>() );
        bossDto.subordinates.add( new EmployeeDto( "Employee1", bossDto, new AddressDto("Street 1") ) );
        bossDto.subordinates.add( new EmployeeDto( "Employee2", bossDto, new AddressDto("Street 2") ) );
        bossDto.subordinates.add( new EmployeeDto( "Employee3", bossDto, new AddressDto("Street 3") ) );

        Employee boss = mapper.map( bossDto, context );
        assertThat( List.of(
                "name",
                "address",
                "address.street",
                "subordinates",
                "subordinates.subordinates.name",
                "subordinates.subordinates.address",
                "subordinates.subordinates.address.street",
                "subordinates.subordinates.subordinates",
                "subordinates.subordinates.title",
                "subordinates.subordinates.name",
                "subordinates.subordinates.address",
                "subordinates.subordinates.address.street",
                "subordinates.subordinates.subordinates",
                "subordinates.subordinates.title",
                "subordinates.subordinates.name",
                "subordinates.subordinates.address",
                "subordinates.subordinates.address.street",
                "subordinates.subordinates.subordinates",
                "subordinates.subordinates.title",
                "title" )
        ).isEqualTo( context.visited );
    }

    @ProcessorTest
    @WithClasses({
            TargetPropertyNameMapper2.class
    })
    public void testTargetPropertyNameMapper2() {
        TargetPropertyNameMapper2.EmployeeMapper mapper = TargetPropertyNameMapper2.EmployeeMapper.INSTANCE;
        TargetPropertyNameMapper2.MapContext context = new TargetPropertyNameMapper2.MapContext();
        //context.ignoreProps.add( "boss" );

        EmployeeDto bossDto = new EmployeeDto( "Boss", new AddressDto("Testing st. 18"), new ArrayList<>() );
        bossDto.subordinates.add( new EmployeeDto( "Employee1", bossDto, new AddressDto("Street 1") ) );
        bossDto.subordinates.add( new EmployeeDto( "Employee2", bossDto, new AddressDto("Street 2") ) );
        bossDto.subordinates.add( new EmployeeDto( "Employee3", bossDto, new AddressDto("Street 3") ) );

        Employee boss = mapper.map( bossDto, context );
        assertThat( List.of(
                "name",
                "boss",
                "address",
                "address.street",
                "subordinates",
                "subordinates[0].name",
                "subordinates[0].boss",
                "subordinates[0].address",
                "subordinates[0].address.street",
                "subordinates[0].subordinates",
                "subordinates[0].title",
                "subordinates[1].name",
                "subordinates[1].boss",
                "subordinates[1].address",
                "subordinates[1].address.street",
                "subordinates[1].subordinates",
                "subordinates[1].title",
                "subordinates[2].name",
                "subordinates[2].boss",
                "subordinates[2].address",
                "subordinates[2].address.street",
                "subordinates[2].subordinates",
                "subordinates[2].title",
                "title"
        ) ).isEqualTo( context.visited );
    }
}
