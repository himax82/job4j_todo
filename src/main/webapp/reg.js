function validate() {
    if ($('#name').val() === "") {
        alert("Введите имя");
        return false;
    }
    if ($('#email').val() === "") {
        alert("Введите email");
        return false;
    }
    if ($('#password').val() === "") {
        alert("Уажите пароль");
        return false;
    }
}