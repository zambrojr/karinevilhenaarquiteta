<template>
    <div>      
        <div v-bind:style="objetoStyle"></div>
        
    </div>
</template>

<script>
    
    
    
    
export default {
    props: {
        //rows: Array,
        //leftPosition: 0,
    },
    
    data() {
        return {

            objeto:{
                velocidade:10.0,
                left:0,
                top:0,
                height:20,
                width:20              
            },
        }
    },
    
    methods:{

        movimentaEnemy(){
            
            this.objeto.top      = this.objeto.top -(1 * this.objeto.velocidade);
            
            //this.objeto.left      = this.objeto.directionLeft   ? this.objeto.left+(1 * this.objeto.velocidade) : this.objeto.left-(1 * this.objeto.velocidade);
            
            if( this.objeto.top >= window.innerHeight || this.objeto.top <= 0 )   return false;
            
            return true;
            
        },
        
        setLeftPosition(leftPosition){
            this.objeto.left    =   leftPosition;
        },
        
        initFire(){
            
            this.objeto.top = window.innerHeight - 20;
            
            
            var tis = this;
            var interval = setInterval(function(){
                    // clearInterval(interval);
                    if(!tis.movimentaEnemy()){
                        tis.$emit('objetivoCompleto', tis);
                        clearInterval(interval);
                    }
                }, 1);
        },
        
        
        fire(){
            
        }

    },
    
    computed: {

            objetoStyle(){
                return {
                    position: 'fixed',
                    left: this.objeto.left + 'px',
                    top: this.objeto.top + 'px',
                    height: this.objeto.height + 'px',
                    width: this.objeto.width + 'px',
                    'background-image': 'url("/images/space/fire.png")'
                }
            },

    },
    
    /*  */
    mounted() {
        this.initFire();
    },      
    
    beforeDestroy(){
        console.log("beforeDestroy");
    },
    
    destroyed(){
        console.log("destroy");
    }    
    
    
}
</script>

