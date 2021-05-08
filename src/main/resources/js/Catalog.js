AJS.toInit(function () {
    var books = AJS.$.ajax({
        type: "GET",
        url: "/confluence/rest/library/1.0/book/getBooks",
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown)
        },
        success: function (data) {
            // console.log(data)
            books = data
            // console.log(books)
            // console.log(books1)
        }
    }).done(function () {
        var dataGrid = $("#gridContainer").dxDataGrid({
            dataSource: books,
            columnsAutoWidth: true,
            showBorders: true,
            // focusedRowEnabled: true,
            // focusedRowKey: 1,
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
            columns: [
                {
                    caption: "ID Книги",
                    dataField: "id",
                    width: 80
                }, {
                    caption: "Название книги",
                    dataField: "name",
                    headerFilter: {
                        allowSearch: true
                    }
                }, {
                    caption: "Авторы",
                    dataField: "authors",
                    width: 200,
                    headerFilter: {
                        allowSearch: true
                    }
                }, {
                    caption: "Теги",
                    dataField: "tags",
                    width: 200,
                    headerFilter: {
                        allowSearch: true
                    }
                }, {
                    caption: "Типы изданий",
                    dataField: "editionTypes",
                    width: 200,
                    headerFilter: {
                        allowSearch: true
                    }
                }, {
                    type: "buttons",
                    buttons: [{
                        text: "Подробнее",
                        //hint: "",
                        onClick: function (e) {
                            var bookId = e.row.data.id
                            window.location.href = '/confluence/library/book-info.action?id=' + bookId;
                        }

                        // {
                        // template: function () {
                        //     var column = this
                        //     console.log("column:" + column.dataField.id)
                        //     var link = $("<a>").text("Подробнее")
                        //         .attr("href", "#");
                        //     link.on("click", function () {
                        //         console.log("DDDDDDDDDDDDD");
                        //     });
                        //     return link;
                        // }
                    }]
                }
            ]
        }).dxDataGrid('instance');


        console.log(books)
        // console.log(books1)

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
    });
})
