<template>

        <div v-bind:class="'col-lg-' + sizeBox" >
            <div class="card box">
                <div class="card-header align-items-center">
                    <H4 class="align-middle"><span class="titleBox">{{titulo}}</span><span class="float-right"><button type="button" class="btnbox btn btn-outline-secondary" @click="openClose()" style="border-radius: 6px;"><b-icon :icon="iconBox"></b-icon></button></span></h4>
                </div>
                <div :id="id" class="card-body" v-bind:class="{limitSize: !this.open}">
                    <slot></slot>    
                </div>
            </div>
        </div>

</template>


<script>


export default {


    /* */
    props: {
        titulo:{
            type: String,
            default: ''
        },        
        isOpen:{
            type: Boolean,
            default: false
        },
        id:{
            type: String,
            default: ''
        },
    },    


  /* */
    data() {
            return {
                open:this.isOpen
            }
    },


    /* */
	computed:{
		/* */ 
        sizeBox(){ return this.open ? 12 : 6 ;  },  

        /* */ 
        iconBox(){ return this.open ? 'aspect-ratio' : 'aspect-ratio' ;  },  
    },
    

    methods: {

        
        /* */
        openClose(){
            this.open   =   !this.open;
            this.$emit("resizePainel", this.open, this.id)
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