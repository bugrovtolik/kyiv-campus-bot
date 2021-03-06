package actions

import Bot
import MarkupUtil
import MessageTexts.DEFAULT
import isAdmin
import org.telegram.telegrambots.meta.api.objects.Message
import storage.student.Student
import storage.student.StudentDao.adminChatId

class DefaultAction(bot: Bot, message: Message): Action(bot, message) {
    fun forwardAdmin(student: Student) {
        if (!message.chatId.isAdmin()) {
            forwardMessage(adminChatId, message.chatId, message.messageId)
            editOldMessage(DEFAULT, markup = MarkupUtil.getDefaultMarkup(student))
        }
    }

    fun mainMenu(student: Student) {
        student.status = null
        student.statusParam = null
        editOldMessage(DEFAULT, markup = MarkupUtil.getDefaultMarkup(student))
    }
}
