### API Tests with REST Assured

Java (JDK) and Maven should be installed and added to System variables.

- Clone repo to local dir and open cmd.exe in it
- To get results and HTML report, run: mvn test
- To run single test-class, run: mvn test -Dtest=className
- Report is created in: target/surefire-reports/index.html

User story:

1. Go to http://zippopotam.us/us/90210
2. Verify place name
3. Verify response status code
4. Verify response content type
5. Log request and response details
6. Verify response body
7. Verify place names with parameterized test
8. Create reusable variables
9. Create data-object from JSON
10. Create JSON from data-object