package com.crazyemperor.construction_management;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class Mock {

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }
}
