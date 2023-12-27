<template>

    <div>

        <section class="statistics section-padding">
                <div class="container-fluid">
                    <div class="row d-flex">

                        <div v-for="objModulo in this.$root.listaModulos" class="col-lg-4 box-income" :key="objModulo.id">
                        <!-- Income-->
                        <div class="card income text-center" @click="go(objModulo)">
                            <div class="icon"><b-icon :icon="objModulo.ico"></b-icon></div>
                            <strong class="text-primary">{{objModulo.nome}}</strong>
                            <p>{{objModulo.descricao}}</p>
                        </div>
                        </div>       

                    </div>
                </div>
            </section>
            
    </div>

</template>

<script>

export default {
    
    /* */
    components: {      
    }, 

    /* */
    data() {
        return {
        }
    },

    /* */
    methods: {

        listouModulos(){
        },

        go(objModulo){
            this.$root.setModulo(objModulo);
        },

        /* */
        balaoBoasVindas(){
            if(this.$root.isUsuarioCarregado() && !this.$root.isUsuarioValido()){
                var textoBalao = "Olá, seja bem vindo ao nosso tour!";
                textoBalao += "<br><br>Eu sou a Bianca Trindade e aqui voce bla bla bla...";
                textoBalao += "<br><br>Em <strong>Orçamento</strong> voce poderá bla bla bla...";
                this.$root.showTips(textoBalao, []);      
            }
        }
      
    },


    /* */
    created() {
       this.$bus.on('userLoaded', this.balaoBoasVindas);
    },  


    

    /* */
    mounted(){
        this.balaoBoasVindas();
    } ,  
        
}
</script>

