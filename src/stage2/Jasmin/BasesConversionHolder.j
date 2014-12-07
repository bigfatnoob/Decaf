.class BasesConversionHolder
.super Object

.field private _sourceBase I
.field private _targetBase I
.field private _source [C
.field private _target [C

.method public <init>()V
.limit locals 0
.limit stack 100
	iconst_2
	aload_0
	putfield BasesConversionHolder/_sourceBase I
	iconst_2
	aload_0
	putfield BasesConversionHolder/_targetBase I
	iconst 65
	newarray [C
	aload_0
	putfield BasesConversionHolder/_source C
	iconst 65
	newarray [C
	aload_0
	putfield BasesConversionHolder/_target C
	return
.end method

.method public getSourceBase()I
.limit locals 0
.limit stack 100
	aload_0
	getfield BasesConversionHolder/_sourceBase I
	ireturn
.end method

.method public getTargetBase()I
.limit locals 0
.limit stack 100
	aload_0
	getfield BasesConversionHolder/_targetBase I
	ireturn
.end method

.method public getSourceString()[C
.limit locals 0
.limit stack 100
	aload_0
	getfield BasesConversionHolder/_source C
	creturn
.end method

.method public getTargetString()[C
.limit locals 0
.limit stack 100
	aload_0
	getfield BasesConversionHolder/_target C
	creturn
.end method

.method public setSourceBase(I;)V
.limit locals 1
.limit stack 100
	iload_1
	aload_0
	putfield BasesConversionHolder/_sourceBase I
	return
.end method

.method public setTargetBase()V
.limit locals 1
.limit stack 100
	aload_0
	getfield BasesConversionHolder/base I
	aload_0
	putfield BasesConversionHolder/_targetBase I
	return
.end method

.method public setSourceString(I;)V
.limit locals 2
.limit stack 100
	iconst_0
	istore2
SWhile0:
	iload_2
	iconst 64
	if_icmpge 	EWhile1
	iload_1
	iload_2
	iconst_0
	if_icmpeq 	EWhile1
	aload_0
	getfield BasesConversionHolder/_source C
	iload_2
	iload_1
	iload_2
	dup_x2
	iastore
	iload_2
	iconst_1
	iadd
	istore_2
EWhile1:
	iload_1
	iload_2
	iconst_0
	dup_x2
	iastore
	return
.end method

.method public setTargetString(I;)V
.limit locals 2
.limit stack 100
SWhile2:
	aload_0
	getfield BasesConversionHolder/i I
	iconst 64
	if_icmpge 	EWhile3
	iload_1
	aload_0
	getfield BasesConversionHolder/i I
	iconst_0
	if_icmpeq 	EWhile3
	aload_0
	getfield BasesConversionHolder/_target C
	aload_0
	getfield BasesConversionHolder/i I
	iload_1
	aload_0
	getfield BasesConversionHolder/i I
	dup_x2
	iastore
	aload_0
	getfield BasesConversionHolder/i I
	iconst_1
	iadd
	aload_0
	putfield BasesConversionHolder/i I
EWhile3:
	iload_1
	aload_0
	getfield BasesConversionHolder/i I
	iconst_0
	dup_x2
	iastore
	return
.end method

