.class Car
.super Object

.field private builder java/lang/String
.field private model java/lang/String

.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public <init>(Ljava/lang/String;Ljava/lang/String;)V
.limit locals 2
.limit stack 100
	aload_1
	aload_0
	putfield Car/builder Ljava/lang/String
	aload_2
	aload_0
	putfield Car/model Ljava/lang/String
	return
.end method

.method public builder()Ljava/lang/String
.limit locals 0
.limit stack 100
	aload_0
	getfield Car/builder Ljava/lang/String
	areturn
.end method

.method public model()Ljava/lang/String
.limit locals 0
.limit stack 100
	aload_0
	getfield Car/model Ljava/lang/String
	areturn
.end method

