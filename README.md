Logical Expression Evaluator
============================
Simple Java application to evaluate java logical formulas, using javaluator.

Description
-----------
Logical expressions formulas can contains (and, or, &&, ||, !, ==, !=, >=, <=, <, >).
Formulas could contains functions as predifined (is_empty, regexMatch).

Example
-------
input `((X == \"T\")) or (((is_empty(Y) && (!(Z <= 10)))))`
Output `((X == T)=false or (false && ((Z <= 10)=false ! )=true)=false)=false`


Build Steps
-----------
 * build `pom.xml` to install maven dependencies.
 * Run application.
