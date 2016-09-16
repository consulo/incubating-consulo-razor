/*
 * Copyright 2013-2016 must-be.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package consulo.razor.csharp.lang.lexer;

import consulo.csharp.lang.psi.CSharpTokens;
import consulo.razor.csharp.lang.RazorCSharpTokens;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;

%%

%public
%class _RazorCSharpLexer
%extends LexerBase
%unicode
%function advanceImpl
%type IElementType
%eof{  return;
%eof}

%xstate COMMENT

WHITE_SPACE=[ \n\r\t\f]+

IDENTIFIER=[:jletter:] [:jletterdigit:]*

%%

<YYINITIAL>
{
    {WHITE_SPACE}     { return RazorCSharpTokens.WHITE_SPACE; }

    "@*"              { yybegin(COMMENT); }

    "for"             { return CSharpTokens.FOR_KEYWORD; }

    "if"              { return CSharpTokens.IF_KEYWORD; }

    "while"           { return CSharpTokens.WHILE_KEYWORD; }

    "@"               { return RazorCSharpTokens.AT; }

    "?."              { return CSharpTokens.NULLABE_CALL; }

    "."               { return CSharpTokens.DOT; }

    "::"              { return CSharpTokens.COLONCOLON; }

    "("               { return CSharpTokens.LPAR; }

    ")"               { return CSharpTokens.RPAR; }

    "["               { return CSharpTokens.LBRACKET; }

    "]"               { return CSharpTokens.RBRACKET; }

    "{"               { return CSharpTokens.LBRACE; }

    "}"               { return CSharpTokens.RBRACE; }


    {IDENTIFIER}      { return CSharpTokens.IDENTIFIER; }

    [^]               { return RazorCSharpTokens.TEMPLATE_TEXT; }
}

<COMMENT>
{
   "*@"             { yybegin(YYINITIAL); return RazorCSharpTokens.BLOCK_COMMENT; }

   [^]              {}

   <<EOF>>          { yybegin(YYINITIAL); return RazorCSharpTokens.BLOCK_COMMENT; }
}
