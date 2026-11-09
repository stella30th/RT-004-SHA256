package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE043 {

@Test
public void testAddAndGetEmployee() {
    HRManagementSystem hr = new HRManagementSystem();
    assertTrue(hr.addEmployee(101, "Alice", "Developer", "IT", 80000.0));
    assertFalse(hr.addEmployee(101, "Duplicate", "Manager", "IT", 90000.0));

    Object employee = hr.getEmployee(101);
    assertTrue(employee instanceof Map);
    Map<?, ?> info = (Map<?, ?>) employee;
    assertEquals("Alice", info.get("name"));
    assertEquals("Developer", info.get("position"));
    assertEquals("IT", info.get("department"));
    assertEquals(80000.0, info.get("salary"));
}

@Test
public void testGetNonExistentEmployeeReturnsFalse() {
    HRManagementSystem hr = new HRManagementSystem();
    assertEquals(false, hr.getEmployee(999));
}

@Test
public void testRemoveEmployee() {
    HRManagementSystem hr = new HRManagementSystem();
    hr.addEmployee(102, "Bob", "Analyst", "Finance", 70000.0);

    assertTrue(hr.removeEmployee(102));
    assertFalse(hr.removeEmployee(102));
    assertEquals(false, hr.getEmployee(102));
}

@Test
public void testUpdateEmployee() {
    HRManagementSystem hr = new HRManagementSystem();
    hr.addEmployee(103, "Charlie", "Junior Dev", "IT", 60000.0);

    Map<String, Object> validUpdate = new HashMap<>();
    validUpdate.put("position", "Senior Dev");
    validUpdate.put("salary", 95000.0);
    assertTrue(hr.updateEmployee(103, validUpdate));

    Map<?, ?> info = (Map<?, ?>) hr.getEmployee(103);
    assertEquals("Senior Dev", info.get("position"));
    assertEquals(95000.0, info.get("salary"));

    // Invalid key should be rejected
    Map<String, Object> invalidUpdate = new HashMap<>();
    invalidUpdate.put("bonus", 5000.0);
    assertFalse(hr.updateEmployee(103, invalidUpdate));

    // Non-existent employee cannot be updated
    assertFalse(hr.updateEmployee(999, validUpdate));
}

@Test
public void testListEmployees() {
    HRManagementSystem hr = new HRManagementSystem();
    hr.addEmployee(1, "David", "QA", "IT", 65000.0);
    hr.addEmployee(2, "Eve", "Designer", "UX", 72000.0);

    Map<Integer, Map<String, Object>> list = hr.listEmployees();
    assertEquals(2, list.size());
    assertEquals(1, list.get(1).get("employee_ID"));
    assertEquals(2, list.get(2).get("employee_ID"));
}

}
