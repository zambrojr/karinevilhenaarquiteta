<template>
    <div>      
        <div v-bind:style="enemyStyle"></div>
        
    </div>
</template>

<script>
    
    
    
    
export default {
    props: {
        //rows: Array,
        //type: String,
        ground:  {
            type: Number,
            default: 0
        }
    },
    
    data() {
        return {

            enemy:{
                velocidade:9.0,
                left:0,
                top:0,
                directionTop:true,
                directionLeft:true,
                height:44,
                width:51       
            },
        }
    },
    
    methods:{

        movimentaEnemy(){
            
            this.enemy.top      = this.enemy.directionTop    ? this.enemy.top +(1 * this.enemy.velocidade) : this.enemy.top -(1 * this.enemy.velocidade);
            
            this.enemy.left      = this.enemy.directionLeft   ? this.enemy.left+(1 * this.enemy.velocidade) : this.enemy.left-(1 * this.enemy.velocidade);
            if( this.enemy.top >= this.ground  )   return false;
            
            return true;
            
        },
        
        initEnemy(){
            
            this.enemy.left      = Math.floor((Math.random() * window.innerWidth) + 1);
            
            var tis = this;
            var interval = setInterval(function(){
                    // clearInterval(interval);
                    if(!tis.movimentaEnemy()){
                        tis.$emit('objetivoCompleto', tis);
                        clearInterval(interval);
                    }
                }, 100);
        },
        
        
        fire(){
            
        }

    },
    
    computed: {

            enemyStyle(){
                return {
                    position: 'fixed',
                    left: this.enemy.left + 'px',
                    top: this.enemy.top + 'px',
                    height: this.enemy.height + 'px',
                    width: this.enemy.width + 'px',
                    'background-image': 'url("/images/space/PH86_Space-Invader_Pin-595x595.png")'
                }
            },

    },
    
    /*  */
    mounted() {
        this.initEnemy();
    },      
    
    beforeDestroy(){
        console.log("beforeDestroy");
    },
    
    destroyed(){
        console.log("destroy");
    }    
    
    
}
</script>

