/**
 * @author VISTALL
 * @since 27/01/2023
 */
module consulo.razor {
    requires consulo.code.editor.api;
    requires consulo.color.scheme.api;
    requires consulo.language.api;
    requires consulo.language.editor.api;
    requires consulo.language.impl;
    requires consulo.localize.api;
    requires consulo.project.api;
    requires consulo.ui.api;
    requires consulo.util.lang;
    requires consulo.virtual.file.system.api;

    requires consulo.csharp.csharp.psi.api;
    requires consulo.csharp.psi.impl;
    requires consulo.dotnet.psi.api;
    requires com.intellij.xml.api;
    requires com.intellij.xml.html.api;
}