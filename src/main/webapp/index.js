let show = false;
$(document).ready(function () {
    build();
    auth();

    $('#add').click(function () {
        validateAndAdd();
        setTimeout(function () {
            build();
        }, 2000);
    });

    $('#flexSwitchCheckDefault').click(function () {
        show = !show;
        build();
    });
});

function validateAndAdd() {
    if ($('#description').val() === '') {
        alert('Enter your Affairs');
        return false;
    }
    addAffairs();
    return true;
}

function addAffairs() {
    $.get("http://localhost:8080/todo/add", {
        description: $('#description').val()
    }).done(function (response) {
        console.log("Fine Data Sent " + $('#description').val());
    }).fail(function (err) {
        alert('Error Sending!' + err);
        console.log("Error Sending data");
    });
}
function auth() {
    $.getJSON("http://localhost:8080/todo/auth.do"
    ).done(function (response) {
        $('#user').text('Пользователь ' + response.name +' | ');
        console.log("Response Data: " + response);
    }).fail(function (err) {
        alert('Request Failed!');
        console.log("Request Failed: " + err);
    });
}

function build() {
    $.post("http://localhost:8080/todo/affair.do"
    ).done(function (response) {
        let affair = [];
        $.each(response, function (key, value) {
            if (show === true) {
                    affair.push('<tr><td>' + value.description + '</td>' +
                        '<td>' + value.user.name + '</td>' +
                        '<td>' + '<div class="form-check">' +
                        '<input class="form-check-input" type="checkbox" onchange = "update(this.id)" value="" id="' + value.id + '">' +
                        '</div></td></tr>');
            } else {
                if (value.done === false) {
                    affair.push('<tr><td>' + value.description + '</td>' +
                        '<td>' + value.user.name + '</td>' +
                        '<td>' + '<div class="form-check">' +
                        '<input class="form-check-input" type="checkbox" onchange = "update(this.id)" value="" id="' + value.id + '">' +
                        '</div></td></tr>');
                }
            }
            $('#table').html(affair);
        });
    }).fail(function (err) {
        alert('buildTable Failed!');
        console.log("Request Failed: " + err);
    });
}

function update(id) {
    $.get("http://localhost:8080/todo/update", {
        id: id
    }).done(function (response) {
        build();
        console.log("It is done : " + response);
    }).fail(function (err) {
        alert('Affairs Failed!');
        console.log("Affairs Failed: " + err);
    });
}