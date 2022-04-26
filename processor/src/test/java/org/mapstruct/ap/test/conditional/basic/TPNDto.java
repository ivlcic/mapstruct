/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conditional.basic;

import java.util.List;

/**
 * Target Property Name DTO (TPNDto) test data transfer objects.
 *
 * @author Nikola Ivačič
 */
public interface TPNDto {

    class TPNEmployeeDto implements TPNDto {

        private String firstName;
        private String lastName;
        private String title;
        private String country;
        private boolean active;
        private int age;

        private TPNEmployeeDto boss;

        private TPNAddressDto primaryAddress;
        private List<TPNAddressDto> addresses;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public TPNEmployeeDto getBoss() {
            return boss;
        }

        public void setBoss(TPNEmployeeDto boss) {
            this.boss = boss;
        }

        public TPNAddressDto getPrimaryAddress() {
            return primaryAddress;
        }

        public void setPrimaryAddress(TPNAddressDto primaryAddress) {
            this.primaryAddress = primaryAddress;
        }

        public List<TPNAddressDto> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<TPNAddressDto> addresses) {
            this.addresses = addresses;
        }
    }

    class TPNAddressDto implements TPNDto {
        private String street;

        public TPNAddressDto() {
        }

        public TPNAddressDto(String street) {
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
