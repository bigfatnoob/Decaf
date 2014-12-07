.class Cars
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static describe(LCar;)V
.limit locals 1
.limit stack 100
	aload_1
	aload 0
	invokevirtual Car/builder()Ljava/lang/String
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc " "
	invokestatic IO/putString(Ljava/lang/String;)V
	aload_1
	aload 0
	invokevirtual Car/model()Ljava/lang/String
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

.method public static main(Ljava/lang/String;)V
.limit locals 5
.limit stack 100
	new Escort
	dup
	invokespecial Escort/<init>()V
	astore2
	new F150
	dup
	invokespecial F150/<init>()V
	astore3
	new Caprice
	dup
	invokespecial Caprice/<init>()V
	astore 4
	new Suburban
	dup
	invokespecial Suburban/<init>()V
	astore 5
	aload_2
	invokestatic Cars/describe(LCar;)V
	aload_3
	invokestatic Cars/describe(LCar;)V
	aload 4
	invokestatic Cars/describe(LCar;)V
	aload 5
	invokestatic Cars/describe(LCar;)V
	return
.end method

