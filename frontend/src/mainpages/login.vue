<template>

<div>

<div class="page_loader"></div>

<!-- Login section start -->
<div class="login-section">
    <div class="container-fluid">
        <div class="row login-box">
            <div class="col-lg-6 align-self-center pad-0 form-section">
                <div class="form-inner">
                    <a href="index.html" class="logo">
                        <img src="img/logos/logo.png" alt="logo">
                    </a>
                    <h3>Sign into your account</h3>
                    <form action="#" method="GET">
                        <div class="form-group form-box">
                            <input type="email" v-model="username" name="email" class="input-text" placeholder="Email Address">
                        </div>
                        <div class="form-group form-box clearfix">
                            <input type="password" v-model="password" name="Password" class="input-text" placeholder="Password">
                        </div>
                        <div class="form-group checkbox clearfix">
                            <div class="form-check checkbox-theme">
                                <input class="form-check-input" type="checkbox" value="" id="rememberMe">
                                <label class="form-check-label" for="rememberMe">
                                    Remember me
                                </label>
                            </div>
                            <a href="forgot-password.html" class="forgot-password">Forgot Password</a>
                        </div>
                        <div class="form-group clearfix">
                            <button @click="logaUser" class="btn-md btn-theme btn-block">Login</button>
                        </div>
                        <div class="extra-login clearfix">
                            <span>Or Login With</span>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                    <div class="social-list">
                        <a href="#" class="facebook-bg">
                            <i class="fa fa-facebook"></i>
                        </a>
                        <a href="#" class="twitter-bg">
                            <i class="fa fa-twitter"></i>
                        </a>
                        <a href="#" class="google-bg">
                            <i class="fa fa-google"></i>
                        </a>
                        <a href="#" class="linkedin-bg">
                            <i class="fa fa-linkedin"></i>
                        </a>
                    </div>
                    <p>Don't have an account? <a href="signup.html" class="thembo"> Register here</a></p>
                </div>
            </div>
            <div class="col-lg-6 bg-color-15 align-self-center pad-0 none-992 bg-img">
                <img src="http://placehold.it/860x550" alt="logo" class="w-100 img-fluid">
            </div>
        </div>
    </div>
</div>
<!-- Login section end -->

<!-- Full Page Search -->
<div id="full-page-search">
    <button type="button" class="close">×</button>
    <form action="index.html#">
        <input type="search" value="" placeholder="type keyword(s) here"/>
        <button type="submit" class="btn btn-sm button-theme">Search</button>
    </form>
</div>

</div>


</template>

<script>
export default {

    data() {
        return {
            username:'',
            password:'',
        }
    },

    methods:{    

        /* */
        logaUser(event){
            event.preventDefault();
            this.$api.usuario.login({email:this.username, password:this.password})
            .then(response => {
                // login successful if there's a jwt token in the response
                if (response.data.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
    
                    localStorage.setItem('token', response.data.token);
                    this.$root.loadUseLoggedIn(true);
    
                }
    
                return response;
            })
            .catch(() => {
                    //commit('loginFailure', error);
            });            
        },
    }

        

}

</script>
