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
