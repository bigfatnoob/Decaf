.class Bases
.super Object


.method public <init>()V
.limit locals 1
.limit stack 100
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static displayWelcome()V
.limit locals 0
.limit stack 100
	ldc "****************************************"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "***************************************\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "Welcome to BaseConverter\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "This program takes a positive number of"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "base 2 through 32 as input with up to 8\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "digits and generates another number of"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "base 2 through 32 as output.\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "Please press any key to continue..."
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

.method public static runWelcome()V
.limit locals 0
.limit stack 100
	invokestatic Bases/displayWelcome()V
	invokestatic IO/getChar()I
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

.method public static displayMenu()V
.limit locals 0
.limit stack 100
	ldc "****************************************"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "***************************************\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "Please select from one of the following options:\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "    H = Convert from decimal to hexadecimal\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "    D = Convert from hexadecimal to decimal\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "    N = Perform another type of base conversion\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "    X = Exit program\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "> "
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

.method public static getMenuResponse()I
.limit locals 1
.limit stack 100
	iconst_1
	istore1
	invokestatic IO/getChar()I
	istore_1
	ldc "\n\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	goto trueLab7
ORLab6:
trueLab7:
	goto	FI5
Else4:
	goto trueLab11
ORLab10:
trueLab11:
	goto	FI9
Else8:
	goto trueLab15
ORLab14:
trueLab15:
	goto	FI13
Else12:
	goto trueLab19
ORLab18:
trueLab19:
	goto	FI17
Else16:
	ireturn
FI17:
FI13:
FI9:
FI5:
.end method

.method public static getBases(LBasesConversionHolder;)V
.limit locals 3
.limit stack 100
SWhile20:
	goto trueLab23
ORLab22:
trueLab23:
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "Please enter a base for the source (2-32)> \n"
	invokestatic IO/putString(Ljava/lang/String;)V
	invokestatic IO/getInt()I
	aload_0
	putfield Bases/source I
EWhile21:
	aload_1
	aload_0
	getfield Bases/source I
	invokevirtual BasesConversionHolder/setSourceBase(I;)V
SWhile24:
	goto trueLab27
ORLab26:
trueLab27:
	ldc "Please enter a base for the target (2-32)> \n"
	invokestatic IO/putString(Ljava/lang/String;)V
	invokestatic IO/getInt()I
	aload_0
	putfield Bases/target I
EWhile25:
	aload_1
	aload_0
	getfield Bases/target I
	invokevirtual BasesConversionHolder/setTargetBase()V
	return
.end method

.method public static getSourceNumber()V
.limit locals 1
.limit stack 100
	return
.end method

.method public static convertNumber()V
.limit locals 1
.limit stack 100
	return
.end method

.method public static displayNumber()V
.limit locals 1
.limit stack 100
	return
.end method

.method public static runMenu()V
.limit locals 2
.limit stack 100
	new BasesConversionHolder
	dup
	invokespecial BasesConversionHolder/<init>()V
	astore2
SWhile28:
	aload_0
	getfield Bases/response I
	iconst_0
	if_icmpeq 	EWhile29
	invokestatic Bases/displayMenu()V
	invokestatic Bases/getMenuResponse()I
	aload_0
	putfield Bases/response I
	aload_0
	getfield Bases/response I
	invokestatic IO/putInt(I;)V
	aload_0
	getfield Bases/response I
	iconst_1
	if_icmpne 	Else30
	goto	EWhile29
Else30:
	aload_0
	getfield Bases/response I
	iconst_2
	if_icmpne 	Else31
	goto	EWhile29
Else31:
	aload_0
	getfield Bases/response I
	iconst_3
	if_icmpne 	Else32
	goto	EWhile29
Else32:
	aload_0
	getfield Bases/response I
	if_icmpne 	EWhile29
	ldc "Unrecognized result; please try again...\n\n"
	invokestatic IO/putString(Ljava/lang/String;)V
EWhile29:
	return
.end method

.method public static displayGoodbye()V
.limit locals 0
.limit stack 100
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "****************************************"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "***************************************\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "Thank you for using BaseConverter\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "****************************************"
	invokestatic IO/putString(Ljava/lang/String;)V
	ldc "***************************************\n"
	invokestatic IO/putString(Ljava/lang/String;)V
	return
.end method

.method public static main()V
.limit locals 0
.limit stack 100
	invokestatic Bases/runWelcome()V
	invokestatic Bases/runMenu()V
	invokestatic Bases/displayGoodbye()V
	return
.end method

