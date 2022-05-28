package io.craigmiller160.craigbuild.gradle.plugin.model

import org.gradle.api.Project
import java.io.Serializable

class CraigBuildProjectImpl(
    project: Project
) : CraigBuildProject, Serializable {
    override val group: String = project.group.toString()
    override val name: String = project.name
    override val version: String = project.version.toString()
}