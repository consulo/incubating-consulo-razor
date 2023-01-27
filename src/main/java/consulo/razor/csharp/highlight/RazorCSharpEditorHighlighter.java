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

import consulo.colorScheme.EditorColorsScheme;
import consulo.csharp.lang.CSharpLanguage;
import consulo.language.editor.highlight.*;
import consulo.project.Project;
import consulo.razor.csharp.lang.RazorCSharpTokens;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.lang.html.HTMLLanguage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 11.03.2016
 */
public class RazorCSharpEditorHighlighter extends LayeredLexerEditorHighlighter
{
	public RazorCSharpEditorHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @Nonnull EditorColorsScheme scheme)
	{
		super(new RazorCSharpHighlighter(), scheme);

		SyntaxHighlighter htmlHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(HTMLLanguage.INSTANCE, project, virtualFile);
		LayerDescriptor htmlLayer = new LayerDescriptor(new TemplateDataHighlighterWrapper(htmlHighlighter), "\n");
		registerLayer(RazorCSharpTokens.TEMPLATE_TEXT, htmlLayer);

		htmlHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(CSharpLanguage.INSTANCE, project, virtualFile);
		htmlLayer = new LayerDescriptor(new TemplateDataHighlighterWrapper(htmlHighlighter), "\n");
		registerLayer(RazorCSharpTokens.CSHARP_TEXT, htmlLayer);
	}
}
