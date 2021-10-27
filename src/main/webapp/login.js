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

function auth() {
    $.ajax( {
        method: 'POST',
        url: "http://localhost:8080/todo/login.do",
        data: JSON.stringify({
            email: $('#email').val(),
            password: $('#password').val()
        }),
        dataType: 'json'
    }).done(function (data) {
        if (data === '200 OK') {
            window.location.href = "http://localhost:8080/todo/index.html";
        } else if (data === '407') {
            $('#invalid').text('Неправильный пароль');
            console.log(data);
        } else  {
            $('#invalid').text('Такой пользователь не существует');
            console.log(data);
        }
    }).fail(function (err) {
        alert('Error Sending!' + err);
        console.log(err);
    });
}