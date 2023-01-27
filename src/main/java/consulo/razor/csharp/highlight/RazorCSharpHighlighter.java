/*
 * Copyright 2013-2016 consulo.io
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

package consulo.razor.csharp.highlight;

import consulo.codeEditor.DefaultLanguageHighlighterColors;
import consulo.colorScheme.TextAttributesKey;
import consulo.language.ast.IElementType;
import consulo.language.editor.highlight.SyntaxHighlighterBase;
import consulo.language.lexer.Lexer;
import consulo.razor.csharp.lang.RazorCSharpTokens;
import consulo.razor.csharp.lang.lexer.RazorCSharpLexer;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 11.03.2016
 */
public class RazorCSharpHighlighter extends SyntaxHighlighterBase
{
	@Nonnull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new RazorCSharpLexer.Merge();
	}

	@Nonnull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
	{
		if(tokenType == RazorCSharpTokens.BLOCK_COMMENT)
		{
			return pack(DefaultLanguageHighlighterColors.BLOCK_COMMENT);
		}
		else if(tokenType == RazorCSharpTokens.AT)
		{
			return pack(DefaultLanguageHighlighterColors.MARKUP_ENTITY);
		}
		return EMPTY;
	}
}
