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

package consulo.razor.csharp.lang;

import consulo.annotation.component.ExtensionImpl;
import consulo.csharp.lang.impl.psi.CSharpElements;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.file.FileViewProvider;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiBuilder;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;
import consulo.razor.csharp.lang.lexer.RazorCSharpLexer;
import consulo.razor.csharp.lang.psi.RazorCSharpFile;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
@ExtensionImpl
public class RazorCSharpParserDefinition implements ParserDefinition
{
	private static final IFileElementType FILE_ELEMENT_TYPE = new IFileElementType("RAZOR_CSHARP_FILE", RazorCSharpLanguage.INSTANCE);

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return RazorCSharpLanguage.INSTANCE;
	}

	@Nonnull
	@Override
	public Lexer createLexer(@Nonnull LanguageVersion languageVersion)
	{
		return new RazorCSharpLexer.Merge();
	}

	@Nonnull
	@Override
	public PsiParser createParser(@Nonnull LanguageVersion languageVersion)
	{
		return new PsiParser()
		{
			@Nonnull
			@Override
			public ASTNode parse(@Nonnull IElementType root, @Nonnull PsiBuilder builder, @Nonnull LanguageVersion languageVersion)
			{
				PsiBuilder.Marker mark = builder.mark();
				PsiBuilder.Marker typeMarker = builder.mark();
				PsiBuilder.Marker methodMarker = builder.mark();
				PsiBuilder.Marker blockMarker = builder.mark();
				while(!builder.eof())
				{
					builder.advanceLexer();
				}
				blockMarker.done(CSharpElements.BLOCK_STATEMENT);
				methodMarker.done(RazorCSharpElements.METHOD_DECLARATION);
				typeMarker.done(RazorCSharpElements.TYPE_DECLARATION);
				mark.done(root);
				return builder.getTreeBuilt();
			}
		};
	}

	@Nonnull
	@Override
	public IFileElementType getFileNodeType()
	{
		return FILE_ELEMENT_TYPE;
	}

	@Nonnull
	@Override
	public TokenSet getWhitespaceTokens(@Nonnull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@Nonnull
	@Override
	public TokenSet getCommentTokens(@Nonnull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@Nonnull
	@Override
	public TokenSet getStringLiteralElements(@Nonnull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@Nonnull
	@Override
	public PsiElement createElement(ASTNode node)
	{
		return new ASTWrapperPsiElement(node);
	}

	@Override
	public PsiFile createFile(FileViewProvider viewProvider)
	{
		return new RazorCSharpFile(viewProvider, RazorCSharpLanguage.INSTANCE);
	}
}
