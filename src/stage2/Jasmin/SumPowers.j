.class SumPowers
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static pow(I;I;)I
.limit locals 4
.limit stack 100
	iconst_1
	istore3
	iconst_1
	istore 4
SWhile0:
	iload_3
	iload_2
	if_icmpgt 	EWhile1
	iload 4
	iload_1
	imul
	istore 4
	iload_3
	iconst_1
	iadd
	istore_3
EWhile1:
	iload 4
	ireturn
.end method

.method public static sumpowers(I;)I
.limit locals 4
.limit stack 100
	iconst_0
	istore 4
SWhile2:
	aload_0
	getfield SumPowers/i I
	aload_0
	getfield SumPowers/n I
	if_icmpgt 	EWhile3
	iload 4
	aload_0
	getfield SumPowers/i I
	iload_2
	invokestatic SumPowers/pow(I;I;)I
	iadd
	istore 4
	aload_0
	getfield SumPowers/i I
	iconst_1
	iadd
	aload_0
	putfield SumPowers/i I
EWhile3:
	iload 4
	ireturn
.end method

.method public static main(Ljava/lang/String;)V
.limit locals 4
.limit stack 100
	ldc "This program computes the sum for\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "   i = 1 to N of i to the K-th power\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	iconst_0
	istore2
	iconst_0
	istore3
SWhile4:
	bipush 1
	ldc "\nEnter the value of N:"
	invokestatic IO/putString(Ljava/lang/String;)V
	invokestatic IO/getInt()I
	istore_2
	iload_2
	iconst_1
	if_icmpge 	EWhile5
	ldc "N must be positive\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	goto	SWhile4
	ldc "\nEnter the value of K:"
	invokestatic IO/putString(Ljava/lang/String;)V
	invokestatic IO/getInt()I
	istore_3
	iload_3
	iconst_0
	if_icmpge 	EWhile5
	ldc "K must be non-negative\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	goto	SWhile4
	goto	EWhile5
EWhile5:
	ldc "Computing the sum for i = 1 to "
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_2
	invokestatic IO/putInt(I;)V
	ldc " of i to the "
	invokestatic IO/putString(Ljava/lang/String;)V
	iload_3
	invokestatic IO/putInt(I;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "The result is: "
	invokestatic IO/putString(Ljava/lang/String;)V
	aload_0
	getfield SumPowers/x I
	invokestatic IO/putInt(I;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

