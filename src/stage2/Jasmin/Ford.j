.class Ford
.super Car


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial Car/<init>()V
	return
.end method

.method public <init>(Ljava/lang/String;)V
.limit locals 1
.limit stack 100
	aload 0
	ldc "Ford"
	aload_1
	invokevirtual Ford/Car(Ljava/lang/String;Ljava/lang/String;)LCar
	return
.end method

