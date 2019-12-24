package core

import core.listeners.ActivityListener
import core.listeners.LeaverListener
import core.listeners.MessageListener
import core.listeners.ReactionListener
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDABuilder
import reminder.QuartzScheduler
import utils.BotUtils

object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        if (args.size != 1) {
            println("Please enter the bot token in argument")
            return
        }

        // Building bot
        BotUtils.bot = JDABuilder(AccountType.BOT).setToken(args[0])
                .addEventListeners(
                        MessageListener(),
                        ActivityListener(),
                        LeaverListener(),
                        ReactionListener()
                )
                .build()
                .awaitReady()
        BotUtils.randomStatus()

        QuartzScheduler.run()

    }

}
