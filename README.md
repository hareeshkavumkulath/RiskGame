# Naming Convensions

### Package Names
##### Package names are all lowercase, with consecutive words simply concatenated together.
```
com.risk.model , com.risk.controller
```
### Class File Names
##### The source file name consists of the case-sensitive name of the top-level class it contains, plus the .java extension. Class names are written in UpperCamelCase.
```
MapController.java, GameWindow.java
```
### Test Class Names
Test classes are named starting with the name of the class they are testing, and ending with Tests.
```
MapControllerTests.java
```
### Method Names
##### Method names are written in lower camel case. Method names are typically verbs or verb phrases. 
```
validateMap, checkCountriesConnected
```
### Constant Names
##### Constant names use CONSTANT_CASE: all uppercase letters, with each word separated from the next by a single underscore.
```
COMMA_JOINER
```
### Non-constant filed Names, Parameter Names and Local Variable Names
##### These names use lowerCamelCase.
```
index, fileContent
```

# Layout

##### A source file consists of, in order:

* ##### Package statement: which is not line-wrapped
* ##### Import statement: No wildcard imports and No line wrapping.
* ##### Class Declaration: Exactly one top level class declaration.

### Comments
##### Javadoc Comments: The basic format of Javadoc comment is as follows
```
/**
 * {Name of this Class}
 *  
 * {Purpose of this Class}
 *  
 * {Other notes relating to the class}
 *  
 * @author
 * @version 
 */
```

### Methods,
```
/**
* Returns an Image object that can then be painted on the screen. 
* The url argument must specify an absolute {@link URL}. The name
* argument is a specifier that is relative to the url argument. 
*
* @param url <explanation>
* @param name <explanation>
* @return <return_type> <explanation>
*/
```
### Variables,
```
For global variables, use,
    @SuppressWarnings("javadoc")
String name;
```
### Statements

##### Braces are used with if, else, for, do and while statements, even when the body is empty or contains only a single statement. One statement per line.





# References
* ### [Google Java Style Guide](http://google.github.io/styleguide/javaguide.html)
