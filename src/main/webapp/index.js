let show = false;
let categories = new Map();
$(document).ready(function () {
    build();
    auth();
    getCategories();
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
    let SelCategories = $('#selectCategories').find(":selected").map(function () {
        return $(this).val();
    }).get().map(function (key) {
        return categories.get(parseInt(key));
    });
    $.ajax({
        method: 'post',
        url: 'http://localhost:8080/todo/add',
        data: JSON.stringify({
            description: $('#description').val(),
            categories: SelCategories
        })
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
        let cat = '';
        $.each(response, function (key, value) {
            if (show === true) {
                $.each(value.categories, function (k, v) {
                    cat += v.name;
                    cat += ' ';
                })
                    affair.push('<tr><td>' + value.description + '</td>' +
                        '<td>' + value.user.name + '</td>' +
                        '<td>' + cat + '</td>' +
                        '<td>' + '<div class="form-check">' +
                        '<input class="form-check-input" type="checkbox" onchange = "update(this.id)" value="" id="' + value.id + '">' +
                        '</div></td></tr>');
                cat = '';
            } else {
                if (value.done === false) {
                    $.each(value.categories, function (k, v) {
                        cat += v.name;
                        cat += ' ';
                    })
                    affair.push('<tr><td>' + value.description + '</td>' +
                        '<td>' + value.user.name + '</td>' +
                        '<td>' + cat + '</td>' +
                        '<td>' + '<div class="form-check">' +
                        '<input class="form-check-input" type="checkbox" onchange = "update(this.id)" value="" id="' + value.id + '">' +
                        '</div></td></tr>');
                    cat = '';
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

function getCategories() {
    categories.clear();
    $('#selectCategories').empty();
    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/todo/categories.do',
        dataType: 'json'
    }).done(function (data) {
        for (let category of data) {
            categories.set(category.id, category);
            $('#selectCategories').append($('<option>', {
                value: category.id,
                text: category.name
            }));
        }
    }).fail(function (err) {
        console.log(err);
    });
}