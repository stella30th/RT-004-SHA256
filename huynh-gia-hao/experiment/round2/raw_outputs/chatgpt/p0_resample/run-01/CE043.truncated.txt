
// BEGIN GeneratedTest_CE043
package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class GeneratedTest_CE043 {

    private HRManagementSystem system;

    @Before
    public void setUp() {
        system = new HRManagementSystem();
    }

    @Test
    public void testInitialEmployeeListIsEmpty() {
        assertTrue(system.listEmployees().isEmpty());
        assertFalse(system.employees.containsKey(1));
        assertEquals(Boolean.FALSE, system.getEmployee(1));
    }

    @Test
    public void testAddEmployeeSuccessfully() {
        assertTrue(system.addEmployee(
                1, "Alice", "Developer", "IT", 5000.0));

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
    public void testDuplicateEmployeeIdIsRejected() {
        assertTrue(system.addEmployee(
                1, "Alice", "Developer", "IT", 5000.0));

        assertFalse(system.addEmployee(
                1, "Bob", "Manager", "HR", 8000.0));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alice", employee.get("name"));
        assertEquals("Developer", employee.get("position"));
        assertEquals(5000.0, (Double) employee.get("salary"), 0.0);
        assertEquals(1, system.listEmployees().size());
    }

    @Test
    public void testRemoveExistingAndMissingEmployee() {
        assertTrue(system.addEmployee(
                1, "Alice", "Developer", "IT", 5000.0));
        assertTrue(system.addEmployee(
                2, "Bob", "Manager", "HR", 8000.0));

        assertTrue(system.removeEmployee(1));
        assertFalse(system.removeEmployee(1));
        assertFalse(system.removeEmployee(999));

        assertEquals(Boolean.FALSE, system.getEmployee(1));
        assertTrue(system.listEmployees().containsKey(2));
        assertEquals(1, system.listEmployees().size());
    }

    @Test
    public void testUpdateEmployeeWithValidKeys() {
        system.addEmployee(
                1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Alicia");
        updates.put("position", "Senior Developer");
        updates.put("salary", 7500.0);

        assertTrue(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alicia", employee.get("name"));
        assertEquals("Senior Developer", employee.get("position"));
        assertEquals("IT", employee.get("department"));
        assertEquals(7500.0, (Double) employee.get("salary"), 0.0);
    }

    @Test
    public void testUpdateRejectsInvalidKeysWithoutModification() {
        system.addEmployee(
                1, "Alice", "Developer", "IT", 5000.0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Changed");
        updates.put("invalidField", "Invalid");

        assertFalse(system.updateEmployee(1, updates));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alice", employee.get("name"));
        assertFalse(employee.containsKey("invalidField"));
        assertEquals(4, employee.size());
    }

    @Test
    public void testUpdateMissingEmployeeAndEmptyUpdate() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Unknown");

        assertFalse(system.updateEmployee(999, updates));
        assertEquals(Boolean.FALSE, system.getEmployee(999));

        system.addEmployee(
                1, "Alice", "Developer", "IT", 5000.0);

        assertTrue(system.updateEmployee(1, new HashMap<>()));

        Map<?, ?> employee = (Map<?, ?>) system.getEmployee(1);
        assertEquals("Alice", employee.get("name"));
        assertEquals(4, employee.size());
    }

    @Test
    public void testListEmployeesIncludesIdAndCopiesEmployeeData() {
        system.addEmployee(
                10, "Alice", "Developer", "IT", 5000.0);
        system.addEmployee(
                20, "Bob", "Manager", "HR", 8000.0);

        Map<Integer, Map<String, Object>> listed =
                system.listEmployees();

        assertEquals(2, listed.size());
        assertEquals(10, listed.get(10).get("employee_ID"));
        assertEquals(20, listed.get(20).get("employee_ID"));
        assertEquals("Alice", listed.get(10).get("name"));
        assertEquals("Bob", listed.get(20).get("name"));

        listed.get(10).put("name", "Modified");
        listed.get(10).put("salary", 100.0);
        listed.remove(20);

        Map<?, ?> original = (Map<?, ?>) system.getEmployee(10);
        assertEquals("Alice", original.get("name"));
        assertEquals(5000.0, (Double) original.get("salary"), 0.0);
        assertFalse(original.containsKey("employee_ID"));

        assertEquals(2, system.listEmployees().size());
        assertEquals("Bob",
                ((Map<?, ?>) system.getEmployee(20)).get("name"));
    }
}
// END GeneratedTest_CE043
