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

package org.mustbe.consulo.razor.csharp.lang.lexer;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.csharp.lang.psi.CSharpTokens;
import org.mustbe.consulo.razor.csharp.lang.RazorCSharpTokens;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
public class RazorCSharpLexer extends LookAheadLexer
{
	public static class Merge extends MergingLexerAdapter
	{
		public Merge()
		{
			super(new RazorCSharpLexer(), TokenSet.create(RazorCSharpTokens.TEMPLATE_TEXT));
		}
	}

	public static TokenSet ourPairStatementKeywords = TokenSet.create(CSharpTokens.FOR_KEYWORD, CSharpTokens.IF_KEYWORD, CSharpTokens.WHILE_KEYWORD);

	public RazorCSharpLexer()
	{
		super(new _RazorCSharpLexer());
	}

	@Override
	protected void lookAhead(Lexer baseLexer)
	{
		IElementType tokenType = baseLexer.getTokenType();
		if(tokenType == null)
		{
			advanceLexer(baseLexer);
			return;
		}

		if(tokenType == RazorCSharpTokens.AT)
		{
			// eat AT
			advanceAs(baseLexer, RazorCSharpTokens.AT);

			LexerPosition currentPosition = baseLexer.getCurrentPosition();

			Ref<LexerPosition> positionRef = Ref.create();
			eatExpression(baseLexer, positionRef);
			baseLexer.restore(currentPosition);

			LexerPosition lexerPosition = positionRef.get();
			if(lexerPosition != null)
			{
				while(baseLexer.getTokenType() != null)
				{
					if(baseLexer.getTokenStart() == lexerPosition.getOffset())
					{
						break;
					}

					baseLexer.advance();
				}

				addToken(baseLexer.getTokenStart(), RazorCSharpTokens.CSHARP_TEXT);
			}
		}
		else if(tokenType == RazorCSharpTokens.BLOCK_COMMENT)
		{
			advanceLexer(baseLexer);
		}
		else
		{
			advanceAs(baseLexer, RazorCSharpTokens.TEMPLATE_TEXT);
		}
	}

	private boolean eatExpression(Lexer lexer, @NotNull Ref<LexerPosition> positionRef)
	{
		IElementType tokenType = lexer.getTokenType();
		if(tokenType == CSharpTokens.LBRACE)
		{
			eatPairElements(lexer, CSharpTokens.LBRACE, CSharpTokens.RBRACE);

			positionRef.set(lexer.getCurrentPosition());
		}
		else if(tokenType == CSharpTokens.LPAR)
		{
			eatPairElements(lexer, CSharpTokens.LPAR, CSharpTokens.RPAR);

			positionRef.set(lexer.getCurrentPosition());
		}
		else if(ourPairStatementKeywords.contains(tokenType))
		{
			lexer.advance();

			skipSpaces(lexer);

			eatPairElements(lexer, CSharpTokens.LPAR, CSharpTokens.RPAR);

			skipSpaces(lexer);

			eatPairElements(lexer, CSharpTokens.LBRACE, CSharpTokens.RBRACE);

			positionRef.set(lexer.getCurrentPosition());
		}
		else if(tokenType == CSharpTokens.IDENTIFIER)
		{
			lexer.advance();

			eatArguments(lexer);

			positionRef.set(lexer.getCurrentPosition());
		}
		return true;
	}

	private static void eatArguments(Lexer lexer)
	{
		skipSpaces(lexer);

		while(lexer.getTokenType() == CSharpTokens.LPAR || lexer.getTokenType() == CSharpTokens.LBRACKET)
		{
			IElementType tokenType = lexer.getTokenType();
			eatPairElements(lexer, tokenType, tokenType == CSharpTokens.LPAR ? CSharpTokens.RPAR : CSharpTokens.RBRACKET);
		}
	}

	private static void skipSpaces(Lexer lexer)
	{
		while(lexer.getTokenType() == RazorCSharpTokens.WHITE_SPACE)
		{
			lexer.advance();
		}
	}

	private static void eatPairElements(Lexer lexer, IElementType open, IElementType stop)
	{
		IElementType tokenType = lexer.getTokenType();
		if(tokenType == open)
		{
			lexer.advance();

			int count = 1;
			IElementType elementType;
			while((elementType = lexer.getTokenType()) != null)
			{
				lexer.advance();

				if(elementType == open)
				{
					count++;
				}
				else if(elementType == stop)
				{
					count--;
					if(count <= 0)
					{
						break;
					}
				}
			}
		}
	}
}
