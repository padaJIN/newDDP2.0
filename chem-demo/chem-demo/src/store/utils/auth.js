
function getToken() {
    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    return user.token
}