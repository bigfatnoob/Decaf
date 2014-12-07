.class CharNode
.super Object

.field private value C
.field private next CharNode

.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public <init>(C;LCharNode;)V
.limit locals 2
.limit stack 100
	cload_1
	aload_0
	putfield CharNode/value C
	aload_2
	aload_0
	putfield CharNode/next LCharNode
	return
.end method

.method public value()C
.limit locals 0
.limit stack 100
	aload_0
	getfield CharNode/value C
	creturn
.end method

.method public next()LCharNode
.limit locals 0
.limit stack 100
	aload_0
	getfield CharNode/next LCharNode
	areturn
.end method

.method public isNext()Z
.limit locals 0
.limit stack 100
	aload_0
	getfield CharNode/next LCharNode
	aconst_null
	if_icmpeq 	
	areturn
.end method

.method public length()I
.limit locals 0
.limit stack 100
	iconst_1
	ireturn
FI0:
	iconst_1
	aload 0
	invokevirtual CharNode/next()LCharNode
	iadd
	ireturn
.end method

