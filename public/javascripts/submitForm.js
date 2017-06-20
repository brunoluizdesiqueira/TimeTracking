/**
 * Created by Bruno on 19/06/17.
 */
function submitForm() {
    $.ajax({
        url: jQuery("#formulario").attr("action"),
        type: jQuery("#formulario").attr("method"),
        data: jQuery("#formulario").serialize(),
        statusCode: {
            201: (function () {
                alert("Record was created, you can close the modal");
            }),
            400: (function () {
                alert("hmmm badRequest...");
            })
        }
    });
}
