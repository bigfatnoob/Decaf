.class Reverser
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static readCharList()LCharNode
.limit locals 2
.limit stack 100
	aconst_null
	astore1
	invokestatic IO/getChar()I
	aload_0
	putfield Reverser/c C
SWhile1:
	aload_0
	getfield Reverser/c C
	if_icmpeq 	EWhile2
	new CharNode
	dup
	invokespecial CharNode/<init>(C;LCharNode;)V
	astore_1
	invokestatic IO/getChar()I
	aload_0
	putfield Reverser/c C
EWhile2:
	aload_1
	areturn
.end method

.method public static printCharList(LCharNode;)V
.limit locals 1
.limit stack 100
SWhile3:
	aload_1
	aconst_null
	if_icmpeq 	EWhile4
	aload_1
	aload 0
	invokevirtual CharNode/value()C
	invokestatic IO/putChar(C;)V
	aload_1
	aload 0
	invokevirtual CharNode/next()LCharNode
	astore_1
EWhile4:
	return
.end method

.method public static main(Ljava/lang/String;)V
.limit locals 2
.limit stack 100
	aload_0
	getfield Reverser/l LCharNode
	invokestatic Reverser/printCharList(LCharNode;)V
	return
.end method

