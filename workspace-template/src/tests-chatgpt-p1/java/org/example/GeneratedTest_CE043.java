package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE043 {

@Test public void missingEmployeeReturnsBooleanFalse() {
 HRManagementSystem h=new HRManagementSystem();
 assertEquals(Boolean.FALSE,h.getEmployee(42));
 assertFalse(h.removeEmployee(42)); assertFalse(h.updateEmployee(42,java.util.Collections.emptyMap()));
}
@Test public void addUpdateListAndRemove() {
 HRManagementSystem h=new HRManagementSystem();
 assertTrue(h.addEmployee(1,"Ann","Dev","IT",100.0));
 assertFalse(h.addEmployee(1,"Other","HR","HR",2.0));
 assertFalse(h.updateEmployee(1,java.util.Collections.singletonMap("invalid","x")));
 assertTrue(h.updateEmployee(1,java.util.Collections.singletonMap("salary",150.0)));
 assertEquals(150.0,(Double)((java.util.Map<?,?>)h.getEmployee(1)).get("salary"),0.0);
 assertEquals(1,h.listEmployees().get(1).get("employee_ID"));
 assertTrue(h.removeEmployee(1)); assertEquals(Boolean.FALSE,h.getEmployee(1));
}

}
