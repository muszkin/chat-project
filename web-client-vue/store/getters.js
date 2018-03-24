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
  getActiveUser (state) {
    return state.userList.length !== 0 ? state.userList.find(user => user.isActive) : false
  },
  getActiveUserId (state) {
    return state.userList.length !== 0 ? state.userList.find(user => user.isActive).userId : false
  }
}