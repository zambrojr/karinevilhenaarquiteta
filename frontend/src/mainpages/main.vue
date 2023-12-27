<template>
    <div>
        <router-view v-if="SPA" v-show="readySpa"></router-view>
        <slot v-if="!readySpa"></slot>
    </div>        
</template>
  

<script>

/* global $*/

export default {

    /* */
    components: {
    }, 

    /* */
    watch: {
        '$route': function(value) {
            if(value.name=="showbairro"){
                this.SPA=true;
                this.$nextTick(function () {
                    //console.log("BAIRRO!!");
                    //this.$bus.emit('initComponentCategoria');                        
                    //dataLayer.push()
                })	                
            } 
        }
    },

    /* */
    props: {
        minSearchSize: {
            type: Number,
            default: 1
        }        
    },            

    /* */
    data() {
            return {
                SPA:true,
                readySpa:true,
            }
        },

    /* */
	computed:{
		
	},

    /* */
    methods: {



        /* */
        _readySpa(){
            this.readySpa=true;
        }
        

    },

    /* */
    created() {
        //this.$bus.on('readySpa', this._readySpa);
    },    

    /* */
    mounted(){
        


        
            $('#menuToggle').on('click', function() {
                $('body').toggleClass('open');
            });

	}        
        
}
</script>

