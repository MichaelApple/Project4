/**
 * Created by Miha on 31.07.2017.
 */

jQuery(function($){
    $('input').on('keyup', function () {
        var name = $(this).attr("name");
        var regex ="^[0-9.]{3,20}$";

        switch (name) {
            case "name":
                regex = "^[А-яїЇA-z0-9-_]{3,20}$";
                break;
            case "homePhone":
                regex = "^\\([0-9]{3}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}$";
                break;
            case "email":
                regex = "^([A-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
                break;
            case "newEmail":
                regex = "^([A-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
                break;
            case "desiredDateTime":
                regex = "/(^(((0[1-9]|1[0-9]|2[0-8])[-](0[1-9]|1[012]))|((29|30|31)[-](0[13578]|1[02]))|((29|30)[-](0[4,6,9]|11)))[-](19|[2-9][0-9])\d\d$)|(^29[-]02[-](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/";
                break;
            default:
                regex = "^[A-z0-9-_]{3,20}$";
                break;
        }
        $('button[type="submit"]').attr('disabled','disabled');
        var input = this.value;
        if (input.match(regex)){
            $(this).addClass("form-control-success");
            $(this).parent().parent().addClass("has-success");
            $(this).removeClass("form-control-danger");
            $(this).parent().parent().removeClass("has-danger");
            $('button[type="submit"]').removeAttr('disabled');
        } else {
            $(this).removeClass("form-control-success");
            $(this).addClass("form-control-danger");
            $(this).parent().parent().addClass("has-danger");
            $(this).parent().parent().removeClass("has-success");
        }
    });
});

$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').focus()
});
$(function () {
    $('#datetimepicker8').datetimepicker({
        icons: {
            date: "fa fa-calendar",
            up: "fa fa-arrow-up",
            down: "fa fa-arrow-down"
        },
        format: 'DD-MM-YYYY',
        minDate: new Date()
    });
});




