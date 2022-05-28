package io.craigmiller160.craigbuild.gradle.plugin.model

import java.io.Serializable
import org.gradle.api.Project

class CraigBuildProjectImpl(project: Project) : CraigBuildProject, Serializable {
  override val group: String = project.group.toString()
  override val name: String = project.name
  override val version: String = project.version.toString()
}
