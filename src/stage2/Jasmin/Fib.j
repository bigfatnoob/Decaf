.class Fib
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static fib(I;)I
.limit locals 1
.limit stack 100
	iload_1
	iconst_2
	if_icmpgt 	Else0
	goto	FI1
Else0:
	iload_1
	iconst_1
	isub
	invokestatic Fib/fib(I;)I
	iload_1
	iconst_2
	isub
	invokestatic Fib/fib(I;)I
	iadd
	ireturn
FI1:
.end method

.method public static printFibs()V
.limit locals 2
.limit stack 100
	iconst_1
	istore2
SWhile2:
	iload_2
	aload_0
	getfield Fib/n I
	if_icmpgt 	EWhile3
	ldc "fib("
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_2
	invokestatic IO/putInt(I;)V
	ldc ") = "
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_2
	invokestatic Fib/fib(I;)I
	invokestatic IO/putInt(I;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_2
	iconst_1
	iadd
	istore_2
EWhile3:
	return
.end method

.method public static main(Ljava/lang/String;)V
.limit locals 1
.limit stack 100
	iconst 20
	invokestatic Fib/printFibs()V
	return
.end method

