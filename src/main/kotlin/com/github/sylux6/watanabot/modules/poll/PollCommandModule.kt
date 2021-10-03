package com.github.sylux6.watanabot.modules.poll

import com.github.sylux6.watanabot.internal.commands.AbstractCommandModule
import com.github.sylux6.watanabot.modules.poll.commands.PollNewCommand

object PollCommandModule : AbstractCommandModule(
    "Poll",
    "poll",
    setOf(PollNewCommand)
) {
    override val moduleDescription: String
        get() = "Commands to create a poll."
}
