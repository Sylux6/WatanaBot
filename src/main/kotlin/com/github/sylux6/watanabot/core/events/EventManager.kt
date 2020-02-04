package com.github.sylux6.watanabot.core.events

import club.minnced.jda.reactor.ReactiveEventManager
import club.minnced.jda.reactor.createManager
import club.minnced.jda.reactor.on
import com.github.sylux6.watanabot.core.events.guild.onGuildMemberLeaveEvent
import com.github.sylux6.watanabot.core.events.message.onMessageReceivedEvent
import com.github.sylux6.watanabot.core.events.user.onUserUpdateActivityEnd
import com.github.sylux6.watanabot.core.events.user.onUserUpdateActivityStart
import com.github.sylux6.watanabot.utils.isPrivateServer
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.user.UserActivityEndEvent
import net.dv8tion.jda.api.events.user.UserActivityStartEvent
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ScheduledExecutorService
import kotlin.concurrent.thread

var count: Int = 0
val executor: ScheduledExecutorService =
    Executors.newScheduledThreadPool(ForkJoinPool.getCommonPoolParallelism()) {
        thread(start = false, block = it::run, name = "jda-thread-${count++}", isDaemon = true)
    }
val schedulerWrap: Scheduler = Schedulers.fromExecutor(executor)
val manager: ReactiveEventManager = createManager {
    scheduler = schedulerWrap
    isDispose = false
}

fun initReactiveEventManager() {
    manager.on<MessageReceivedEvent>()
        .filter { !it.message.author.isBot && !it.message.author.isFake }
        .subscribe { onMessageReceivedEvent(it) }

    manager.on<GuildMemberLeaveEvent>()
        .filter { isPrivateServer(it.guild.idLong) }
        .subscribe { onGuildMemberLeaveEvent(it) }

    manager.on<UserActivityStartEvent>()
        .filter { isPrivateServer(it.guild.idLong) }
        .subscribe { onUserUpdateActivityStart(it) }

    manager.on<UserActivityEndEvent>()
        .filter { isPrivateServer(it.guild.idLong) }
        .subscribe { onUserUpdateActivityEnd(it) }
}