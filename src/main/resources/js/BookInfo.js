
var bookConst

function getBookById(bookId) {
    var book
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/book/getBookById",
        data: {id: bookId},
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown)
        },
        success: function (data) {
            fillPage(data)
        }
    })
}

function fillPage(book) {
    bookConst = book
    $("#top-book-info-img").attr("src", book.imageUrl)
    $('#book-name').html(book.name);
    $('#book-authors').html(book.authors);
    $('#book-publisher').html(book.publisher);
    $('#book-year-publishing').html(book.yearPublishing);
    $('#book-page-volume').html(book.pageVolume);
    $('#book-isnb').html(book.isnb);
    $('#book-edition-type').html(book.editionTypes);
    $('#desc-text').html(book.description);
    $('#book-tags').html(book.tags)

    if (book.editionTypes.indexOf("Физический") !== -1) {
        // $('.paper-book').append("<h3 class=\"type-book-h3\">Физическая книга</h3> <button class=\"aui-button aui-button-primary btn-get\" type=\"button\" onclick=\"addToWaiting($action.getId())\">Получить книгу</button>");
        $('.paper-book').attr("style", "display: flex;")
    }

    if (book.editionTypes.indexOf("Электронный") !== -1) {
        // $('.e-book').append("<h3 class=\"type-book-h3\">Электронная книга</h3> <button class=\"aui-button aui-button-primary btn-get\" type=\"button\" onclick=\"getEbook($action.getId())\">Скачать книгу</button>");
        $('.e-book').attr("style", "display: flex;")
    }

    getCommentaries(book.id)
}

function addToWaiting(bookId) {
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/lending/addToWaiting",
        data: {book_id: bookId},
        statusCode: {
            200: function() {
                AJS.messages.success("#messaging", {
                    title: "Книга записана на вас!",
                    body: "Вы можете придти и забрать книгу в библиотеке.",
                    fadeout: true
                });
            },
            208: function() {
                AJS.messages.error("#messaging", {
                    title: "Книга уже у вас на руках",
                    body: "Вы не можете получить еще 1 экземпляр.",
                    fadeout: true
                });
            },
            500: function() {
                AJS.dialog2("#demo-warning-dialog").show();
            }
        }
    })
}

function addToBooked(bookId) {
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/lending/addToBooked",
        data: {book_id: bookId},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            AJS.messages.error("#messaging", {
                title: "ОШИБКА",
                body: "Книга не забронирована, попробуйте еще раз!",
                fadeout: true
            });
            cancelBanner()
        },
        statusCode: {
            200: function () {
                AJS.messages.success("#messaging", {
                    title: "Успешно",
                    body: "Книга успешно забронирована! Ожидайте уведомление.",
                    fadeout: true
                });
                cancelBanner()
            },
            208: function () {
                AJS.messages.error("#messaging", {
                    title: "Книга уже уже забронирована",
                    body: "Ожидайте уведомления на почту.",
                    fadeout: true
                });
                cancelBanner()
            }
        }
    })
}

function getCommentaries(bookId) {
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/commentary/getCommentaries",
        data: {book_id: bookId},
        success: function (data) {
            fillCommentaries(data);
        },
        error: function () {
            AJS.messages.error("#messaging", {
                title: "Комментарии не загружены",
                body: "Попробуйте перезагрузить страницу",
                fadeout: true
            });
        }
    })
}

function fillCommentaries(comments) {
    for (var i = 0; i < comments.length; i++) {
        $('#other-commentaries').append('<div class="comment-item">\n' +
            '                                <div class="comment-head">\n' +
            '                                    <div class="employee-created">' + comments[i].userName + '</div>\n' +
            '                                    <div class="date-created">  (Дата создания: ' + new Date(comments[i].createdDate).toLocaleString() + ')</div>\n' +
            '                                </div>\n' +
            '                                <div class="comment-text">' + comments[i].description + '</div>\n' +
            '                                <hr class="comment-hr">' +
            '                            </div>');
    }
}

function cancelBanner() {
    AJS.dialog2("#demo-warning-dialog").hide();
}

function getEbook(bookId) {
    window.open(bookConst.ebookUrl);
}

function sendCommentary() {
    var commentary = {
        bookId: bookConst.id,
        description: $("#new-commentary-input").val()
    }

    if (commentary.description.replace(/\s/g, '').length !== 0) {

        AJS.$.ajax({
            type: "POST",
            url: "/confluence/rest/library/1.0/commentary/addCommentary",
            contentType: "application/json",
            data: JSON.stringify(commentary),
            success: function (data) {
                $('#other-commentaries').append('<div class="comment-item">\n' +
                    '                                <div class="comment-head">\n' +
                    '                                    <div class="employee-created">' + data.userName + '</div>\n' +
                    '                                    <div class="date-created">  (Дата создания: ' + new Date(data.createdDate).toLocaleString() + ')</div>\n' +
                    '                                </div>\n' +
                    '                                <div class="comment-text">' + data.description + '</div>\n' +
                    '                                <hr class="comment-hr">' +
                    '                            </div>');
                AJS.messages.success("#messaging", {
                    title: "Успешно",
                    body: "Комментарий добавлен",
                    fadeout: true
                });
            },
            error: function () {
                AJS.messages.error("#messaging", {
                    title: "ОШИБКА",
                    body: "Комментарий не добавлен, попробуйте еще раз!",
                    fadeout: true
                });
            }
        });
        $('form[name=new-commentary-form]').trigger('reset');
    }
}