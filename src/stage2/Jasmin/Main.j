.class Main
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static Append(LNode;LNode;)LNode
.limit locals 2
.limit stack 100
	aload_1
	aconst_null
	if_icmpne 	FI0
	aload_2
	areturn
FI0:
	aload_1
	getField Node/next LNode
	aload_2
	invokestatic Main/Append(LNode;LNode;)LNode
	astore_1
	aload_1
	areturn
.end method

.method public static split3Ways(LNode;I;)LMultipleNodes
.limit locals 5
.limit stack 100
	astore3
	aload_1
	aconst_null
	if_icmpne 	FI1
	new MultipleNodes
	dup
	invokespecial MultipleNodes/<init>(LNode;LNode;LNode;)V
	areturn
FI1:
	astore 4
	astore 5
	aload_1
	getField Node/next LNode
	astore 4
	aload_1
	astore 5
	aconst_null
	astore 5
	aload 4
	iload_2
	invokestatic Main/split3Ways(LNode;I;)LMultipleNodes
	astore_3
	aload 5
	getField Node/data I
	iload_2
	if_icmpne 	Else2
	goto	FI3
Else2:
	aload 5
	getField Node/data I
	iload_2
	if_icmpge 	Else4
	goto	FI5
Else4:
	aload_3
	getField MultipleNodes/high LNode
	aload 5
	invokestatic Main/Append(LNode;LNode;)LNode
	astore_3
FI5:
FI3:
	aload_3
	areturn
.end method

.method public static qsort(LNode;)LNode
.limit locals 2
.limit stack 100
	astore2
	aload_1
	aconst_null
	if_icmpne 	FI6
	aconst_null
	areturn
FI6:
	aload_1
	aload_1
	getField Node/data I
	invokestatic Main/split3Ways(LNode;I;)LMultipleNodes
	astore_2
	aload_2
	getField MultipleNodes/low LNode
	invokestatic Main/qsort(LNode;)LNode
	astore_2
	aload_2
	getField MultipleNodes/high LNode
	invokestatic Main/qsort(LNode;)LNode
	astore_2
	aload_2
	getField MultipleNodes/low LNode
	aload_2
	getField MultipleNodes/equal LNode
	aload_2
	getField MultipleNodes/high LNode
	invokestatic Main/Append(LNode;LNode;)LNode
	invokestatic Main/Append(LNode;LNode;)LNode
	areturn
.end method

.method public static dump()V
.limit locals 1
.limit stack 100
	ldc "The list contains:\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	aload_0
	getfield Main/n LNode
	aconst_null
	if_icmpne 	Else7
	goto	FI8
Else7:
	ldc "["
	invokestatic IO/putString(Ljava/lang/String;)V
FI8:
SWhile9:
	bipush 1
