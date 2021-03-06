/* Generated By:JavaCC: Do not edit this line. ParserConstants.java */
package stage1;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int BREAK = 9;
  /** RegularExpression Id. */
  int CLASS = 10;
  /** RegularExpression Id. */
  int CONTINUE = 11;
  /** RegularExpression Id. */
  int ELSE = 12;
  /** RegularExpression Id. */
  int EXTENDS = 13;
  /** RegularExpression Id. */
  int IF = 14;
  /** RegularExpression Id. */
  int NEW = 15;
  /** RegularExpression Id. */
  int PRIVATE = 16;
  /** RegularExpression Id. */
  int PROTECTED = 17;
  /** RegularExpression Id. */
  int PUBLIC = 18;
  /** RegularExpression Id. */
  int RETURN = 19;
  /** RegularExpression Id. */
  int STATIC = 20;
  /** RegularExpression Id. */
  int SUPER = 21;
  /** RegularExpression Id. */
  int THIS = 22;
  /** RegularExpression Id. */
  int WHILE = 23;
  /** RegularExpression Id. */
  int BOOLEAN = 24;
  /** RegularExpression Id. */
  int CHAR = 25;
  /** RegularExpression Id. */
  int VOID = 26;
  /** RegularExpression Id. */
  int INT = 27;
  /** RegularExpression Id. */
  int ABSTRACT = 28;
  /** RegularExpression Id. */
  int BYTE = 29;
  /** RegularExpression Id. */
  int CASE = 30;
  /** RegularExpression Id. */
  int CATCH = 31;
  /** RegularExpression Id. */
  int CONST = 32;
  /** RegularExpression Id. */
  int DEFLT = 33;
  /** RegularExpression Id. */
  int DO = 34;
  /** RegularExpression Id. */
  int DOUBLE = 35;
  /** RegularExpression Id. */
  int FINAL = 36;
  /** RegularExpression Id. */
  int FINALLY = 37;
  /** RegularExpression Id. */
  int FOR = 38;
  /** RegularExpression Id. */
  int IMPLEMENTS = 39;
  /** RegularExpression Id. */
  int IMPORT = 40;
  /** RegularExpression Id. */
  int INSTANCEOF = 41;
  /** RegularExpression Id. */
  int INTERFACE = 42;
  /** RegularExpression Id. */
  int LONG = 43;
  /** RegularExpression Id. */
  int NATIVE = 44;
  /** RegularExpression Id. */
  int GOTO = 45;
  /** RegularExpression Id. */
  int PACKAGE = 46;
  /** RegularExpression Id. */
  int SHORT = 47;
  /** RegularExpression Id. */
  int SWITCH = 48;
  /** RegularExpression Id. */
  int SYNCHRONIZED = 49;
  /** RegularExpression Id. */
  int THROW = 50;
  /** RegularExpression Id. */
  int THROWS = 51;
  /** RegularExpression Id. */
  int TRANSIENT = 52;
  /** RegularExpression Id. */
  int TRY = 53;
  /** RegularExpression Id. */
  int VOLATILE = 54;
  /** RegularExpression Id. */
  int BYVALUE = 55;
  /** RegularExpression Id. */
  int CAST = 56;
  /** RegularExpression Id. */
  int FUTURE = 57;
  /** RegularExpression Id. */
  int GENERIC = 58;
  /** RegularExpression Id. */
  int INNER = 59;
  /** RegularExpression Id. */
  int NONE = 60;
  /** RegularExpression Id. */
  int OPERATOR = 61;
  /** RegularExpression Id. */
  int OUTER = 62;
  /** RegularExpression Id. */
  int REST = 63;
  /** RegularExpression Id. */
  int VAR = 64;
  /** RegularExpression Id. */
  int INTEGER = 65;
  /** RegularExpression Id. */
  int TRUE = 66;
  /** RegularExpression Id. */
  int FALSE = 67;
  /** RegularExpression Id. */
  int CHARLITERAL = 68;
  /** RegularExpression Id. */
  int STRINGLITERAL = 69;
  /** RegularExpression Id. */
  int NULL = 70;
  /** RegularExpression Id. */
  int LP = 71;
  /** RegularExpression Id. */
  int RP = 72;
  /** RegularExpression Id. */
  int LCB = 73;
  /** RegularExpression Id. */
  int RCB = 74;
  /** RegularExpression Id. */
  int LSB = 75;
  /** RegularExpression Id. */
  int RSB = 76;
  /** RegularExpression Id. */
  int SCOLON = 77;
  /** RegularExpression Id. */
  int COMMA = 78;
  /** RegularExpression Id. */
  int DOT = 79;
  /** RegularExpression Id. */
  int ASSIGN = 80;
  /** RegularExpression Id. */
  int GREATER = 81;
  /** RegularExpression Id. */
  int LESSER = 82;
  /** RegularExpression Id. */
  int NOT = 83;
  /** RegularExpression Id. */
  int EQUAL = 84;
  /** RegularExpression Id. */
  int GREATEREQ = 85;
  /** RegularExpression Id. */
  int LESSEREQ = 86;
  /** RegularExpression Id. */
  int NOTEQUAL = 87;
  /** RegularExpression Id. */
  int PLUS = 88;
  /** RegularExpression Id. */
  int MINUS = 89;
  /** RegularExpression Id. */
  int MULTIPLY = 90;
  /** RegularExpression Id. */
  int DIVIDE = 91;
  /** RegularExpression Id. */
  int AND = 92;
  /** RegularExpression Id. */
  int OR = 93;
  /** RegularExpression Id. */
  int MODULO = 94;
  /** RegularExpression Id. */
  int Let = 95;
  /** RegularExpression Id. */
  int Dig = 96;
  /** RegularExpression Id. */
  int ID = 97;
  /** RegularExpression Id. */
  int EMPTY = 98;
  /** RegularExpression Id. */
  int NON_EMPTY = 99;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int CMNT_BODY = 1;
  /** Lexical state. */
  int MATCH_NON_EMPTY = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "<token of kind 5>",
    "\"/*\"",
    "\"*/\"",
    "<token of kind 8>",
    "\"break\"",
    "\"class\"",
    "\"continue\"",
    "\"else\"",
    "\"extends\"",
    "\"if\"",
    "\"new\"",
    "\"private\"",
    "\"protected\"",
    "\"public\"",
    "\"return\"",
    "\"static\"",
    "\"super\"",
    "\"this\"",
    "\"while\"",
    "\"boolean\"",
    "\"char\"",
    "\"void\"",
    "\"int\"",
    "\"abstract\"",
    "\"byte\"",
    "\"case\"",
    "\"catch\"",
    "\"const\"",
    "\"default\"",
    "\"do\"",
    "\"double\"",
    "\"final\"",
    "\"finally\"",
    "\"for\"",
    "\"implements\"",
    "\"import\"",
    "\"instanceof\"",
    "\"interface\"",
    "\"long\"",
    "\"native\"",
    "\"goto\"",
    "\"package\"",
    "\"short\"",
    "\"switch\"",
    "\"synchronized\"",
    "\"throw\"",
    "\"throws\"",
    "\"transient\"",
    "\"try\"",
    "\"volatile\"",
    "\"byvalue\"",
    "\"cast\"",
    "\"future\"",
    "\"generic\"",
    "\"inner\"",
    "\"none\"",
    "\"operator\"",
    "\"outer\"",
    "\"rest\"",
    "\"var\"",
    "<INTEGER>",
    "\"true\"",
    "\"false\"",
    "<CHARLITERAL>",
    "<STRINGLITERAL>",
    "\"null\"",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\",\"",
    "\".\"",
    "\"=\"",
    "\">\"",
    "\"<\"",
    "\"!\"",
    "\"==\"",
    "\">=\"",
    "\"<=\"",
    "\"!=\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"&&\"",
    "\"||\"",
    "\"%\"",
    "<Let>",
    "<Dig>",
    "<ID>",
    "\"\"",
    "<NON_EMPTY>",
  };

}
