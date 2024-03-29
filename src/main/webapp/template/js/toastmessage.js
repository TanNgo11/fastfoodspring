


const toasts = {
    success_add: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Add successful !',
        alert: 'success'
    },
    success_signUp: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Sign up successful !',
        alert: 'success',
    },
    success_delete: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Delete successful !',
        alert: 'success',
    },
    success_update: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Update successful !',
        alert: 'success',
    },
    success_reset: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Please check the email to confirm this reset!',
        alert: 'success',
    },
    
    success_resetpassword: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Reset password successful',
        alert: 'success',
    },
    success_change: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Change successful !',
        alert: 'success'
    },
    success_add_to_cart: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Add to cart successful !',
        alert: 'success'
    },
    success_order: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Order successful !',
        alert: 'success'
    },
    
    success_order_status: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Change order successful !',
        alert: 'success'
    },
    success: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Buy successful !',
        alert: 'success'
    },
    error_login: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'You need to login !',
        alert: 'error'
    },
    error_validation_phonenumber: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Please enter a valid phonenumber',
        alert: 'error'
    },
    
    error_validation_email: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Please enter a valid email address.',
        alert: 'error'
    },
    
    
    error_validation_address: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Please enter your address.',
        alert: 'error'
    },
    error_notfound: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Can not find any orders',
        alert: 'error'
    },
    
    error_reset: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Error!',
        alert: 'error'
    },
    error_empty: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Your cart is empty !',
        alert: 'error'
    },
    error_instock: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'The product is out of stock please comback later!',
        alert: 'error'
    },
    error_signUp: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Sign up unsuccess !',
        alert: 'error'
    },
    error_delete: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Delete unsuccess !',
        alert: 'error'
    },
    error_change: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Change user information unsuccess !',
        alert: 'error'
    },
    error_add: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Add new product unsuccess !',
        alert: 'error'
    },
    error_update: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'update product unsuccess !',
        alert: 'error'
    }
}

function createToast(status) {
    let toast = document.createElement('div')
    toast.className = `toast ${status} ${toasts[status].alert}`

    toast.innerHTML = `
    ${toasts[status].icon}
    <span class="msg">${toasts[status].msg}</span>
    <span class="countdown"></span>
    `
    document.querySelector('#toasts').appendChild(toast)

    setTimeout(() => {
        toast.style.animation = 'hide_slide 1s ease forwards'
    }, 4000)
    setTimeout(() => {
        toast.remove()
    }, 6000)


}

let msg = document.getElementById("message");
if(msg.value!==""){
	createToast(msg.value);
	msg.value = "";	
}




