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

package org.mustbe.consulo.razor.csharp.lang;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.csharp.lang.psi.CSharpElements;
import org.mustbe.consulo.razor.csharp.lang.lexer.RazorCSharpLexer;
import org.mustbe.consulo.razor.csharp.lang.psi.RazorCSharpFile;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageVersion;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
public class RazorCSharpParserDefinition implements ParserDefinition
{
	private static final IFileElementType FILE_ELEMENT_TYPE = new IFileElementType("RAZOR_CSHARP_FILE", RazorCSharpLanguage.INSTANCE);
	@NotNull
	@Override
	public Lexer createLexer(@Nullable Project project, @NotNull LanguageVersion languageVersion)
	{
		return new RazorCSharpLexer.Merge();
	}

	@NotNull
	@Override
	public PsiParser createParser(@Nullable Project project, @NotNull LanguageVersion languageVersion)
	{
		return new PsiParser()
		{
			@NotNull
			@Override
			public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder, @NotNull LanguageVersion languageVersion)
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

	@NotNull
	@Override
	public IFileElementType getFileNodeType()
	{
		return FILE_ELEMENT_TYPE;
	}

	@NotNull
	@Override
	public TokenSet getWhitespaceTokens(@NotNull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@NotNull
	@Override
	public TokenSet getCommentTokens(@NotNull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@NotNull
	@Override
	public TokenSet getStringLiteralElements(@NotNull LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@NotNull
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

	@NotNull
	@Override
	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right)
	{
		return SpaceRequirements.MAY;
	}
}
