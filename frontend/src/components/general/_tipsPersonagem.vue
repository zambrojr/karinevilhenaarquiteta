<template>
<section>
	<div class="tips" v-if="open">
		<div class="falatip speech-bubble-ds">
			<p class="mb-2" v-html="texto"></p>
            <div class="m-1" v-for="(op, ind) in opcoes" v-bind:key="ind"><a @click="op.action(op.params)" class="opcBaloon">{{op.label}}</a></div>
			<div class="row"> 
                <div class="text-left col"><button v-for="(buton, ind) in buttons" v-bind:key="ind" @click="buton.action">{{buton.label}}</button></div>
                <div class="text-right col"><button @click="click">Não, obrigado!</button></div>
			</div>  
			<div class="speech-bubble-ds-arrow"></div>
		</div>
		<img src="/images/karine.webp" alt="person" class="img-hor personagens" />
	</div>  
    <b-modal v-model="modalShow" hide-footer>
    <fale-conosco></fale-conosco>
    </b-modal>
</section>    
</template>

<script>

import faleConosco from '../sistema/outros/faleConosco';
export default {
    
    /* */
    props: {

    },        

    /* */
    components: {
        faleConosco,
        BModal: () => import('bootstrap-vue').then(module => module.BModal)
    },     

    /* */
    data() {
            return {
                open:true,
                texto: 'Olá, eu sou a Karine Vilhena, Arquiteta e design de interiores. É um pazer te receber!<br><br>Posso te ajudar em algo?',
                buttons: [],
                opcoes: [],         
                modalShow:false       
            }
    },

    /* */
	computed:{

	},

    /* */
    methods: {

        click(){
            this.$emit("close");
            this.open= false;
        },


        listaEspecialidades(){

            this.$api.utils.listaEspecialidades({})
            .then(response => { 
                
                let especialidades = this.$api.scl(response.data);
                this.opcoes = [];
                especialidades.forEach(esp=>{
                    this.opcoes.push({label:esp.titulo, action:this.loadServico,params:esp});
                })

            }) 
            .catch();  

        },            

        contatoComigo(){
            this.modalShow = true;
            this.texto = "Prezo muito pela seu sigilo. Seus dados não serão utilizados senão para o meu contato com você!";
            this.opcoes = [
                          
                          {label:"Que tal conhecer mais sobre as minhas especialidades?", action:this.especialidades,params:null},
                          {label:"Quer conhecer o meu processo de trabalho?", action:this.loadOpcoesEstagioDois,params:null}
            ];
            this.buttons = [];
        },
        
        loadServico(esp){
            window.inicia("/servicos/"+esp.url);
            this.opcoes = [];
            this.texto = esp.descricao_breve
            this.opcoes = [
                          {label:"Gostaria de um contato?", action:this.contatoComigo,params:null},
                          {label:"Que tal conhecer mais sobre as minhas especialidades?", action:this.especialidades,params:null},
                          {label:"Quer conhecer o meu processo de trabalho?", action:this.loadOpcoesEstagioDois,params:null}
            ];            
        },

        especialidades(){
            window.inicia("/servicos");
            this.texto = "Eu ...";
            this.listaEspecialidades();
        },


        loadOpcoesEstagioDois(){
            this.texto = "Claro, vamos la!";
            this.opcoes = [
                          {label:"Gostaria de um contato?", action:this.contatoComigo,params:null},
                          {label:"Que tal conhecer mais sobre as minhas especialidades?", action:this.especialidades,params:null},
                          {label:"Quer conhecer o meu processo de trabalho?", action:this.loadOpcoesEstagioDois,params:null}
            ];
            this.buttons = [];
        }

    },


    /* */
    mounted(){
        
        this.buttons = [{label:"Sim, por favor!", action:this.loadOpcoesEstagioDois}];

    },    


}
</script>