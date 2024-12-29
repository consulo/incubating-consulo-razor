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

import consulo.csharp.lang.CSharpLanguage;
import consulo.csharp.lang.impl.parser.CSharpBuilderWrapper;
import consulo.csharp.lang.impl.parser.ModifierSet;
import consulo.csharp.lang.impl.parser.exp.ExpressionParsing;
import consulo.csharp.lang.impl.parser.stmt.StatementParsing;
import consulo.csharp.lang.psi.CSharpTokens;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.ast.ILazyParseableElementType;
import consulo.language.ast.TokenType;
import consulo.language.impl.psi.template.TemplateDataElementType;
import consulo.language.parser.PsiBuilder;
import consulo.language.parser.PsiBuilderFactory;
import consulo.language.psi.PsiElement;
import consulo.language.version.LanguageVersion;
import consulo.project.Project;
import consulo.razor.csharp.lang.lexer.RazorCSharpLexer;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
public interface RazorCSharpTokens extends TokenType
{
	IElementType AT = new IElementType("AT", RazorCSharpLanguage.INSTANCE);

	IElementType CSHARP_TEXT = new ILazyParseableElementType("CSHARP_TEXT", RazorCSharpLanguage.INSTANCE)
	{
		@Override
		protected ASTNode doParseContents(@Nonnull ASTNode chameleon, @Nonnull PsiElement psi)
		{
			final Project project = psi.getProject();
			final Language languageForParser = getLanguageForParser(psi);
			final LanguageVersion languageVersion = CSharpLanguage.INSTANCE.getVersions()[0];
			final PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon, null, languageForParser, languageVersion, chameleon.getChars());

			CSharpBuilderWrapper builderWrapper = new CSharpBuilderWrapper(builder, languageVersion);

			PsiBuilder.Marker rootMarker = builderWrapper.mark();
			if(RazorCSharpLexer.ourPairStatementKeywords.contains(builder.getTokenType()) || builder.getTokenType() == CSharpTokens.LBRACE)
			{
				StatementParsing.parse(builderWrapper, ModifierSet.create());
			}
			else
			{
				ExpressionParsing.parse(builderWrapper, ModifierSet.create());
			}

			while(!builder.eof())
			{
				PsiBuilder.Marker mark = builder.mark();
				builder.advanceLexer();
				mark.error("Unexpected element");
			}

			rootMarker.done(this);
			return builder.getTreeBuilt().getFirstChildNode();
		}

		@Override
		protected Language getLanguageForParser(PsiElement psi)
		{
			return CSharpLanguage.INSTANCE;
		}
	};

	IElementType BLOCK_COMMENT = new IElementType("BLOCK_COMMENT", RazorCSharpLanguage.INSTANCE);

	IElementType OUTER_ELEMENT_TYPE = new IElementType("OUTER_ELEMENT_TYPE", RazorCSharpLanguage.INSTANCE);

	IElementType TEMPLATE_TEXT = new IElementType("TEMPLATE_TEXT", RazorCSharpLanguage.INSTANCE);

	TemplateDataElementType TEMPLATE_DATA = new TemplateDataElementType("TEMPLATE_DATA", RazorCSharpLanguage.INSTANCE, TEMPLATE_TEXT, OUTER_ELEMENT_TYPE);
}
