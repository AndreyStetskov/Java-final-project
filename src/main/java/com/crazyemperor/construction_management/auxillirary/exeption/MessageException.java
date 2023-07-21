package com.crazyemperor.construction_management.auxillirary.exeption;

public class MessageException {

    void buildingNotFound() {

    }

    void buildingNotFoundById(long id) {
        String.format("No offer found for oid %s", id);
    }
}