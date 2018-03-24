export default {
  toggleSidebar (state) {
    state.sidebar = !state.sidebar
  },
  newMessage (state, message) {
    state.messages.push(message)
  },
  changeActiveUser (state, userId) {
    state.userList = state.userList.map(user => {
      user.userId === userId ? user.isActive = true : user.isActive = false
      return user
    })
  },
  clearMessageList (state) {
    state.messages = []
  },
  setUserList (state, {userList}) {
    state.userList = userList
  },
  setMessages (state, { messagesList, userId }) {
    state.messages = messagesList
    const index = state.userList.map((user) => user.userId).indexOf(userId)
    state.userList[index].unreadMessages = 0
  },
  bumpUnreadMessagesByUserId (state, userId) {
    const index = state.userList.map((user) => user.userId).indexOf(userId)
    console.log(index)
    state.userList[index].unreadMessages = state.userList[index].unreadMessages + 1
  }
}
