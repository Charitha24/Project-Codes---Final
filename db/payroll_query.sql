SELECT e.id, e.name, p.salary, p.payDate
FROM Employee e
JOIN Payroll p ON e.id = p.employeeId;