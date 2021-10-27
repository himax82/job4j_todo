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
    authEmail();
}

function authEmail() {
    $.ajax( {
        method: 'POST',
        url: "http://localhost:8080/todo/reg.do",
        data: JSON.stringify({
            name: $('#name').val(),
            email: $('#email').val(),
            password: $('#password').val()
        }),
        dataType: 'json'
    }).done(function (data) {
        if (data !== '200') {
            window.location.href = "http://localhost:8080/todo/index.html";
        } else {
            $('#fail').text('Пользователь с такий email уже существует');
            console.log(data);
        }
    }).fail(function (err) {
        alert('Error Sending!' + err);
            console.log(err);
    });
}