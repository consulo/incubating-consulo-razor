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

package org.mustbe.consulo.razor.csharp.lang.psi;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.RequiredReadAction;
import org.mustbe.consulo.csharp.lang.psi.CSharpElementVisitor;
import org.mustbe.consulo.csharp.lang.psi.CSharpGenericConstraint;
import org.mustbe.consulo.csharp.lang.psi.CSharpGenericConstraintList;
import org.mustbe.consulo.csharp.lang.psi.CSharpTypeDeclaration;
import org.mustbe.consulo.csharp.lang.psi.impl.source.resolve.type.CSharpGenericWrapperTypeRef;
import org.mustbe.consulo.csharp.lang.psi.impl.source.resolve.type.CSharpStaticTypeRef;
import org.mustbe.consulo.csharp.lang.psi.impl.source.resolve.type.CSharpTypeRefByQName;
import org.mustbe.consulo.dotnet.psi.DotNetGenericParameter;
import org.mustbe.consulo.dotnet.psi.DotNetGenericParameterList;
import org.mustbe.consulo.dotnet.psi.DotNetInheritUtil;
import org.mustbe.consulo.dotnet.psi.DotNetModifier;
import org.mustbe.consulo.dotnet.psi.DotNetModifierList;
import org.mustbe.consulo.dotnet.psi.DotNetNamedElement;
import org.mustbe.consulo.dotnet.psi.DotNetTypeList;
import org.mustbe.consulo.dotnet.resolve.DotNetTypeRef;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.IncorrectOperationException;

/**
 * @author VISTALL
 * @since 12.03.2016
 */
public class RazorCSharpTypeDeclaration extends ASTWrapperPsiElement implements CSharpTypeDeclaration
{
	public RazorCSharpTypeDeclaration(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull PsiElementVisitor visitor)
	{
		if(visitor instanceof CSharpElementVisitor)
		{
			((CSharpElementVisitor) visitor).visitTypeDeclaration(this);
		}
		else
		{
			visitor.visitElement(this);
		}
	}

	@RequiredReadAction
	@Override
	public PsiElement getLeftBrace()
	{
		return null;
	}

	@RequiredReadAction
	@Override
	public PsiElement getRightBrace()
	{
		return null;
	}

	@Nullable
	@Override
	public CSharpGenericConstraintList getGenericConstraintList()
	{
		return null;
	}

	@NotNull
	@Override
	public CSharpGenericConstraint[] getGenericConstraints()
	{
		return new CSharpGenericConstraint[0];
	}

	@Override
	public boolean isInterface()
	{
		return false;
	}

	@Override
	public boolean isStruct()
	{
		return false;
	}

	@Override
	public boolean isEnum()
	{
		return false;
	}

	@Override
	public boolean isNested()
	{
		return false;
	}

	@Nullable
	@Override
	public DotNetTypeList getExtendList()
	{
		return null;
	}

	@NotNull
	@Override
	public DotNetTypeRef[] getExtendTypeRefs()
	{
		return new DotNetTypeRef[] {new CSharpGenericWrapperTypeRef(new CSharpTypeRefByQName("RazorEngine.Templating.HtmlTemplateBase`1"), CSharpStaticTypeRef.DYNAMIC)};
	}

	@RequiredReadAction
	@Override
	public boolean isInheritor(@NotNull String s, boolean b)
	{
		return DotNetInheritUtil.isInheritor(this, s, b);
	}

	@Override
	public DotNetTypeRef getTypeRefForEnumConstants()
	{
		return null;
	}

	@RequiredReadAction
	@Nullable
	@Override
	public String getVmQName()
	{
		return null;
	}

	@RequiredReadAction
	@Nullable
	@Override
	public String getVmName()
	{
		return null;
	}

	@Nullable
	@Override
	public DotNetGenericParameterList getGenericParameterList()
	{
		return null;
	}

	@NotNull
	@Override
	public DotNetGenericParameter[] getGenericParameters()
	{
		return new DotNetGenericParameter[0];
	}

	@Override
	public int getGenericParametersCount()
	{
		return 0;
	}

	@NotNull
	@Override
	public DotNetNamedElement[] getMembers()
	{
		return new DotNetNamedElement[0];
	}

	@RequiredReadAction
	@Override
	public boolean hasModifier(@NotNull DotNetModifier dotNetModifier)
	{
		return false;
	}

	@RequiredReadAction
	@Nullable
	@Override
	public DotNetModifierList getModifierList()
	{
		return null;
	}

	@RequiredReadAction
	@Nullable
	@Override
	public String getPresentableParentQName()
	{
		return null;
	}

	@RequiredReadAction
	@Nullable
	@Override
	public String getPresentableQName()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiElement getNameIdentifier()
	{
		return null;
	}

	@Override
	public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException
	{
		return null;
	}
}
