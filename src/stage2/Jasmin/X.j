.class X
.super Y


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial Y/<init>()V
	return
.end method

.method public g()I
.limit locals 0
.limit stack 100
	iconst_1
	aload 0
	invokevirtual Y/f()I
	iadd
	ireturn
.end method

