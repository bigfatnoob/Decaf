.class Short
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static main()V
.limit locals 4
.limit stack 100
	iconst_0
	istore1
	iconst_1
	istore2
	iconst_1
	istore3
SWhile0:
	iload_1
	iconst 10
	if_icmpge 	EWhile1
	iload_2
	iload_3
	iadd
	istore 4
	iload_3
	istore_2
	iload 4
	istore_3
	iload_1
	iconst_1
	iadd
	istore_1
EWhile1:
	iload_3
	invokestatic IO/putInt(I;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

