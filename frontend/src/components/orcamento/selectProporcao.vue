<template>

    <b-form-select class="inputPersonal" size="sm" v-model="selecionado" @change="setSelecionado()" :options="optionsProporcao"></b-form-select>

</template>


<script>


export default {


    /* */
    props: {
        value:{type:Object, default: function(){ return {}}}
    },    

    watch:{
        value: {
            immediate: true,
            deep: true,
            handler() {
                this.selecionado = this.value.id;
            }
        }
    },

  /* */
    data() {
            return {
                listaProporcao:[{id:1, descricao:"Pequeno"},{id:2, descricao:"Médio"},{id:3, descricao:"Grande"}],
                selecionado:null
            }
    },


    /* */
	computed:{
        optionsProporcao(){
            return this.listaProporcao.map(x=>{return {value: x.id, text:x.descricao}})
        }
    },
    

    methods: {

        
        /* */
        setSelecionado(){
            var objeto = this.listaProporcao.find(x=>{return x.id == this.selecionado})
            this.$emit("input", objeto)
        }
    }    



}


</script>

<style>
.limitSize{
    max-height:400px;
    min-height:400px;
    overflow:auto
}
</style>