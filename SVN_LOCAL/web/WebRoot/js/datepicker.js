$('#datetimepicker').datetimepicker();
$('#datetimepicker').on('changeDate', function (e) {
    $('#realdate').val(moment(e.date).format());
});