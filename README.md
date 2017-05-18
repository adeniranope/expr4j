# expr4j


### Services
####  JSON API
Write a JSON API (over HTTP 1.1) service, which receives a basic mathematical expression and returns its result. If the expression is wrong, return an error. The expression can contain parenthesis, factorial and absolute value calculations.

A complicated example:

````json
{
 "expression": "5! + abs(6-7*7) / 9"
}
````

Which returns

````json
{
  "result": "..."
}
````

#### Web Service
Write a web service which shows a form for the expression above, and call the JSON API from this service. Present the result or the error.

#### Command line python
Write a simple python script that I can call from the command line. The argument is the expression, and the result printed to the standard output. The error also goes there, not the standard error.

#### Prerequisites
1. Install java 8
2. Install gradle, create a gradle wrapper .
3. Install a python compiler

#### Building the application
Take a clone of the source code and run

````
gradlew clean build
gradlew allJar

````
This command will create a jar file in the delivery folder of the expr-boot project. To start the application, run

````
java -jar <jar_file_full_path>

````
The application is started listening on port 9010 and bounded to the localhost

The JSON API context will be on http://localhost:8084/expr4j/evaluate
The webservice will be on http://localhost:8084/processExpression

To run the python script use the command below

````
python <directory>/py/expression.py

````

