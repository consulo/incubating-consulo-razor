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

package org.mustbe.consulo.razor.csharp.highlight;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.razor.csharp.lang.RazorCSharpTokens;
import org.mustbe.consulo.razor.csharp.lang.lexer.RazorCSharpLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 11.03.2016
 */
public class RazorCSharpHighlighter extends SyntaxHighlighterBase
{
	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new RazorCSharpLexer.Merge();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
	{
		if(tokenType == RazorCSharpTokens.BLOCK_COMMENT)
		{
			return pack(DefaultLanguageHighlighterColors.BLOCK_COMMENT);
		}
		return EMPTY;
	}
}
