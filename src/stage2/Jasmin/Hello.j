.class Hello
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static main(Ljava/lang/String;)V
.limit locals 1
.limit stack 100
	ldc "hello world!\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

