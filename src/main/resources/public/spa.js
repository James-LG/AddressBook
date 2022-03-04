bookDetailsJSON = {
    currentBookId: 0,
    request: function(bookId) {
        $.ajax({
            url: `http://localhost:8080/addressBook/${bookId}/buddyInfos`
        }).then(function(data) {
            bookDetailsJSON.currentBookId = bookId;
            bookDetailsJSON.render(data);
        });
    },
    render: function(data) {
        let tableHtml =
            "<button onclick='bookJSON.request()'>Back</button>\n" +
            "<table>\n" +
            "   <tr>\n" +
            "       <th>ID</th>\n" +
            "       <th>Name</th>\n" +
            "       <th>Address</th>\n" +
            "       <th>Phone Number</th>\n" +
            "   </tr>";

        data['_embedded']['buddy'].forEach(buddy => {
            tableHtml +=
                "   <tr>\n" +
                `      <td>${buddy['name']}</td>\n` +
                `      <td>${buddy['address']}</td>\n` +
                `      <td>${buddy['phoneNumber']}</td>\n` +
                "   </tr>\n";
        });
        tableHtml += "</table>";

        $('#table').html(tableHtml);

        $('#form').html(
            `<form>\n` +
            "   <p>Name: <input type=\"text\" id='name' /></p>\n" +
            "   <p>Address: <input type=\"text\" id='address' /></p>\n" +
            "   <p>Phone Number: <input type=\"text\" id='phoneNumber' /></p>\n" +
            '   <p><input type=\"submit\" value=\"Add Buddy\" onclick="bookDetailsJSON.submit()" /></p>\n' +
            "</form>");
    },
    submit: function() {
        let name = $('#name').val();
        let address = $('#address').val();
        let phoneNumber = $('#phoneNumber').val();

        $.ajax(
            {
                url: `http://localhost:8080/buddy`,
                dataType: 'json',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "name": name,
                    "address": address,
                    "phoneNumber": phoneNumber,
                    "addressBook": `addressBook/${bookDetailsJSON.currentBookId}`
                })
            }).then(function() {
                bookDetailsJSON.request(bookDetailsJSON.currentBookId);
            });
    }
}

bookJSON = {
    request: function() {
        $.ajax({
            url: "http://localhost:8080/addressBook"
        }).then(function(data) {
            bookJSON.render(data);
        });
    },
    render: function(data) {
        let tableHtml =
            "<table>\n" +
            "   <tr>\n" +
            "      <th>ID</th>\n" +
            "   </tr>\n";

        data['_embedded']['addressBook'].forEach(book => {
            tableHtml +=
                "   <tr>\n" +
                `      <td><a href='#' onclick='bookDetailsJSON.request(${book.id})'>${book.id}</a></td>\n` +
                "   </tr>\n";
        });
        tableHtml += "</table>";

        $('#table').html(tableHtml);

        $('#form').html(
            "<form>\n" +
            "   <input type=\"submit\" value=\"Create New Book\" onclick='bookJSON.submit()'/>\n" +
            "</form>");
    },
    submit: function() {
        $.ajax(
            {
                url: `http://localhost:8080/addressBook`,
                dataType: 'json',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "buddyInfos": []
                })
            }).then(function() {
                bookJSON.request();
            });
    }
}

$(document).ready(function() {
    bookJSON.request();
});