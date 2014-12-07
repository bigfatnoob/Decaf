.class IntStack
.super Object

.field private top I
.field private max I
.field private members I

.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public <init>(I;)V
.limit locals 1
.limit stack 100
	iload_1
	aload_0
	putfield IntStack/max I
	aload_0
	putfield IntStack/top I
	aload_0
	getfield IntStack/max I
	newarray [I
	aload_0
	putfield IntStack/members I
	return
.end method

.method public top()I
.limit locals 0
.limit stack 100
	aload_0
	getfield IntStack/top I
	iconst_0
	if_icmplt 	FI0
	aload_0
	getfield IntStack/members I
	aload_0
	getfield IntStack/top I
	ireturn
FI0:
	ireturn
.end method

.method public isEmpty()Z
.limit locals 0
.limit stack 100
	aload_0
	getfield IntStack/top I
	if_icmpne 	
	zreturn
.end method

.method public pop()I
.limit locals 1
.limit stack 100
	aload 0
	invokevirtual IntStack/top()I
	istore1
	aload_0
	getfield IntStack/top I
	iconst_0
	if_icmplt 	FI1
	aload_0
	getfield IntStack/top I
	iconst_1
	isub
	aload_0
	putfield IntStack/top I
FI1:
	iload_1
	ireturn
.end method

.method public push(I;)V
.limit locals 1
.limit stack 100
	aload_0
	getfield IntStack/top I
	aload_0
	getfield IntStack/max I
	iconst_1
	isub
	if_icmpge 	FI2
	aload_0
	getfield IntStack/top I
	iconst_1
	iadd
	aload_0
	putfield IntStack/top I
	aload_0
	getfield IntStack/members I
	aload_0
	getfield IntStack/top I
	iload_1
	dup_x2
	iastore
FI2:
	return
.end method

.method public isFull()Z
.limit locals 0
.limit stack 100
	aload_0
	getfield IntStack/top I
	aload_0
	getfield IntStack/max I
	iconst_1
	isub
	if_icmplt 	
	zreturn
.end method

