package com.example.rent2gojavaproject.core.utilities.constants;

public enum HibernateConstants {
    IS_ACTIVE_FILTER_USER("isActiveFilterUser"),
    IS_ACTIVE_FILTER_CAR("isActiveFilterCar"),
    IS_ACTIVE_FILTER_CUSTOMER("isActiveFilterCustomer"),
    IS_ACTIVE_FILTER_RENTAL("isActiveFilterRental"),
    IS_ACTIVE_FILTER_BILL("isActiveFilterBill"),
    IS_ACTIVE_FILTER_DISCOUNT("isActiveFilterDiscount"),
    IS_ACTIVE_FILTER_MODEL("isActiveFilterModel"),
    IS_ACTIVE_FILTER_BRAND("isActiveFilterBrand"),
    IS_ACTIVE_FILTER_EMPLOYEE("isActiveFilterEmployee"),
    IS_ACTIVE_FILTER_COLOR("isActiveFilterColor"),

    IS_ACTIVE("isActive");

    private final String value;

    HibernateConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
