# Java Environment Setup – LearnTrack

---

## Java Installation
The Java Development Kit (JDK) was installed from the official OpenJDK distribution.
After installation, the JAVA_HOME environment variable was configured, and the JDK
bin directory was added to the system PATH.

## JDK Version Used
- JDK Version: Java 17 (LTS)
- Vendor: OpenJDK
- Operating System: macOS

Java was verified using the following commands:

```bash
java -version
javac -version
```


## Hello World Program Verification

To verify that Java was installed correctly, a simple "Hello World" program was created
and executed.

**Sample Code**
```bash
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

**Compilation Command**
```bash
javac HelloWorld.java
```

**Execution Command**
```bash
java HelloWorld
```

**Output**
```bash
Hello, World!
```

This confirmed that the Java compiler and runtime were working correctly.