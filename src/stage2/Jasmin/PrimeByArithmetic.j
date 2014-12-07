.class PrimeByArithmetic
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method private static printPrime(I;I;)V
.limit locals 2
.limit stack 100
	ldc "Prime["
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_1
	invokestatic IO/putInt(I;)V
	ldc "] = "
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_2
	invokestatic IO/putInt(I;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

.method public static main(Ljava/lang/String;)V
.limit locals 8
.limit stack 100
	iconst 1300000
	istore2
	iload_2
	newarray [I
	istore3
	iload_2
	newarray [I
	istore 4
	istore 5
	istore 6
	iload_3
	iconst_0
	iconst_2
	dup_x2
	iastore
	iload 4
	iconst_0
	iload_3
	iconst_0
	dup_x2
	iastore
	iconst_1
	istore 5
	iconst_2
	istore 6
	iconst_0
	iload_3
	iconst_0
	invokestatic PrimeByArithmetic/printPrime(I;I;)V
SWhile0:
	iload 5
	iload_2
	if_icmpge 	EWhile1
	bipush 1
	zstore 7
	iload 6
	iconst_1
	iadd
	istore 6
	iconst_0
	istore 8
SWhile2:
	iload 8
	iload 5
	if_icmpge 	EWhile1
	iload 4
	iload 8
	iload 4
	iload 8
	iconst_1
	isub
	dup_x2
	iastore
	iload 4
	iload 8
	iconst_0
	if_icmpne 	EWhile1
	iload 4
	iload 8
	iload_3
	iload 8
	dup_x2
	iastore
	bipush 0
	zstore 7
	iload 8
	iconst_1
	iadd
	istore 8
	zload 7
	iload 5
	iload 6
	invokestatic PrimeByArithmetic/printPrime(I;I;)V
	iload_3
	iload 5
	iload 6
	dup_x2
	iastore
	iload 4
	iload 5
	iload 6
	dup_x2
	iastore
	iload 5
	iconst_1
	iadd
	istore 5
EWhile1:
	return
.end method

