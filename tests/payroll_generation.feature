Feature: Payroll Generation

  Scenario: Generate payroll for all employees
    Given employees exist in the system
    And attendance for the month is recorded
    When the payroll job runs
    Then payroll records are generated for each employee