## String 字符串拼接问题，到底什么时候会走 StringBuilder？

#### 1. 走StringBuilder

```java
public static void main(String[] args) {
        String a = "111";
        String b = "111";
        String c = b + "";
        System.out.println(a == c);//false
    }


E:\studyCode\girl>javap -c E:\studyCode\girl\target\classes\com\xiejiadao\girl\StringTest\StringConcatenationTest.class
Compiled from "StringConcatenationTest.java"
public class com.xiejiadao.girl.StringTest.StringConcatenationTest {
  public com.xiejiadao.girl.StringTest.StringConcatenationTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // String 111
       2: astore_1
       3: ldc           #2                  // String 111
       5: astore_2
       6: new           #3                  // class java/lang/StringBuilder
       9: dup
      10: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
      13: aload_2
      14: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      17: ldc           #6                  // String
      19: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      22: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      25: astore_3
      26: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
      29: aload_1
      30: aload_3
      31: if_acmpne     38
      34: iconst_1
      35: goto          39
      38: iconst_0
      39: invokevirtual #9                  // Method java/io/PrintStream.println:(Z)V
      42: return
}
```

解释下： 通过变量和字符串拼接，**java是需要先到内存找变量对应的值**，才能进行完成字符串拼接的工作，这种方式java编译器没法优化，只能走 `StringBuilder`进行拼接字符串，然后调用toString方法，当然返回的结果和常量池中的 `111`这个字符串的内存地址是**不一样**的，因此结果为false。 



#### 2. java编译器自动优化

```java
public static void main(String[] args) {
        String a = "111";
        String c = "111" + "";
        System.out.println(a == c);//true
    }


Compiled from "StringConcatenationTest.java"
public class com.xiejiadao.girl.StringTest.StringConcatenationTest {
  public com.xiejiadao.girl.StringTest.StringConcatenationTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // String 111
       2: astore_1
       3: ldc           #2                  // String 111
       5: astore_2
       6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
       9: aload_1
      10: aload_2
      11: if_acmpne     18
      14: iconst_1
      15: goto          19
      18: iconst_0
      19: invokevirtual #4                  // Method java/io/PrintStream.println:(Z)V
      22: return
}

```

**解释： 直接在表达式里写值，java不用根据变量去内存里找对应的值，可以在编译的时候直接对这个表达式进行优化，优化后的表达式从 `"111"+""` 直接变成了 `"111"` ，两个String类型的变量都指向了常量池的111字符串，因此结果为true;** 



##### ps: JVM字节码指令

