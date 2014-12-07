.class Node
.super Object

.field public data I
.field public next Node

.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public <init>(I;LNode;)V
.limit locals 2
.limit stack 100
	iload_1
	aload_0
	putfield Node/data I
	aload_2
	aload_0
	putfield Node/next LNode
	return
.end method

