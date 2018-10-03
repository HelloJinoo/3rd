grammar MiniGo;
program		: decl+	{System.out.println("201402415 Rule 0"); }; //o
decl		: var_decl {System.out.println("201402415 Rule 1-0");} //o 
			| fun_decl {System.out.println("201402415 Rule 1-1"); }; //o
var_decl	: dec_spec IDENT type_spec {System.out.println("201402415 Rule 2-0"); }   //o
			| dec_spec IDENT type_spec '=' LITERAL  {System.out.println("201402415 Rule 2-1"); }  //o
			| dec_spec IDENT '[' LITERAL ']' type_spec {System.out.println("201402415 Rule 2-2"); }; //o
dec_spec	: VAR {System.out.println("201402415 Rule 3"); }; //o
type_spec	: INT {System.out.println("201402415 Rule 4-0");} //o
			| {System.out.println("201402415 Rule 4-1"); }; //o
fun_decl	: FUNC IDENT '(' params ')' type_spec compound_stmt {System.out.println("201402415 Rule 5-0"); } //o
			| FUNC IDENT '(' params ')' '(' type_spec ',' type_spec ')' compound_stmt {System.out.println("201402415 Rule 5-1"); }; //o 
params		: param(',' param)* {System.out.println("201402415 Rule 6-0"); } //o 
			| {System.out.println("201402415 Rule 6-1"); };  //o
param		: IDENT {System.out.println("201402415 Rule 7-0"); } //o
			| IDENT type_spec	{System.out.println("201402415 Rule 7-1"); }; //o
stmt		: expr_stmt {System.out.println("201402415 Rule 8-0"); } //o
			| compound_stmt {System.out.println("201402415 Rule 8-1"); } //o 
			| if_stmt {System.out.println("201402415 Rule 8-2"); } //o
			| for_stmt {System.out.println("201402415 Rule 8-3"); } //o
			| return_stmt {System.out.println("201402415 Rule 8-4"); }; //o 
expr_stmt	: expr{System.out.println("201402415 Rule 9"); }; //o
for_stmt	: FOR loop_expr stmt{System.out.println("201402415 Rule 10-0" ); } //o
			| FOR expr stmt	{System.out.println("201402415 Rule 10-1"); };  //o
compound_stmt: '{' local_decl* stmt* '}' {System.out.println("201402415 Rule 11"); };  //o
local_decl	: dec_spec IDENT type_spec  {System.out.println("201402415 Rule 12-0");} //o
			| dec_spec IDENT type_spec '=' LITERAL {System.out.println("201402415 Rule 12-1");} //o
			| dec_spec IDENT '[' LITERAL ']' type_spec {System.out.println("201402415 Rule 12-2");}; //o
if_stmt		: IF expr stmt {System.out.println("201402415 Rule 13-0"); } //o
			| IF expr stmt ELSE stmt {System.out.println("201402415 Rule 13-1"); }; //o
return_stmt	: RETURN expr {System.out.println("201402415 Rule 14-0"); }  //o
			| RETURN expr ',' expr	{System.out.println("201402415 Rule 14-1"); } //o
			| RETURN {System.out.println("201402415 Rule 14-2"); };  //o
loop_expr   : expr ';' expr ';' expr ('++'|'--') {System.out.println("201402415 Rule 15"); }; //o
expr		: (LITERAL|IDENT) {System.out.println("201402415 Rule 16-0"); }  //o
			| '(' expr ')' {System.out.println("201402415 Rule 16-1"); }  
			| IDENT '[' expr ']' {System.out.println("201402415 Rule 16-2"); } 
			| IDENT '(' args ')' {System.out.println("201402415 Rule 16-3"); }  //o
			| FMT '.' IDENT '(' args ')' {System.out.println("201402415 Rule 16-4"); } 
			| op=('-'|'+'|'--'|'++'|'!') expr {System.out.println("201402415 Rule 16-5"); } //o
			| left=expr op=('*'|'/') right=expr {System.out.println("201402415 Rule 16-6"); }  
			| left=expr op=('%'|'+'|'-') right=expr {System.out.println("201402415 Rule 16-7"); } //o 
			| left=expr op=(EQ|NE|LE|'<'|GE|'>'|AND|OR) right=expr {System.out.println("201402415 Rule 16-8"); } //o
			| IDENT '=' expr  {System.out.println("201402415 Rule 16-9"); } //o
			| IDENT '[' expr ']' '=' expr {System.out.println("201402415 Rule 16-10"); }; 
args		: expr (',' expr) * {System.out.println("201402415 Rule 17-0"); } 
			| {System.out.println("201402415 Rule 17-1"); }; 
VAR			: 'var'   {System.out.println("201402415 Rule 18"); }; 
FUNC		: 'func'  {System.out.println("201402415 Rule 19"); }; 
FMT			: 'fmt'	  {System.out.println("201402415 Rule 20"); }; 
VOID		: 'void'  {System.out.println("201402415 Rule 21"); }; 
INT			: 'int'   {System.out.println("201402415 Rule 22"); };  
FOR			: 'for'   {System.out.println("201402415 Rule 23"); }; 
IF			: 'if'    {System.out.println("201402415 Rule 24"); };  
ELSE		: 'else'  {System.out.println("201402415 Rule 25"); }; 
RETURN		: 'return' {System.out.println("201402415 Rule 26"); }; 
OR			: 'or'    {System.out.println("201402415 Rule 27"); }; 
AND			: 'and'   {System.out.println("201402415 Rule 28"); };
LE			: '<='    {System.out.println("201402415 Rule 29"); };  
GE			: '>='    {System.out.println("201402415 Rule 30"); }; 
EQ			: '=='    {System.out.println("201402415 Rule 31"); }; 
NE			: '!='   {System.out.println("201402415 Rule 32"); } ; 

IDENT		: [a-zA-Z_]  {System.out.println("201402415 Rule 33-0"); } 
			( [a-zA-Z_] {System.out.println("201402415 Rule 33-1"); } 
			| [0-9] {System.out.println("201402415 Rule 33-2"); }  
			)*;
			
LITERAL		: DecimalConstant {System.out.println("201402415 Rule 34-0"); } //ok
			| OctalConstant {System.out.println("201402415 Rule 34-1"); } 
			| HexadecimalConstant {System.out.println("201402415 Rule 34-2"); };
			
DecimalConstant	: '0'{System.out.println("201402415 Rule 35-0"); } 
				| [1-9] [0-9]* {System.out.println("201402415 Rule 35-1"); } ;
OctalConstant	: '0' [0-7]* {System.out.println("201402415 Rule 36"); } ;
HexadecimalConstant	: '0' [xX] [0-9a-fA-F]+ {System.out.println("201402415 Rule 37"); };
WS			: (' ' 	{System.out.println("201402415 Rule 38-0"); } //ok
			| '\t' {System.out.println("201402415 Rule 38-1"); }  //ok 
			| '\r' {System.out.println("201402415 Rule 38-2"); }    //ok	
			| '\n'  {System.out.println("201402415 Rule 38-3"); }  //ok     
			)+
	-> channel(HIDDEN)	 
    ;
