function validate() {
    if ($('#email').val() === "") {
        alert("Введите email");
        return false;
    }
    if ($('#password').val() === "") {
        alert("Уажите пароль");
        return false;
    }
    return true;
}