.class Legal8Program
.super Object

.field public input I

.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public main()I
.limit locals 1
.limit stack 100
	istore1
	aload_0
	getfield Legal8Program/input I
	iconst_0
	if_icmpne 	FI0
	iconst 10
	aload_0
	putfield Legal8Program/input I
FI0:
	aload_0
	getfield Legal8Program/input I
	iconst_1
	if_icmpne 	FI1
	iconst_1
	ireturn
FI1:
	aload_0
	getfield Legal8Program/input I
	iconst_2
	if_icmpne 	FI2
	iconst_1
	ireturn
FI2:
	aload_0
	getfield Legal8Program/input I
	iconst_1
	isub
	aload_0
	putfield Legal8Program/input I
	aload 0
	invokevirtual Legal8Program/main()I
	istore_1
	aload_0
	getfield Legal8Program/input I
	iconst_1
	iadd
	aload_0
	putfield Legal8Program/input I
	aload_0
	getfield Legal8Program/input I
	iconst_2
	isub
	aload_0
	putfield Legal8Program/input I
	iload_1
	aload 0
	invokevirtual Legal8Program/main()I
	iadd
	istore_1
	aload_0
	getfield Legal8Program/input I
	iconst_2
	iadd
	aload_0
	putfield Legal8Program/input I
	aload_0
	getfield Legal8Program/input I
	iconst 10
	if_icmpne 	FI3
