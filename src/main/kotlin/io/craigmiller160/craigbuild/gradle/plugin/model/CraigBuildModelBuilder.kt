package io.craigmiller160.craigbuild.gradle.plugin.model

import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilder

class CraigBuildModelBuilder : ToolingModelBuilder {
  override fun canBuild(modelName: String): Boolean = CraigBuildProject::class.java.name == modelName

  override fun buildAll(modelName: String, project: Project): Any = CraigBuildProjectImpl(project)
}
