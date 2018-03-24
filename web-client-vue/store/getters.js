export default {
  getUserList (state) {
    return state.userList
  },
  getMessages (state) {
    return state.messages
  },
  getMessagesLength (state) {
    return state.messages.length
  },
  getActiveUserId (state) {
    return state.userList.find(user => user.isActive).userId
  }
}
