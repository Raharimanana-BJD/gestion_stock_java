 ./mvnw clean spring-boot:run
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.stock:gestion_stock >-----------------------
[INFO] Building gestion_stock 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-clean-plugin/3.5.0/maven-clean-plugin-3.5.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-clean-plugin/3.5.0/maven-clean-plugin-3.5.0.pom (5.7 kB at 6.0 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-plugins/44/maven-plugins-44.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-plugins/44/maven-plugins-44.pom (8.4 kB at 101kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/44/maven-parent-44.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/44/maven-parent-44.pom (52 kB at 411 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/34/apache-34.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/apache/34/apache-34.pom (24 kB at 238 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.12.1/junit-bom-5.12.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.12.1/junit-bom-5.12.1.pom (5.6 kB at 65 kB/s)
[INFO] 
[INFO] --- clean:3.5.0:clean (default-clean) @ gestion_stock ---
[INFO] Deleting C:\Users\brayj\projet\gestion_stock\target
[INFO] 
[INFO] >>> spring-boot:4.1.0:run (default-cli) > test-compile @ gestion_stock >>>
[INFO] 
[INFO] --- resources:3.5.0:resources (default-resources) @ gestion_stock ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ gestion_stock ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 11 source files with javac [debug parameters release 21] to target\classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.365 s
[INFO] Finished at: 2026-06-15T16:30:53+05:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project gestion_stock: Fatal error compiling: error: release version 21 not supported -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
gestion_stock x 