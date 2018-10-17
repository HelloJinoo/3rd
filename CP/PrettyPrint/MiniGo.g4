grammar MiniGo;
program		: decl+	; //o
decl		: var_decl  //o
			| fun_decl ; //o
var_decl	: dec_spec IDENT type_spec  //o
			| dec_spec IDENT type_spec '=' LITERAL   //o
			| dec_spec IDENT '[' LITERAL ']' type_spec; //o
dec_spec	: VAR ;  //o
type_spec	: INT  //o
			| ; //o
fun_decl	: FUNC IDENT '(' params ')' type_spec compound_stmt //o
			| FUNC IDENT '(' params ')' '(' type_spec ',' type_spec ')' compound_stmt; //o
params		: param(',' param)* //o
			| ; //o
param		: IDENT //o
			| IDENT type_spec; //o
stmt		: expr_stmt //o
			| compound_stmt  //o
			| if_stmt //o
			| for_stmt //o
			| return_stmt ; //o 
expr_stmt	: expr; //o
for_stmt	: FOR loop_expr stmt //o
			| FOR expr stmt	;  //o
compound_stmt: '{' local_decl* stmt* '}' ; //o
local_decl	: dec_spec IDENT type_spec  //o
			| dec_spec IDENT type_spec '=' LITERAL //o
			| dec_spec IDENT '[' LITERAL ']' type_spec; //o
if_stmt		: IF expr stmt  //o
			| IF expr stmt ELSE stmt ; //o
return_stmt	: RETURN expr //o
			| RETURN expr ',' expr //o 
			| RETURN ;  //o
loop_expr   : expr ';' expr ';' expr ('++'|'--') ; //o
expr		: (LITERAL|IDENT) //o
			| '(' expr ')'  //o
			| IDENT '[' expr ']' //o
			| IDENT '(' args ')'  //o 
			| FMT '.' IDENT '(' args ')'//o
			| op=('-'|'+'|'--'|'++'|'!') expr //o
			| left=expr op=('*'|'/') right=expr  //o
			| left=expr op=('%'|'+'|'-') right=expr //o
			| left=expr op=(EQ|NE|LE|'<'|GE|'>'|AND|OR) right=expr //o
			| IDENT '=' expr  //o
			| IDENT '[' expr ']' '=' expr ; //o
args		: expr (',' expr) * //o  
			| ; //o
VAR			: 'var'   ; 
FUNC		: 'func'  ; 
FMT			: 'fmt'	  ; 
VOID		: 'void'  ; 
INT			: 'int'   ;  
FOR			: 'for'   ; 
IF			: 'if'    ;  
ELSE		: 'else'  ; 
RETURN		: 'return'; 
OR			: 'or'    ; 
AND			: 'and'   ;
LE			: '<='    ;  
GE			: '>='    ; 
EQ			: '=='    ; 
NE			: '!='   ; 

IDENT		: [a-zA-Z_]   
			( [a-zA-Z_]  
			| [0-9]   
			)*;
			
LITERAL		: DecimalConstant  
			| OctalConstant 
			| HexadecimalConstant;
			
DecimalConstant	: '0'
				| [1-9] [0-9]*  ;
OctalConstant	: '0' [0-7]*  ;
HexadecimalConstant	: '0' [xX] [0-9a-fA-F]+; 
WS			: (' ' 	 
			| '\t'   
			| '\r'    
			| '\n' 
			)+
	-> channel(HIDDEN)	 
    ;
