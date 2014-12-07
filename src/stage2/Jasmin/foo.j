.class foo
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static main(Ljava/lang/String;)V
.limit locals 4
.limit stack 100
	istore2
	istore3
	iconst 10
	istore_2
	iconst 5
	istore_3
	ldc "before: "
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "x="
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_2
	invokestatic IO/putInt(I;)V
	ldc " y="
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_3
	invokestatic IO/putInt(I;)V
	bipush 39
	invokestatic IO/putChar(C;)V
	iload_3
	iload_2
	if_icmpge 	FI0
	iload_2
	istore 4
	iload_3
	istore_2
	iload 4
	istore_3
	ldc " t="
	invokestatic IO/putString(Ljava/lang/String;)V
	iload 4
	invokestatic IO/putInt(I;)V
FI0:
	ldc "after: "
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "x="
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_2
	invokestatic IO/putInt(I;)V
	ldc " y="
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_3
	invokestatic IO/putInt(I;)V
	bipush 39
	invokestatic IO/putChar(C;)V
	return
.end method

