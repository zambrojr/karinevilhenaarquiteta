<template>

    <div>

        <section class="statistics section-padding">
                <div class="container-fluid">
                    <div class="row d-flex">

                        <div class="col-lg-4 box-income">
                        <!-- Income-->
                        <div class="card income text-center" @click="go('orcamento_novoorcamento')">
                            <div class="icon"><b-icon icon="plus-circle"></b-icon></div>
                            <strong class="text-primary">Novo Orçamento</strong>
                            <p>Novo Orçamento</p>
                        </div>
                        </div>       


                        <div class="col-lg-4 box-income">
                        <!-- Income-->
                        <div class="card income text-center" @click="go('orcamento_listaproposta')">
                            <div class="icon"><b-icon icon="card-checklist"></b-icon></div>
                            <strong class="text-primary">Propostas</strong>
                            <p>Propostas em andamento e enviadas</p>
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

        go(objModulo){
            if(this.$root.isUsuarioValido()){
                this.$router.push({name:objModulo});
            } else {
                this.$router.push({name: 'orcamento_painelproposta', query: {proposta: '2a445d09fb2e6b450273171391105191f86a624502048ae34b364b59b904ce0b'}})
            }
        },      
        
        /* */
        balaoBoasVindas(){
            if(this.$root.isUsuarioCarregado() && !this.$root.isUsuarioValido()){
                var textoBalao = "Agora clique em 'Orçamento' para iniciar a demonstração! ";
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

