function getAllLendigs() {
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/lending/getLendings",
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown)
        },
        success: function (data) {
            fillLendings(data)
        }
    })
}

function fillLendings(lendings) {
    var isChief = function (position) {
        return position === "На руках" || position === "Возвращена" || position === "Потеряна"
    };

    var isWaiting = function (position) {
        return position === "Ожидается выдача"
    };

    var isOnHands = function (position) {
        return position === "На руках"
    };

    var isLost = function (position) {
        return position === "Потеряна"
    };

    console.log(lendings)
    var dataGrid = $("#gridContainer-lendings-info").dxDataGrid({
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
            }
            // useIcons: true
        },
        onCellPrepared: function onCellPrepared(e) {
            if (e.rowType === "header") {
                e.cellElement.css("text-align", "center");
            }
            if (e.rowType === "data" && e.index === 1) {
                e.cellElement.css("text-align", "center");
            }
        },
        onRowPrepared: function (e) {
            if (e.rowType === "data" && e.data.status === "На руках") {
                var onHands = (new Date().getTime() - new Date(e.data.dateOfIssue).getTime()) / (1000 * 3600 * 24);
                if (onHands > e.data.returnPeriod) {
                    e.rowElement.css("background-color", "#ff000045");
                }
            }
            if (e.rowType === "data" && e.data.status === "Возвращена") {
                var onHands = (new Date(e.data.returnDate).getTime() - new Date(e.data.dateOfIssue).getTime()) / (1000 * 3600 * 24);
                if (onHands < e.data.returnPeriod) {
                    e.rowElement.css("background-color", "#15ff0045");
                } else {
                    e.rowElement.css("background-color", "#ffaa0045");
                }

            }
            if (e.rowType === "data" && e.data.status === "Потеряна") {
                e.rowElement.css("background-color", "#0400ff45");
            }
        },
        columns: [
            {
                type: "buttons",
                width: 50,
                buttons: [{
                    name: "delete",
                    icon: "trash",
                    onClick: function (e) {
                        deletelending(e.row.data.id);
                        lendings.splice(e.row.rowIndex, 1)
                        e.component.refresh(true);
                        e.event.preventDefault();
                    }
                }]
            }, {
                caption: "Номер заказа",
                dataField: "id",
                width: 70,
                allowHeaderFiltering: false,
                allowSearch: false
            }, {
                caption: "Пользователь",
                dataField: "userName",
                width: 200,
                allowHeaderFiltering: true,
                headerFilter: {
                    allowSearch: true
                }
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
                width: 130,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                caption: "Дата возврата",
                dataField: "returnDate",
                dataType: "date",
                width: 130,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                caption: "Статус",
                dataField: "status",
                width: 130,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                caption: "Дата изменения статуса",
                dataField: "dateChangedStatus",
                dataType: "date",
                width: 130,
                headerFilter: {
                    allowSearch: true
                }
            }, {
                type: "buttons",
                width: 200,
                buttons: [{
                    text: "Выдана",
                    visible: function(e) {
                        return isWaiting(e.row.data.status);
                    },
                    onClick: function (e) {
                        var lending = changeStatus(e.row.data.id, e.row.data.status, 0)
                        getAllLendigs()
                    }
                }, {
                    text: "Возвращена",
                    visible: function(e) {
                        return isOnHands(e.row.data.status) || isLost(e.row.data.status);
                    },
                    onClick: function (e) {
                        changeStatus(e.row.data.id, e.row.data.status, 0)
                        getAllLendigs()
                    }
                }, {
                    text: "Потеряна",
                    visible: function(e) {
                        return isOnHands(e.row.data.status);
                    },
                    onClick: function (e) {
                        changeStatus(e.row.data.id, e.row.data.status, 1)
                        getAllLendigs()
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

function changeStatus(lendingId, curStatus, isLost) {
    var status
    if (curStatus === "Ожидается выдача") {
        status = "ON_HANDS"
    }
    if ((curStatus === "На руках" || curStatus === "Потеряна") && !isLost) {
        status = "RETURNED"
    }
    if (curStatus === "На руках" && isLost) {
        status = "LOST"
    }

    var lending
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/lending/changeStatus",
        contentType: "application/json",
        data: {status: status, lending_id: lendingId},
        async: false,
        success: function (data) {
            lending = data
            AJS.messages.success("#messaging", {
                title: "Успешно",
                body: "Статус изменен",
                fadeout: true
            });
        },
        error: function () {
            AJS.messages.error("#messaging", {
                title: "ОШИБКА",
                body: "Статуc не изменен, попробуйте еще раз!",
                fadeout: true
            });
        }
    });
    return lending
}

function deletelending(lendingId) {
    var lending = {
        id: lendingId
    }
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
        }
    });
}

// function updateTable(e, data) {
//     console.log(data)
//
// }