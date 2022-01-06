$(document).ready(function () {
    
    onSubmit();
});

function onSubmit() {
    $('#signupButton').click((e) => {
        
        e.preventDefault();

        const user = {
            fullName : $('#signupForm #fullName').val(),
            username : $('#signupForm #username-signup').val(),
            password : $('#signupForm #password-signup').val(),
            email : $('#signupForm #email').val()

            
        };
        
        
        $.ajax({
            url: '/user/form',
            type: 'POST',
            data: JSON.stringify(user),
            contentType: 'application/json',
            success: function (res) {
                console.log(res);
                console.log($('#signupModal').modal('toggle'));
                Swal.fire({
                    title: 'Success',
                    text: 'Check your email to activate your account',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                });
            },
            error: function (e) {
                console.log(e);
            }
        })

    })
}

