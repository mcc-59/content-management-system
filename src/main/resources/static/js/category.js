$(document).ready(function () {
    var id = document.getElementsByTagName("H1")[0].getAttribute("data-value"); 
    getByCategoryId(id);
});

function getByCategoryId(id) {
    $.ajax({
        url: '/page/content-category/' + id,
        type: 'GET',
        success: function (data) {
            let target = $("#contentCard")
            $.each(data, function (index, value) {
                var el = data[index];
                target.append(
                    '<div class="col">' +
                    '<div class="card shadow-sm">' +
                    '<img src="/image/covid.jpg" class="bd-placeholder-img card-img-top" width="100%" height="225"' +
                    'role="img" focusable="false" preserveAspectRatio="xMidYMid slice">' +
                    '<div class="card-body">' +
                    '<div calss="card-title" style="height: 116px;">' + '<h2 class="text-center">' + el.title + '</h2>' +
                    '</div>' + '<p class="card-text description-text-card">' + el.body + '</p>' +
                    '<div class="d-flex justify-content-between align-items-end">' + '<div class="btn-group">' +
                    '<a href="/page/content/' + el.id + '">' +
                    '<button type="button" class="btn btn-md btn-outline-secondary">Read More</button>' +
                    '</a></div>' + '<small class="text-muted">' + el.user.fullName + '</small>' +
                    '</div>' + '</div>' + '</div>' + '</div>' + '</div>'
                )
            });
        }
    });
}

function getId(){
    console.log(document.getElementById('category').getAttribute("data-value"));
}