/*
 * Author: Abdullah A Almsaeed
 * Date: 4 Jan 2014
 * Description:
 *      This is a demo file used only for the main dashboard (index.html)
 **/

$(function () {
  'use strict'

  // Make the dashboard widgets sortable Using jquery UI
  $('.connectedSortable').sortable({
    placeholder: 'sort-highlight',
    connectWith: '.connectedSortable',
    handle: '.card-header, .nav-tabs',
    forcePlaceholderSize: true,
    zIndex: 999999
  })
  $('.connectedSortable .card-header').css('cursor', 'move')

  // jQuery UI sortable for the todo list
  $('.todo-list').sortable({
    placeholder: 'sort-highlight',
    handle: '.handle',
    forcePlaceholderSize: true,
    zIndex: 999999
  })

  // bootstrap WYSIHTML5 - text editor
  $('.textarea').summernote()

  $('.daterange').daterangepicker({
    ranges: {
      Today: [moment(), moment()],
      Yesterday: [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
      'Last 7 Days': [moment().subtract(6, 'days'), moment()],
      'Last 30 Days': [moment().subtract(29, 'days'), moment()],
      'This Month': [moment().startOf('month'), moment().endOf('month')],
      'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
    startDate: moment().subtract(29, 'days'),
    endDate: moment()
  }, function (start, end) {
    // eslint-disable-next-line no-alert
    alert('You chose: ' + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
  })

  /* jQueryKnob */
  $('.knob').knob()

  //-------------
  //- BAR CHART -
  //-------------
  var areaChartData = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
      {
        label: 'Digital Goods',
        backgroundColor: 'rgba(60,141,188,0.9)',
        borderColor: 'rgba(60,141,188,0.8)',
        pointRadius: false,
        pointColor: '#3b8bba',
        pointStrokeColor: 'rgba(60,141,188,1)',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(60,141,188,1)',
        data: [28, 48, 40, 19, 86, 27, 90]
      },
      {
        label: 'Electronics',
        backgroundColor: 'rgba(210, 214, 222, 1)',
        borderColor: 'rgba(210, 214, 222, 1)',
        pointRadius: false,
        pointColor: 'rgba(210, 214, 222, 1)',
        pointStrokeColor: '#c1c7d1',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(220,220,220,1)',
        data: [65, 59, 80, 81, 56, 55, 40]
      }
    ]
  }

  var barChartCanvas = $('#barChart').get(0).getContext('2d')
  var barChartData = $.extend(true, {}, areaChartData)
  var temp0 = areaChartData.datasets[0]
  var temp1 = areaChartData.datasets[1]
  barChartData.datasets[0] = temp1
  barChartData.datasets[1] = temp0

  var barChartOptions = {
    responsive              : true,
    maintainAspectRatio     : false,
    datasetFill             : false
  }

  new Chart(barChartCanvas, {
    type: 'bar',
    data: barChartData,
    options: barChartOptions
  })
})
