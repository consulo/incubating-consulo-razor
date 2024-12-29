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

package consulo.razor.csharp.lang.psi;

import consulo.annotation.access.RequiredReadAction;
import consulo.csharp.lang.psi.CSharpFile;
import consulo.csharp.lang.psi.CSharpUsingListChild;
import consulo.dotnet.psi.DotNetQualifiedElement;
import consulo.language.Language;
import consulo.language.file.FileViewProvider;
import consulo.language.impl.psi.PsiFileBase;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
public class RazorCSharpFile extends PsiFileBase implements CSharpFile
{
	public RazorCSharpFile(@Nonnull FileViewProvider viewProvider, @Nonnull Language language)
	{
		super(viewProvider, language);
	}

	@Nonnull
	@Override
	public DotNetQualifiedElement[] getMembers()
	{
		return findChildrenByClass(DotNetQualifiedElement.class);
	}

	@RequiredReadAction
	@Nonnull
	@Override
	public CSharpUsingListChild[] getUsingStatements()
	{
		return new CSharpUsingListChild[0];
	}
}
