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

package org.mustbe.consulo.razor.csharp.fileTypes;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.razor.csharp.RazorCSharpIcons;
import org.mustbe.consulo.razor.csharp.lang.RazorCSharpLanguage;
import com.intellij.openapi.fileTypes.LanguageFileType;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
public class RazorCSharpFileType extends LanguageFileType
{
	public static final RazorCSharpFileType INSTANCE = new RazorCSharpFileType();

	private RazorCSharpFileType()
	{
		super(RazorCSharpLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getName()
	{
		return "RAZOR_CSHARP";
	}

	@NotNull
	@Override
	public String getDescription()
	{
		return "Razor Template C# files";
	}

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return "cshtml";
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return RazorCSharpIcons.Cshtml;
	}
}
