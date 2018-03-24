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
  }
}
