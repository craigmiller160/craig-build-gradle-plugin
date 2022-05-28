package io.craigmiller160.craigbuild.gradle.plugin

import io.craigmiller160.craigbuild.gradle.plugin.model.CraigBuildModelBuilder
import javax.inject.Inject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry

class CraigBuildGradlePlugin
@Inject
constructor(private val registry: ToolingModelBuilderRegistry) : Plugin<Project> {
  override fun apply(target: Project) {
    registry.register(CraigBuildModelBuilder())
  }
}
