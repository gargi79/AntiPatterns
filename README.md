# AntiPatterns
Creating Antipattern detectors and testing using Eclipse plugins


582 Class Project: CodeSmell Detection
Team members
Garhgaj Singh
David Kelly
Hunter Davis
Code smell detectors:
Blob
Shotgun
Spaghetti Code
Type Checking
Swiss Army Knife
Duplicate Code
Technology:
Eclipse: Version: 2022-09 (4.25.0)
JRE System Library: JavaSE-17
JUnit 4 or 5
CheckStyle plugin ver 10.8.0
Setup:
Right click on a jar file to add to build path.
Configure CheckStyle: Window: Preferences: CheckStyle. Add custom xml file and set to default.
checkstyle-10.8.0-all.jar should be in \582Project. This is the root dir and tied to github.
References:
Documentation: Extending CheckStyle
Run CheckStyle GUI: \582Project>java -cp checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.gui.Main
How to Build a Custom Checker:
Check the AST (abstract syntax tree)
java -jar checkstyle-10.8.0-all.jar -t Check.java
Use the CheckStyle API: import com.puppycrawl.tools.checkstyle.api.*
How to Test Files:
Run checkStyle with default xml file:

java -jar checkstyle-10.8.0-all.jar -c /sun_checks.xml

Results:
Starting audit... - [ERROR] D:\WSU\582 Testing\Project\582Project\Check.java:1: Missing package-info.java file. [JavadocPackage] - [ERROR] D:\WSU\582 Testing\Project\582Project\Check.java:1:9: Name 'CodeSmell' must match pattern '^[a-z]+(.[ ....
Audit done.
Checkstyle ends with 27 errors.
Run checkstyle with custom module(s) (Root module, Checks, etc) in configuration file: (https://checkstyle.org/cmdline.html)

Export your class project as a jar file -> CodeSmell.jar. Use your config.xml file instead of sun_checks.xml.
Execute in a cmd line: \582Project>java -classpath CodeSmell.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c config.xml Check.java
Note: Custom modules should be specified with the class' full classpath in the configuration file and the compiled class be located in the custom JAR for Checkstyle to find.
