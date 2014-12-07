.class MultipleNodes
.super Object

.field public high Node
.field public equal Node
.field public low Node

.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public <init>(LNode;LNode;LNode;)V
.limit locals 3
.limit stack 100
	aload_1
	aload_0
	putfield MultipleNodes/high LNode
	aload_2
	aload_0
	putfield MultipleNodes/equal LNode
	aload_3
	aload_0
	putfield MultipleNodes/low LNode
	return
.end method

