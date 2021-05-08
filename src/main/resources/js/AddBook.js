function addBook() {
    var book = {
        name: "",
        isnb: "",
        yearPublishing: 0,
        pageVolume: 0,
        countCopies: 0,
        description: "",
        imageUrl: "",
        ebookUrl: "",
        publisher: "",
        authors: "",
        tags: "",
        editionTypes: ""
    }

    var text = ""

    book.name = $("#comment-name-book").val();
    if (book.name.length === 0) {
        text += "<li><strong>Некорректное название:</strong> пустая строка</li>"
    }

    book.authors = $("#comment-authors-book").val();
    if (book.authors.length === 0) {
        text += "<li><strong>Некорректные авторы:</strong> пустая строка</li>"
    }

    book.publisher = $("#comment-publisher-book").val();
    if (book.name.length === 0) {
        text += "<li><strong>Некорректный издатель:</strong> пустая строка</li>"
    }

    book.yearPublishing = $("#comment-year").val();
    if (isNumeric(book.yearPublishing) && parseInt(book.yearPublishing) >= 1800 && parseInt(book.yearPublishing) <= 3000) {
        book.yearPublishing = parseInt(book.yearPublishing)
    } else {
        text += "<li><strong>Некорректная дата пбликации:</strong> должно быть целое число от 1800 до 3000</li>"
    }
    book.pageVolume = $("#comment-count-pages").val();
    if (isNumeric(book.pageVolume) && parseInt(book.pageVolume) > 0) {
        book.pageVolume = parseInt(book.pageVolume)
    } else {
        if (text !== "") text += "\n";
        text += "<li><strong>Некорректный объем страниц:</strong> должно быть целое число большее 0</li>"
    }

    book.isnb = $("#comment-isnb-book").val();
    if (!isISNB(book.isnb)) {
        if (text !== "") text += "\n";
        text += "<li><strong>Некорректный ISNB:</strong> должна быть строка из цифр и -</li>"
    }
    // book.tags = $("#").val();
    book.editionTypes = $("#comment-type-edition").val(); // select
    book.description = $("#comment-input").val(); // textarea
    book.imageUrl = $("#comment-link").val();
    book.ebookUrl = $("#comment-link-ebook").val();
    // checkImgSrc(book.imageUrl)
    book.countCopies = $("#comment-count-copies").val();
    if (isNumeric(book.countCopies) && parseInt(book.countCopies) > 0) {
        book.countCopies = parseInt(book.countCopies)
    } else {
        if (text !== "") text += "\n";
        text += "<li><strong>Некорректное количество экземпляров:</strong> должно быть целое число большее 0</li>"
    }

    console.log(text);
    console.log(text.length);
    if (text.length === 0)
        sendNewBookData(book)
    else
        invalidData(text)
}

function sendNewBookData(book) {
    AJS.$.ajax({
        type: "POST",
        url: "/confluence/rest/library/1.0/book/addBook",
        contentType: "application/json",
        data: JSON.stringify(book),
        success: function (data) {
            //successFunction(data);
            AJS.messages.success("#messaging", {
                title: "Успешно",
                body: "Книга успешно добавлена!",
                fadeout: true
            });
        },
        error: function () {
            AJS.messages.error("#messaging", {
                title: "ОШИБКА",
                body: "Книга не была добавлена, попробуйте еще раз!",
                fadeout: true
            });
        }
    });
    $('form[name=add-book-form]').trigger('reset');
}

function invalidData(text) {
    text = "<ul>" + text + "</ul>";
    AJS.messages.error("#messaging", {
        title: "ОШИБКА ЗАПОЛНЕНИЯ ФОРМЫ",
        body: text,
        fadeout: true
    });
}

const checkImgSrc = src => {
    const img = new Image();
    img.onload = function () {
        console.log(`valid src: ${src}`);
    }
    img.onerror = function () {
        console.log(`unvalid src: ${src}`);
    }
    img.src = src;
    console.log(img.src)
}

function isNumeric(value) {
    return /^-?\d+$/.test(value);
}

function isISNB(value) {
    return /[0-9]{3}[-]?[0-9][-]?[0-9]{2}[-]?[0-9]{6}[-]?[0-9]/.test(value);
}
