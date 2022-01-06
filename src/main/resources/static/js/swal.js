$('.swal').click(function (e) {
    e.preventDefault();
    var signupform = this.form;
    Swal.fire({
        title: 'Success',
        text: 'Check your email to activate your account',
        icon: 'success',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok'
    }).then((result) => {
        if (result.isConfirmed) {
            signupform.submit();
            signupform.modal('toggle');
        }
    })
})