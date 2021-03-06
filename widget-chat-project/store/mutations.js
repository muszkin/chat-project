export default {
    toogleChatOpen (state) {
        state.chat_open = !state.chat_open
    },
    toggleSidebar (state) {
        state.sidebar = !state.sidebar
    },
    newMessage (state, message) {
        state.messages.push(message)
    },
    setMessages (state, { messagesList }) {
        console.log(messagesList);
        state.messages = messagesList
    },
}
  