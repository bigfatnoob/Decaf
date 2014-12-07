.class Chevrolet
.super Car


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial Car/<init>()V
	return
.end method

.method public <init>()V
.limit locals 1
.limit stack 100
	aload 0
	ldc "Chevrolet"
	aload_0
	getfield Chevrolet/x Ljava/lang/String
	invokevirtual Chevrolet/Car(Ljava/lang/String;Ljava/lang/String;)LCar
	return
.end method

