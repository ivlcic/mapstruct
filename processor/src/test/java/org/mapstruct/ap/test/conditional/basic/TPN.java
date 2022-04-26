/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conditional.basic;

import java.util.List;

/**
 * Target Property Name (TPN) test entities
 *
 * @author Nikola Ivačič
 */
public interface TPN {

    class TPNEmployee implements TPN {

        private String firstName;
        private String lastName;
        private String title;
        private String country;
        private boolean active;
        private int age;

        private TPNEmployee boss;

        private TPNAddress primaryAddress;

        private List<TPNAddress> addresses;

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

        public TPNEmployee getBoss() {
            return boss;
        }

        public void setBoss(TPNEmployee boss) {
            this.boss = boss;
        }

        public TPNAddress getPrimaryAddress() {
            return primaryAddress;
        }

        public void setPrimaryAddress(TPNAddress primaryAddress) {
            this.primaryAddress = primaryAddress;
        }

        public List<TPNAddress> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<TPNAddress> addresses) {
            this.addresses = addresses;
        }
    }

    class TPNAddress implements TPN {
        private String street;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }
}
