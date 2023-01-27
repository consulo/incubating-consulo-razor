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

package consulo.razor.csharp.fileTypes;

import consulo.language.Language;
import consulo.language.impl.file.MultiplePsiFilesPerDocumentFileViewProvider;
import consulo.language.impl.psi.PsiFileImpl;
import consulo.language.parser.ParserDefinition;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.language.template.TemplateLanguageFileViewProvider;
import consulo.razor.csharp.lang.RazorCSharpLanguage;
import consulo.razor.csharp.lang.RazorCSharpTokens;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.lang.html.HTMLLanguage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
public class RazorCSharpFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements TemplateLanguageFileViewProvider
{
	private static Set<Language> ourLanguages = Set.of(RazorCSharpLanguage.INSTANCE, HTMLLanguage.INSTANCE);

	public RazorCSharpFileViewProvider(PsiManager manager, VirtualFile virtualFile, boolean eventSystemEnabled)
	{
		super(manager, virtualFile, eventSystemEnabled);
	}

	@Nullable
	@Override
	protected PsiFile createFile(@Nonnull Language lang)
	{
		if(lang == getBaseLanguage())
		{
			return ParserDefinition.forLanguage(lang).createFile(this);
		}

		if(lang == getTemplateDataLanguage())
		{
			PsiFileImpl file = (PsiFileImpl) ParserDefinition.forLanguage(lang).createFile(this);
			file.setContentElementType(RazorCSharpTokens.TEMPLATE_DATA);
			return file;
		}

		return null;
	}

	@Nonnull
	@Override
	public Set<Language> getLanguages()
	{
		return ourLanguages;
	}

	@Nonnull
	@Override
	public Language getBaseLanguage()
	{
		return RazorCSharpLanguage.INSTANCE;
	}

	@Nonnull
	@Override
	public Language getTemplateDataLanguage()
	{
		return HTMLLanguage.INSTANCE;
	}

	@Override
	protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile fileCopy)
	{
		return new RazorCSharpFileViewProvider(getManager(), fileCopy, false);
	}
}
