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

import org.mustbe.consulo.razor.csharp.lang.psi.RazorCSharpMethodDeclaration;
import org.mustbe.consulo.razor.csharp.lang.psi.RazorCSharpTypeDeclaration;
import com.intellij.psi.tree.IElementType;
import consulo.psi.tree.ElementTypeAsPsiFactory;

/**
 * @author VISTALL
 * @since 12.03.2016
 */
public interface RazorCSharpElements
{
	IElementType TYPE_DECLARATION = new ElementTypeAsPsiFactory("TYPE_DECLARATION", RazorCSharpLanguage.INSTANCE, RazorCSharpTypeDeclaration.class);

	IElementType METHOD_DECLARATION = new ElementTypeAsPsiFactory("METHOD_DECLARATION", RazorCSharpLanguage.INSTANCE, RazorCSharpMethodDeclaration.class);
}
