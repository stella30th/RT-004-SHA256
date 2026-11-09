// BEGIN GeneratedTest_CE043
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_CE043 {

    @Test public void addingAndListingEmployees() {
        HRManagementSystem h = new HRManagementSystem();
        assertTrue(h.listEmployees().isEmpty());
        assertTrue(h.addEmployee(7, "Ann", "Dev", "IT", 500.0));
        assertFalse(h.addEmployee(7, "Bob", "Lead", "HR", 700.0));
        Map<?, ?> e = (Map<?, ?>) h.getEmployee(7);
        assertEquals("Ann", e.get("name"));
        assertEquals("Dev", e.get("position"));
        assertEquals("IT", e.get("department"));
        assertEquals(500.0, (Double) e.get("salary"), 0.0);
        assertEquals(7, h.listEmployees().get(7).get("employee_ID"));
    }
    @Test public void missingAndRemovedEmployeesReturnFalse() {
        HRManagementSystem h = new HRManagementSystem();
        assertEquals(Boolean.FALSE, h.getEmployee(4));
        assertFalse(h.removeEmployee(4));
        assertFalse(h.updateEmployee(4, new HashMap<String, Object>()));
        h.addEmployee(4, "A", "B", "C", 0);
        assertTrue(h.removeEmployee(4));
        assertEquals(Boolean.FALSE, h.getEmployee(4));
        assertFalse(h.removeEmployee(4));
    }
    @Test public void validPartialAndFullUpdates() {
        HRManagementSystem h = new HRManagementSystem();
        h.addEmployee(1, "A", "Dev", "IT", 100.0);
        Map<String, Object> patch = new HashMap<>();
        patch.put("salary", 200.0);
        assertTrue(h.updateEmployee(1, patch));
        assertEquals("A", ((Map<?, ?>) h.getEmployee(1)).get("name"));
        assertEquals(200.0, (Double) ((Map<?, ?>) h.getEmployee(1)).get("salary"), 0.0);
        patch.put("name", "Z");
        patch.put("position", "Lead");
        patch.put("department", "HR");
        assertTrue(h.updateEmployee(1, patch));
        assertEquals("Z", ((Map<?, ?>) h.getEmployee(1)).get("name"));
        assertEquals("HR", ((Map<?, ?>) h.getEmployee(1)).get("department"));
    }
    @Test public void invalidUpdateIsAtomic() {
        HRManagementSystem h = new HRManagementSystem();
        h.addEmployee(1, "A", "Dev", "IT", 100.0);
        Map<String, Object> patch = new HashMap<>();
        patch.put("name", "B");
        patch.put("other", 12);
        assertFalse(h.updateEmployee(1, patch));
        assertEquals("A", ((Map<?, ?>) h.getEmployee(1)).get("name"));
        assertFalse(((Map<?, ?>) h.getEmployee(1)).containsKey("other"));
    }
    @Test public void listReturnsDeepCopiedMaps() {
        HRManagementSystem h = new HRManagementSystem();
        h.addEmployee(1, "A", "Dev", "IT", 100.0);
        Map<Integer, Map<String, Object>> listing = h.listEmployees();
        listing.get(1).put("name", "changed");
        listing.remove(1);
        assertEquals("A", ((Map<?, ?>) h.getEmployee(1)).get("name"));
        assertEquals(1, h.listEmployees().size());
    }
    @Test public void updateCanSetNullValue() {
        HRManagementSystem h = new HRManagementSystem();
        h.addEmployee(0, null, null, null, -5.0);
        Map<String, Object> patch = new HashMap<>();
        patch.put("salary", null);
        assertTrue(h.updateEmployee(0, patch));
        assertNull(((Map<?, ?>) h.getEmployee(0)).get("salary"));
    }

    @Test public void getEmployeeReturnsMutableBackingMap() { HRManagementSystem h = new HRManagementSystem(); h.addEmployee(1,"A","B","C",2); ((Map<String,Object>)h.getEmployee(1)).put("name","Z"); assertEquals("Z",((Map<?,?>)h.getEmployee(1)).get("name")); }
}
// END GeneratedTest_CE043
