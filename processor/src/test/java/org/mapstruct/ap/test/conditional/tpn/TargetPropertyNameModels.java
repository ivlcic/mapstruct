package org.mapstruct.ap.test.conditional.tpn;

import java.util.List;

/**
 * @author Nikola Ivačič <nikola.ivacic@dropchop.org> on 28. 04. 22.
 */
public interface TargetPropertyNameModels {
    interface Entity {}
    interface Dto {}

    class Employee implements Entity {
        String name;
        Employee boss;
        Address address;
        List<Employee> subordinates;

        String title;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Employee getBoss() {
            return boss;
        }

        public void setBoss(Employee boss) {
            this.boss = boss;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public List<Employee> getSubordinates() {
            return subordinates;
        }

        public void setSubordinates(List<Employee> subordinates) {
            this.subordinates = subordinates;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    class Address implements Entity {
        String street;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }

    class EmployeeDto implements Dto {
        String name;
        EmployeeDto boss;
        AddressDto address;
        List<EmployeeDto> subordinates;
        String title;

        public EmployeeDto() {
        }

        public EmployeeDto(String name, EmployeeDto boss, AddressDto address) {
            this.name = name;
            this.boss = boss;
            this.address = address;
        }

        public EmployeeDto(String name, AddressDto address, List<EmployeeDto> subordinates) {
            this.name = name;
            this.address = address;
            this.subordinates = subordinates;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public EmployeeDto getBoss() {
            return boss;
        }

        public void setBoss(EmployeeDto boss) {
            this.boss = boss;
        }

        public AddressDto getAddress() {
            return address;
        }

        public void setAddress(AddressDto address) {
            this.address = address;
        }

        public List<EmployeeDto> getSubordinates() {
            return subordinates;
        }

        public void setSubordinates(List<EmployeeDto> subordinates) {
            this.subordinates = subordinates;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    class AddressDto implements Dto {
        String street;

        public AddressDto() {
        }

        public AddressDto(String street) {
            this.street = street;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }
}
