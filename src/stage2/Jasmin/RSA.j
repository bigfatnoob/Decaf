.class RSA
.super Object

.field protected n I

.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method private static sq(I;)I
.limit locals 1
.limit stack 100
	iload_1
	iload_1
	imul
	ireturn
.end method

.method public static powermod(I;I;)I
.limit locals 3
.limit stack 100
	iload_2
	iconst_1
	if_icmpne 	FI0
	aload_0
	getfield RSA/x I
	iload_3
	irem
	ireturn
FI0:
	iconst_1
	if_icmpne 	FI1
	iload_3
