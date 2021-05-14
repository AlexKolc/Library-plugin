function getAllUsers() {
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/api/group/confluence-users/member",
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown)
        },
        success: function (data) {
            console.log(data)
            for (var user in data.results) {
                var groups = getGroups(user.name)
            }
            fillUsers(data.results)
            // fillGroups(data.results)

        }
    })
}



function getGroups(data) {
    AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/api/group/confluence-users/member",
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown)
        },
        success: function (data) {
            console.log(data)
            for (var user in data.results) {
                var groups = getGroups(user.name)
            }
            fillUsers(data.results)
            // fillGroups(data.results)

        }
    })
}

function fillUsers(lendings) {
    var dataGrid = $("#gridContainer-control-roles").dxDataGrid({
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
                caption: "Имя пользователя",
                dataField: "displayName",
                allowHeaderFiltering: false,
                allowSearch: true
            }, {
                caption: "Администратор библиотеки",
                type: "buttons",
                width: 200,
                buttons: [{
                    name: "admin",
                    icon: "check",
                    onClick: function (e) {

                    }
                }]
            }, {
                caption: "Пользователь",
                type: "buttons",
                width: 200,
                buttons: [{
                    name: "admin",
                    icon: "check",
                    onClick: function (e) {

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