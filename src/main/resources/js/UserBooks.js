function getAllUsersBooks() {
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/lending/getLendingsByKey",
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown)
        },
        success: function (data) {
            fillUserBooks(data)
        }
    })
}

function fillUserBooks(lendings) {
    var isChief = function (position) {
        // return position && ["На руках", "Возвращено", "Потеряно"].indexOf(position.trim().toUpperCase()) >= 0;
        return position === "На руках" || position === "Возвращено" || position === "Потеряно"
    };

    console.log(lendings)
    var dataGrid = $("#gridContainer-user-books").dxDataGrid({
        dataSource: lendings,
        columnsAutoWidth: true,
        showBorders: true,
        filterRow: {
            visible: true,
            applyFilter: "auto"
        },
        searchPanel: {
            visible: true,
            width: 240,
            placeholder: "Поиск..."
        },
        headerFilter: {
            visible: true
        },
        editing: {
            mode: "row",
            allowUpdating: true,
            allowDeleting: function(e) {
                return !isChief(e.row.data.status);
            },
            useIcons: true
        },
        onCellPrepared: function onCellPrepared(e) {
            if (e.rowType === "header") {
                e.cellElement.css("text-align", "center");
            }
            if (e.rowType === "data" && e.index === 1) {
                e.cellElement.css("text-align", "center");
            }
        },
        columns: [
            {
                caption: "Номер заказа",
                dataField: "id",
                width: 100,
                allowHeaderFiltering: false,
                allowSearch: false
            }, {
                caption: "Название книги",
                dataField: "bookName",
                allowHeaderFiltering: false,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                width: 50,
                type: "buttons",
                buttons: [{
                    text: "О книге",
                    icon: "info",
                    onClick: function (e) {
                        var bookId = e.row.data.bookId
                        window.location.href = '/confluence/library/book-info.action?id=' + bookId;
                    }
                }]
            },
            {
                caption: "Дата выдачи",
                dataField: "dateOfIssue",
                dataType: "date",
                width: 200,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                caption: "Дата возврата",
                dataField: "returnDate",
                dataType: "date",
                width: 200,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                caption: "Статус",
                dataField: "status",
                width: 200,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                caption: "Дата изменения статуса",
                dataField: "dateChangedStatus",
                dataType: "date",
                width: 200,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                type: "buttons",
                width: 50,
                buttons: [{
                    name: "delete",
                    onClick: function (e) {
                        deletelending(e.row.data.id);
                        lendings.splice(e.row.rowIndex, 1)
                        e.component.refresh(true);
                        e.event.preventDefault();
                    }
                }]
            }
        ]
    }).dxDataGrid('instance');

    var applyFilterTypes = [{
        key: "auto",
        name: "Immediately"
    }, {
        key: "onClick",
        name: "On Button Click"
    }];

    var applyFilterModeEditor = $("#useFilterApplyButton").dxSelectBox({
        items: applyFilterTypes,
        value: applyFilterTypes[0].key,
        valueExpr: "key",
        displayExpr: "name",
        onValueChanged: function (data) {
            dataGrid.option("filterRow.applyFilter", data.value);
        }
    }).dxSelectBox("instance");

    $("#filterRow").dxCheckBox({
        text: "Filter Row",
        value: true,
        onValueChanged: function (data) {
            dataGrid.clearFilter();
            dataGrid.option("filterRow.visible", data.value);
            applyFilterModeEditor.option("disabled", !data.value);
        }
    });

    $("#headerFilter").dxCheckBox({
        text: "Header Filter",
        value: true,
        onValueChanged: function (data) {
            dataGrid.clearFilter();
            dataGrid.option("headerFilter.visible", data.value);
        }
    });
}

function deletelending(lendingId) {
    var lending = {
        id: lendingId
    }
    var status = true
    AJS.$.ajax({
        type: "POST",
        url: "/confluence/rest/library/1.0/lending/deleteLending",
        contentType: "application/json",
        data: JSON.stringify(lending),
        success: function () {
            AJS.messages.success("#messaging", {
                title: "Успешно",
                body: "Заказ отмен",
                fadeout: true
            });
        },
        error: function () {
            AJS.messages.error("#messaging", {
                title: "ОШИБКА",
                body: "Заказ не отменен, попробуйте еще раз!",
                fadeout: true
            });
            status = false
        }
    });
    return status
}