package backend;

package backend;

import java.util.LinkedList;
import java.util.Queue;

public class PayrollQueueProcessor {
    private Queue<Integer> employeeQueue = new LinkedList<>();
    private PayrollDAO payrollDAO;

    // Constructor to inject PayrollDAO dependency
    public PayrollQueueProcessor(PayrollDAO payrollDAO) {
        this.payrollDAO = payrollDAO;
    }

    // Add an employee to the payroll processing queue
    public void addEmployeeToProcess(int employeeId) {
        employeeQueue.offer(employeeId);
    }

    // Process all employees in the queue
    public void processPayrollBatch() {
        while (!employeeQueue.isEmpty()) {
            int employeeId = employeeQueue.poll();
            System.out.println("Processing payroll for employee " + employeeId);
            // Call payrollDAO to compute payroll for employeeId
            payrollDAO.processPayroll(employeeId);
        }
    }
}
