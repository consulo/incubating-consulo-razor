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

import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.file.FileViewProvider;
import consulo.language.file.LanguageFileViewProviderFactory;
import consulo.language.psi.PsiManager;
import consulo.razor.csharp.lang.RazorCSharpLanguage;
import consulo.virtualFileSystem.VirtualFile;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
@ExtensionImpl
public class RazorCSharpFileViewProviderFactory implements LanguageFileViewProviderFactory
{
	@Override
	public FileViewProvider createFileViewProvider(@Nonnull VirtualFile file, Language language, @Nonnull PsiManager manager, boolean eventSystemEnabled)
	{
		return new RazorCSharpFileViewProvider(manager, file, eventSystemEnabled);
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return RazorCSharpLanguage.INSTANCE;
	}
}
