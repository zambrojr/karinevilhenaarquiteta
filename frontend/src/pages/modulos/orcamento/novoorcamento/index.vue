<template>


          <section class="statistics section-padding">
                <div class="container-fluid">
                    <div class="row d-flex">
                        <box-padrao isOpen titulo="Dados do Cliente">
                            <form-cadastro ></form-cadastro>
                        </box-padrao>
                    </div>
                </div>    

</section>

</template>

<script>



import formCadastro from "./formCadastro"
//https://www.twilio.com/blog/2018/04/choosing-cameras-javascript-mediadevices-api.html

export default {
    
    /* */
    components: {      
        "form-cadastro": formCadastro
    }, 


    /* */
    props: {
    },   

    /* */
    data() {
        return {

        }
    },


    computed:{
       

    },


    /* */
    methods: {

        
        
      
    },

    /* */
    mounted(){

       
    } ,  
        
}
</script>

