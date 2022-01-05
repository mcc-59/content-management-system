$(document).ready(function () {
    getTrending();
    getAll();
  });

function getAll() {
    $.ajax({
        url: '/content/get-all',
        type: 'GET',
        success: function (data) {
            let target = $("#contentCard")
            $.each(data, function (index, value) {
                var el = data[index];
                target.append(
                    '<div class="col">' + 
                    '<div class="card shadow-sm">' + 
                    '<img src="/image/'+ el.id + '.jpg" class="bd-placeholder-img card-img-top" width="100%" height="225"' +
                    'role="img" focusable="false" preserveAspectRatio="xMidYMid slice">' +
                    '<div class="card-body">' + 
                    '<div calss="card-title" style="height: 116px;">' + '<h2 class="text-center">'+ el.title +'</h2>' +
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

function getTrending(){
    $.ajax({
        url: '/content/get-trending',
        type: 'GET',
        success: function(data){
            let target = $("#trending")
            var el = data;
            target.append(
                '<div class="row">' + '<div class="col-md-6 pe-5">' + '<h1 class="display-4 fst-italic fw-bold me-2">' +
                el.title + '</h1>' + '<p class="lead my-3 description-text me-2">' + el.body + '</p>' + 
                '<p class="lead mb-0">' + '<a class="text-dark fw-bold "href="/page/content/' + el.id + '">Read more..</a>'
                + '</p>' + '</div>' + '<div class="col-md-6 px-0 bg-cream center-cropped">' + '<img src="/image/'+ el.id + '.jpg" width="100%" role="img"></img>'
                + '</div>' + '</div>'
            )
        }
    })
}