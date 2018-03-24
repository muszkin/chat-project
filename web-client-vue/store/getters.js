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
    if (state.userList.length !== 0 && state.userList.find(user => user.isActive)) {
      return state.userList.find(user => user.isActive)
    } else {
      return false
    }
  },
  getActiveUserId (state) {
    if (state.userList.length !== 0 && state.userList.find(user => user.isActive)) {
      return state.userList.find(user => user.isActive).userId
    } else {
      return false
    }
  }
}
