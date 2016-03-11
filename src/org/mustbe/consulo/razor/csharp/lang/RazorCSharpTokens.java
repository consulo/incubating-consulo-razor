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

import com.intellij.psi.TokenType;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 10.03.2016
 */
public interface RazorCSharpTokens extends TokenType
{
	IElementType AT = new IElementType("AT", RazorCSharpLanguage.INSTANCE);

	IElementType CSHARP_TEXT = new IElementType("CSHARP_TEXT", RazorCSharpLanguage.INSTANCE);

	IElementType BLOCK_COMMENT = new IElementType("BLOCK_COMMENT", RazorCSharpLanguage.INSTANCE);

	IElementType OUTER_ELEMENT_TYPE = new IElementType("OUTER_ELEMENT_TYPE", RazorCSharpLanguage.INSTANCE);

	IElementType TEMPLATE_TEXT = new IElementType("TEMPLATE_TEXT", RazorCSharpLanguage.INSTANCE);

	TemplateDataElementType TEMPLATE_DATA = new TemplateDataElementType("TEMPLATE_DATA", RazorCSharpLanguage.INSTANCE, TEMPLATE_TEXT, OUTER_ELEMENT_TYPE);
}
