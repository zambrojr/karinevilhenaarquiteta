<template>

<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Sign In</h3>
				<!--<div class="d-flex justify-content-end social_icon">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div>-->
			</div>
			<div class="card-body">
                <b-form :action="actionLogin" method="post">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-user"></i></span>
						</div>
						<input type="email" class="form-control" v-model="username" name="username" placeholder="Email">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-key"></i></span>
						</div>
						<input type="password" class="form-control" v-model="password" name="password" placeholder="Password">
					</div>
					<div class="row align-items-center remember">
						<input type="checkbox" name="remember-me">Remember Me
					</div>
					<div class="form-group">
						
                                             <button type="submit" class="btn float-right login_btn" text="fefe">Entrar</button>
					</div>
                </b-form>
			</div>
                    
                    
			<div class="card-footer">
                                <div v-if="false" class="alert alert-danger alert-dismissable">
                                 <p>
                                  <i class="icon fa fa-ban"></i> <span th:text="#{error.login_failed}">Invalid Email and Password.</span>
                                 </p>
                                </div>
                                <div v-if="false" class="alert alert-info alert-dismissable">
                                 <p>
                                  <i class="icon fa fa-info"></i> <span th:text="#{info.logout_success}">You have been logged out.</span>
                                 </p>
                                </div>
                                <div v-if="false" class="alert alert-warning alert-dismissable">
                                 <p>
                                  <i class="icon fa fa-warning"></i> <span th:text="${msg}"></span>
                                 </p>
                                </div>
			</div>                    
                    

		</div>
	</div>
</div>

</template>

<script>
export default {

    data() {
        return {
            username:'',
            password:'',
            actionLogin: process.env.VUE_APP_BACKEND_URL+"/login"
        }
    },

    methods:{    

        /* */
        logaUser(){
            this.$api.usuario.login({email:this.username, password:this.password})
            .then(response => {
                // login successful if there's a jwt token in the response
                if (response.data.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
    
                    console.log("sdfdsf");
                    localStorage.setItem('token', response.data.token);
                    this.$root.loadUseLoggedIn(true);
    
                }
    
                return response;
            })
            ;            
        },
    }

        

}

</script>
