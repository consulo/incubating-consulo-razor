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

import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.razor.csharp.lang.RazorCSharpLanguage;
import consulo.razor.icon.RazorIconGroup;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

	@Nonnull
	@Override
	public String getId()
	{
		return "RAZOR_CSHARP";
	}

	@Nonnull
	@Override
	public String getDescription()
	{
		return "Razor Template C# files";
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return "cshtml";
	}

	@Nullable
	@Override
	public Image getIcon()
	{
		return RazorIconGroup.cshtml();
	}
}
