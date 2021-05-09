function addTag() {
    var tag = {
        name: ""
    }
    tag.name = $("#comment-tag-name").val();
    sendNewTagData(tag)
}

function sendNewTagData(tag) {
    AJS.$.ajax({
        type: "POST",
        url: "/confluence/rest/library/1.0/tag/addTag",
        contentType: "application/json",
        data: JSON.stringify(tag),
        statusCode: {
            200: function() {
                AJS.messages.success("#messaging", {
                    title: "Успешно",
                    body: "Тег успешно добавлен",
                    fadeout: true
                });
            },
            208: function() {
                AJS.messages.error("#messaging", {
                    title: "Тег уже существует",
                    body: "Невозможно добавить существующий тег",
                    fadeout: true
                });
            }
        }
    });
    $('form[name=add-tag-form]').trigger('reset');
}