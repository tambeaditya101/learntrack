# JVM Basics – LearnTrack

---

## What is JDK, JRE, and JVM?

**JDK (Java Development Kit)**  
The JDK includes the JRE plus development tools such as the Java compiler (javac),
debugger, and other utilities. Developers need the JDK to write and compile Java code.

**JRE (Java Runtime Environment)**  
The JRE includes the JVM along with core libraries required to run Java applications.
It is used when we only want to execute Java programs, not develop them.

**JVM (Java Virtual Machine)**  
The JVM is responsible for running Java programs. It executes Java bytecode and
handles memory management, garbage collection, and platform independence.

In short:
- JVM runs the program
- JRE provides runtime support
- JDK provides tools for development

---

## What is Bytecode?

Java source code (.java files) is compiled by the Java compiler into an intermediate
format called **bytecode** (.class files).

Bytecode is not specific to any operating system. Instead of running directly on the
hardware, bytecode is executed by the JVM. This allows Java programs to run on any
platform that has a compatible JVM installed.

---

## What Does “Write Once, Run Anywhere” Mean?

The phrase “Write Once, Run Anywhere” means that Java programs can be written once
and executed on multiple platforms without modification.

This is possible because Java code is compiled into bytecode, and the JVM handles
platform-specific details. As long as a system has a JVM, the same bytecode can run
on Windows, macOS, or Linux without changing the source code.