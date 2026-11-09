// BEGIN GeneratedTest_CE043
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class GeneratedTest_CE043 {

    @Test
    public void testInitialState() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.listEmployees().isEmpty());
        assertEquals(Boolean.FALSE, system.getEmployee(1));
        assertFalse(system.removeEmployee(1));
        assertFalse(system.updateEmployee(1, new HashMap<>()));
    }

    @Test
    public void testAddEmployee() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(1, "Alice", "Developer", "IT", 5000.0));

        Object result = system.getEmployee(1);
        assertTrue(result instanceof Map);

        Map<?, ?> employee = (Map<?, ?>) result;
        assertEquals("Alice", employee.get("name"));
        assertEquals("Developer", employee.get("position"));
        assertEquals("IT", employee.get("department"));
        assertEquals(5000.0, (Double) employee.get("salary"), 0.0);
        assertEquals(4, employee.size());
    }

    @Test
    public void testAddDuplicateEmployeeFails() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(1, "Alice", "Developer", "IT", 5000.0));
        assertFalse(system.addEmployee(1, "Bob", "Manager", "HR", 7000.0));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alice", employee.get("name"));
        assertEquals(5000.0, (Double) employee.get("salary"), 0.0);
        assertEquals(1, system.listEmployees().size());
    }

    @Test
    public void testAddMultipleEmployees() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(1, "Alice", "Developer", "IT", 5000.0));
        assertTrue(system.addEmployee(2, "Bob", "Manager", "HR", 7000.0));
        assertTrue(system.addEmployee(3, "Carol", "Analyst", "Finance", 6000.0));

        assertEquals(3, system.listEmployees().size());
        assertEquals("Alice", ((Map<?, ?>) system.getEmployee(1)).get("name"));
        assertEquals("Bob", ((Map<?, ?>) system.getEmployee(2)).get("name"));
        assertEquals("Carol", ((Map<?, ?>) system.getEmployee(3)).get("name"));
    }

    @Test
    public void testRemoveExistingEmployee() {
        HRManagementSystem system = new HRManagementSystem();

        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        assertTrue(system.removeEmployee(1));
        assertEquals(Boolean.FALSE, system.getEmployee(1));
        assertTrue(system.listEmployees().isEmpty());
        assertFalse(system.removeEmployee(1));
    }

    @Test
    public void testRemoveMissingEmployee() {
        HRManagementSystem system = new HRManagementSystem();

        assertFalse(system.removeEmployee(999));
        assertEquals(Boolean.FALSE, system.getEmployee(999));
    }

    @Test
    public void testRemoveOneEmployeePreservesOthers() {
        HRManagementSystem system = new HRManagementSystem();

        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);
        system.addEmployee(2, "Bob", "Manager", "HR", 7000.0);

        assertTrue(system.removeEmployee(1));
        assertEquals(Boolean.FALSE, system.getEmployee(1));
        assertEquals("Bob", ((Map<?, ?>) system.getEmployee(2)).get("name"));
        assertEquals(1, system.listEmployees().size());
    }

    @Test
    public void testReAddRemovedEmployee() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(1, "Alice", "Developer", "IT", 5000.0));
        assertTrue(system.removeEmployee(1));
        assertTrue(system.addEmployee(1, "Bob", "Manager", "HR", 7000.0));

        assertEquals("Bob", ((Map<?, ?>) system.getEmployee(1)).get("name"));
    }

    @Test
    public void testUpdateSingleField() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Alicia");

        assertTrue(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alicia", employee.get("name"));
        assertEquals("Developer", employee.get("position"));
        assertEquals("IT", employee.get("department"));
        assertEquals(5000.0, (Double) employee.get("salary"), 0.0);
    }

    @Test
    public void testUpdateAllValidFields() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Bob");
        updates.put("position", "Manager");
        updates.put("department", "HR");
        updates.put("salary", 8000.0);

        assertTrue(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Bob", employee.get("name"));
        assertEquals("Manager", employee.get("position"));
        assertEquals("HR", employee.get("department"));
        assertEquals(8000.0, (Double) employee.get("salary"), 0.0);
    }

    @Test
    public void testUpdateMissingEmployeeFails() {
        HRManagementSystem system = new HRManagementSystem();

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Bob");

        assertFalse(system.updateEmployee(999, updates));
        assertEquals(Boolean.FALSE, system.getEmployee(999));
    }

    @Test
    public void testUpdateInvalidKeyFails() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("age", 30);

        assertFalse(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertFalse(employee.containsKey("age"));
        assertEquals("Alice", employee.get("name"));
    }

    @Test
    public void testInvalidKeyPreventsAllUpdates() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Bob");
        updates.put("invalid", "value");

        assertFalse(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alice", employee.get("name"));
        assertFalse(employee.containsKey("invalid"));
    }

    @Test
    public void testUpdateWithEmptyMapSucceeds() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        assertTrue(system.updateEmployee(1, new HashMap<>()));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alice", employee.get("name"));
        assertEquals(4, employee.size());
    }

    @Test
    public void testUpdateAllowsNullValues() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", null);
        updates.put("salary", null);

        assertTrue(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertNull(employee.get("name"));
        assertNull(employee.get("salary"));
        assertTrue(employee.containsKey("name"));
        assertTrue(employee.containsKey("salary"));
    }

    @Test
    public void testUpdateAllowsArbitraryValueTypes() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("salary", "high");
        updates.put("position", 123);

        assertTrue(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("high", employee.get("salary"));
        assertEquals(123, employee.get("position"));
    }

    @Test
    public void testAddEmployeeAcceptsNullFields() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(1, null, null, null, 0.0));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertNull(employee.get("name"));
        assertNull(employee.get("position"));
        assertNull(employee.get("department"));
        assertEquals(0.0, (Double) employee.get("salary"), 0.0);
    }

    @Test
    public void testAddEmployeeAcceptsNegativeSalary() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(1, "Alice", "Developer", "IT", -100.0));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals(-100.0, (Double) employee.get("salary"), 0.0);
    }

    @Test
    public void testZeroAndNegativeEmployeeIds() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(0, "Zero", "Developer", "IT", 1000.0));
        assertTrue(system.addEmployee(-1, "Negative", "Manager", "HR", 2000.0));

        assertEquals("Zero", ((Map<?, ?>) system.getEmployee(0)).get("name"));
        assertEquals("Negative", ((Map<?, ?>) system.getEmployee(-1)).get("name"));
        assertEquals(2, system.listEmployees().size());
    }

    @Test
    public void testListEmployeesIncludesEmployeeId() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(42, "Alice", "Developer", "IT", 5000.0);

        Map<Integer, Map<String, Object>> employees = system.listEmployees();

        assertEquals(1, employees.size());
        assertTrue(employees.containsKey(42));

        Map<String, Object> employee = employees.get(42);
        assertEquals(42, employee.get("employee_ID"));
        assertEquals("Alice", employee.get("name"));
        assertEquals("Developer", employee.get("position"));
        assertEquals("IT", employee.get("department"));
        assertEquals(5000.0, (Double) employee.get("salary"), 0.0);
        assertEquals(5, employee.size());
    }

    @Test
    public void testGetEmployeeDoesNotIncludeEmployeeId() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(42, "Alice", "Developer", "IT", 5000.0);

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(42);

        assertFalse(employee.containsKey("employee_ID"));
        assertEquals(4, employee.size());
    }

    @Test
    public void testListEmployeesReturnsIndependentCopies() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<Integer, Map<String, Object>> copy = system.listEmployees();
        copy.get(1).put("name", "Changed");
        copy.remove(1);

        Map<?, ?> original = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alice", original.get("name"));
        assertEquals(1, system.listEmployees().size());
    }

    @Test
    public void testGetEmployeeReturnsInternalMap() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        @SuppressWarnings("unchecked")
        Map<String, Object> employee =
                (Map<String, Object>) system.getEmployee(1);

        employee.put("name", "Changed");

        assertEquals("Changed",
                ((Map<?, ?>) system.getEmployee(1)).get("name"));
    }

    @Test
    public void testListEmployeesReflectsSubsequentUpdates() {
        HRManagementSystem system = new HRManagementSystem();
        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);

        Map<Integer, Map<String, Object>> before = system.listEmployees();

        Map<String, Object> updates = new HashMap<>();
        updates.put("salary", 7000.0);
        assertTrue(system.updateEmployee(1, updates));

        Map<Integer, Map<String, Object>> after = system.listEmployees();

        assertEquals(5000.0, (Double) before.get(1).get("salary"), 0.0);
        assertEquals(7000.0, (Double) after.get(1).get("salary"), 0.0);
    }

    @Test
    public void testUpdateOneEmployeeDoesNotAffectAnother() {
        HRManagementSystem system = new HRManagementSystem();

        system.addEmployee(1, "Alice", "Developer", "IT", 5000.0);
        system.addEmployee(2, "Bob", "Manager", "HR", 7000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("salary", 9000.0);

        assertTrue(system.updateEmployee(1, updates));

        assertEquals(9000.0,
                (Double) ((Map<?, ?>) system.getEmployee(1)).get("salary"), 0.0);
        assertEquals(7000.0,
                (Double) ((Map<?, ?>) system.getEmployee(2)).get("salary"), 0.0);
    }

    @Test
    public void testIndependentSystemInstances() {
        HRManagementSystem first = new HRManagementSystem();
        HRManagementSystem second = new HRManagementSystem();

        assertTrue(first.addEmployee(1, "Alice", "Developer", "IT", 5000.0));

        assertEquals("Alice", ((Map<?, ?>) first.getEmployee(1)).get("name"));
        assertEquals(Boolean.FALSE, second.getEmployee(1));
        assertTrue(second.listEmployees().isEmpty());
    }

    @Test
    public void testPublicEmployeesMapReflectsOperations() {
        HRManagementSystem system = new HRManagementSystem();

        assertTrue(system.addEmployee(1, "Alice", "Developer", "IT", 5000.0));
        assertTrue(system.employees.containsKey(1));

        assertTrue(system.removeEmployee(1));
        assertFalse(system.employees.containsKey(1));
    }
}
// END GeneratedTest_CE043