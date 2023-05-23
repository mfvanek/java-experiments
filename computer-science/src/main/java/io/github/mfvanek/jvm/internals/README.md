# Native Memory Tracking in JVM
See https://shipilev.net/jvm/anatomy-quarks/12-native-memory-tracking/
and https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr007.html

## Run
```shell
java -Xss1M -XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics --source 17 EmptyStackFrameProgram.java
```

```shell
java -Xss1M -XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics --source 17 FullStackFrameProgram.java
```

## Inspect
```shell
ps aux | grep java
```

```
jcmd <PID> VM.native_memory
```
