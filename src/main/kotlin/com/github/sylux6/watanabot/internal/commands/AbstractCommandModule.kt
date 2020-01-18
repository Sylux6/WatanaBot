package com.github.sylux6.watanabot.internal.commands

import com.github.sylux6.watanabot.internal.types.CommandLevelAccess
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import com.github.sylux6.watanabot.utils.BotUtils
import com.github.sylux6.watanabot.utils.MessageUtils

abstract class AbstractCommandModule(val name: String, val shortName: String, commands: Set<AbstractCommand>,
                                     val restrict: Boolean = false) {

    abstract val moduleDescription: String

    val commandMap: MutableMap<String, AbstractCommand> = HashMap()

    init {
        commandMap["--help"] = object : AbstractCommand("help") {
            override val template: String
                get() = ""
            override val description = "Get list of commands"

            override fun runCommand(event: MessageReceivedEvent, args: List<String>) {
                val message = EmbedBuilder()
                        .setAuthor(BotUtils.bot.selfUser.name, null, BotUtils.bot.selfUser.effectiveAvatarUrl)
                        .setColor(BotUtils.PRIMARY_COLOR)
                        .setTitle("List of commands")
                        .setDescription(moduleDescription)
                for ((commandName, command) in commandMap.toSortedMap()) {
                    // Filter some access levels we don't need to check to show documentation
                    if (!command.levelAccess.filter { it in listOf(CommandLevelAccess.IN_VOICE, CommandLevelAccess.BOT_IN_VOICE) }
                        .all { checkCommandAccess(event, it) }
                    ) {
                        continue
                    }
                    if (commandName == "--help") {
                        continue
                    }
                    message.addField("`$commandName`", command.description, false)
                }
                MessageUtils.sendMessage(event.channel, message.build())
            }
        }
        for (command in commands) {
            commandMap[command.name] = command
        }
    }
}