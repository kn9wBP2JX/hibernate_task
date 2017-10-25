package com.company;

import com.company.view.AccountViewImplTest;
import com.company.view.ClientViewImplTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/*
Naming convention for tests: MethodName_StateUnderTest_ExpectedBehavior
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({
        AccountViewImplTest.class,
        ClientViewImplTest.class
})
public class UnitTestSuite {
}
