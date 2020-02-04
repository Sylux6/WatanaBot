package com.github.sylux6.watanabot.modules

import com.natpryce.konfig.Override
import io.kotlintest.Spec
import io.kotlintest.extensions.TopLevelTest
import io.kotlintest.specs.StringSpec
import io.mockk.mockk
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class CommandsTestCase : StringSpec() {
    lateinit var mockMessageReceivedEvent: MessageReceivedEvent
    lateinit var mockConfig: Override

    override fun beforeSpecClass(spec: Spec, tests: List<TopLevelTest>) {
        super.beforeSpecClass(spec, tests)
        mockMessageReceivedEvent = mockk(relaxed = true)
        mockConfig = mockk(relaxed = true)
    }
}