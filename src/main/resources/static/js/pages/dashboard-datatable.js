
$(document).ready(function () {
  $('#data-table').DataTable({
    ajax: {
      url: '/email/test',
      dataSrc: 'data'
    },
    columns: [{
      data: 'id'
    },
    {
      data: 'name'
    }
    ]
  });
});


